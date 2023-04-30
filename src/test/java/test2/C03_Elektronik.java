package test2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class C03_Elektronik extends TestBase {
    @Test
    public void name() {
        extentTest = extentReports.createTest("ExtentTest", "Trendyol Elektronik Menüsü Test Raporu");
        //"https://www.trendyol.com/" sitesine git
        driver.get("https://www.trendyol.com/");
        try {
            driver.findElement(By.id("Rating-Review")).click();
            driver.findElement(By.id("rejectAllButton")).click();
        } catch (Exception e) {

        }
        extentTest.info("https://www.trendyol.com/ sitesine gidildi");

        //sayfanin url'sini al, dogrula
        String ilkSayfa = driver.getCurrentUrl();
        threadSleep(2);
        assertTrueEquals("https://www.trendyol.com/", ilkSayfa);
        extentTest.info("sayfanin urlsi alinip dogrulandi");

        //Elektronik menusu ustune git
        moveToElement("//a[@href='/butik/liste/5/elektronik']");
        extentTest.info("Elektronik menusu ustune gidildi");

        //Cep Telefonu'nu tikla
        findByXpathClick("(//a[@href='/cep-telefonu-x-c103498'])[2]");
        extentTest.info("Cep Telefonu'nuna tiklandi");

        //Soldaki checkboxlardan Apple tikla
        findByXpathClick("//div[text()='Apple']");
        threadSleep(2);
        extentTest.info("Soldaki checkboxlardan Apple tiklandi");

        //urun adlarinda "Apple" oldugunu dogrula
            List<WebElement> apple = driver.findElements(By.xpath("//div[@class='product-down']"));

            for (WebElement w : apple) {
                                System.out.println(w.getText());
                asserTextContainsAssertTrue(w.getText(), "Apple");
            }
             threadSleep(2);
        extentTest.info("urun adlarinda 'Apple' oldugunu dogrulandi");

            //Apple secenegini unchecked yap. Samsung sec
        findByXpathClick("//div[text()='Apple']");
        threadSleep(2);
        findByXpathClick("//div[text()='Samsung']");
        webElementScreenShoot(driver.findElement(By.xpath("//div[text()='Samsung']")));
        extentTest.info("Apple secenegini unchecked yapildi. Samsung secildi");

        //urun adlarinda "Samsung" oldugunu dogrula
        List<WebElement> samsung = driver.findElements(By.xpath("//div[@class='prdct-cntnr-wrppr']"));

        for (WebElement w : samsung) {
            System.out.println(w.getText());
            asserTextContainsAssertTrue(w.getText(), "Samsung");
        }
        threadSleep(2);
        extentTest.info("urun adlarinda 'Samsung' oldugunu dogrulandi");

        //Samsung urun adlarinda "Xiaomi" olmadigini dogrula
        List<WebElement> xiaomi = driver.findElements(By.xpath("//div[@class='prdct-cntnr-wrppr']"));

        for (WebElement w : samsung) {
            System.out.println(w.getText());
            webElementScreenShoot(w);
            assertFalse(!w.getText().contains("Xiaomi") );

        }
        threadSleep(2);
        extentTest.info("Samsung urun adlarinda 'Xiaomi' olmadigi dogrulanamadi");
        extentTest.pass("Test sonlandırıldı");
    }
}