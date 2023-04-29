package test01;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.util.Set;

public class C04_Sepetim extends TestBase {
    //   "https://www.trendyol.com/" sitesine git
    //Giris menusu ustune git
    //menunun acildigini dogrula
    //uyelik bilgilerini gir
    //anne&cocuk menusu ustune git
    //bebek takimlarini tikla
    //ilk urunu tikla
    //acilan pencerede urunu sepete ekle
    //sepete tikla
    //sepette eklenen urunden 1 tane oldugunu dogrula

    @Test
    public void name() {
        extentTest = extentReports.createTest("ExtentTest", "Trendyol Uyelik Test Raporu");
        //   "https://www.trendyol.com/" sitesine git
        driver.get("https://www.trendyol.com/");
        extentTest.info("Trendyol sayfasına gidildi");
        try {
            driver.findElement(By.id("Rating-Review")).click();
            driver.findElement(By.id("rejectAllButton")).click();
        } catch (Exception e) {

        }

        //Giris menusu ustune git
        moveToElement("//p[text()='Giriş Yap']");
        extentTest.info("Giris menusu ustune gidildi ");

        //menunun acildigini dogrula
        WebElement menu = findXpathWebelement("//div[@class='login-button']");
        assertTrueIsDisplayed(menu);
        menu.click();
        extentTest.info("menunun acildigi dogrulandi");

        //uyelik bilgilerini gir
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("fffffff@gmail.com");
        webElementSendKeys("//input[@id='login-password-input']", ",ff3453");
        threadSleep(3);
        findByXpathClick("//button[@class='q-primary q-fluid q-button-medium q-button submit']");
        extentTest.info("Uyelik bilgileri girildi");

        //anne&cocuk menusu ustune git
        threadSleep(2);
        moveToElement("//a[text()='Anne & Çocuk']");
        extentTest.info("anne&cocuk menusu ustune gidildi");

        //bebek takimlarini tikla
        findByXpathClick("//a[@href='/bebek-takimlari-x-c103727']");
        extentTest.info("bebek takimlari tiklandi");

        //ilk urunu tikla
        findByXpathClick("(//div[@class='prc-cntnr discountedPriceBox'])[1]");
        extentTest.info("ilk urun tiklandi");
        String firstElement=driver.getWindowHandle();

        //acilan pencerede urunu sepete ekle
        Set<String> set=driver.getWindowHandles();
        for (String str: set){
            if (!str.equals(firstElement)){
                driver.switchTo().window(str);
            }

        }
        findByXpathClick("//button[@component-id='1']");
        extentTest.info("acilan pencerede urunu sepete eklendi");

        //sepete tikla
        findByXpathClick("//a[@class='link account-basket']");
        extentTest.info("sepete tiklandi");

        //sepette eklenen urunden 1 tane oldugunu dogrula
        String firsProd=findByXpathString("//input[@value='1']");
        asserTextContainsAssertTrue("1", firsProd);
        extentTest.info("isepette eklenen urunden 1 tane oldugunu dogrulandi");
        extentTest.pass("Test sonlandırıldı");
    }
}
