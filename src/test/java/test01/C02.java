package test01;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C02 extends TestBase {

/*
"https://www.trendyol.com/" sitesine git
Giris menusu ustune git
menunun acildigini dogrula
Uye Ol menusunu tikla
bilgileri doldur
"Kişisel verilerimin işlenmesine yönelik aydınlatma metnini okudum ve anladım." metnini tıkla, metni al, aldigini dogrula
Uye Ol butonunu tikla
"E-POSTA DOĞRULAMA" metninin gorundugunu dogrula
 */
    @Test
    public void name() {

//   "https://www.trendyol.com/" sitesine git
        driver.get("https://www.trendyol.com/");

        try {
            driver.findElement(By.id("Rating-Review")).click();
            driver.findElement(By.id("rejectAllButton")).click();
        } catch (Exception e) {

        }
        //Giris menusu ustune git
       moveToElement ("//p[text()='Giriş Yap']");

        //menunun acildigini dogrula
        WebElement menu=findXpathWebelement("//div[@class='new-login-dropdown']");
        assertTrueIsDisplayed(menu);

       //Uye Ol menusunu tikla
        findByXpathClick("//div[@class='signup-button signup-button-container']");

        //bilgileri doldur
        Faker faker=new Faker();
        WebElement email= findByIdWebelement("register-email");
        email.sendKeys(faker.internet().emailAddress());
        WebElement password= findByIdWebelement("register-password-input");
        password.sendKeys(faker.internet().password(10, 15));
findByXpathClick("//button[@class='q-gray q-fluid q-button-medium q-button male  ']");

//"Kişisel verilerimin işlenmesine yönelik aydınlatma metnini okudum ve anladım." metnini tıkla, metni al, aldigini dogrula
        findByXpathClick("(//div[@class='ty-mgr-2 ty-relative ty-checkbox-container'])[2]");
String  str=findByXpathString("(//div[@class='ty-display-flex ty-color-black ty-font-sm ty-flex-column ty-input-w'])[2]");
           asserTextContainsAssertTrue(str, "Kişisel verilerimin işlenmesine yönelik aydınlatma metnini okudum ve anladım." );

// Uye Ol butonunu tikla
findByXpathClick("//button[@class='q-primary q-fluid q-button-medium q-button submit']");

//"E-POSTA DOĞRULAMA" metninin gorundugunu dogrula
        WebElement eposta=findXpathWebelement("//span[text()='E-POSTA DOĞRULAMA']");
        assertTrueIsDisplayed(eposta);
    }
}
