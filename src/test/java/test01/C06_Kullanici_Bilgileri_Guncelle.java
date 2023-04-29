package test01;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;
import java.util.List;

public class C06_Kullanici_Bilgileri_Guncelle extends TestBase {
//"https://www.trendyol.com/Hesabim/KullaniciBilgileri" sayfasina git
    //uyelik bilgilerini gir
//uyelik bilgilerini guncelle
    //soyad gir
    //telefon bilgisi gir
    //gun sec
    //ay sec
    //yil sec
    //Güncelle butonuna tikla



    @Test
    public void test() {

        extentTest = extentReports.createTest("ExtentTest", "Trendyol Kullanici Bilgileri Test Raporu");
        //   "https://www.trendyol.com/Hesabim/KullaniciBilgileri" sayfasina git

        driver.get("https://www.trendyol.com/Hesabim/KullaniciBilgileri");
         extentTest.info("Trendyol sayfasına gidildi");

//uyelik bilgilerini gir
        threadSleep(2);
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("hhhhh@gmail.com");
        webElementSendKeys("//input[@id='login-password-input']", "hhhhh");
        threadSleep(3);
        findByXpathClick("//button[@class='q-primary q-fluid q-button-medium q-button submit']");
        extentTest.info("Uyelik bilgileri girildi");

//uyelik bilgilerini guncelle
        //ad gir
        WebElement ad = findXpathWebelement("//input[@name='firstname']");
        ad.sendKeys("Hakan");
        extentTest.info("uyelik bilgileri guncellendi");

        //soyad gir
        WebElement soyad = findXpathWebelement("//input[@name='lastname']");
        soyad.sendKeys("Kara");
        extentTest.info("soyad girildi");

        //telefon bilgisi gir
        WebElement tel = findXpathWebelement("//input[@name='phone']");
        tel.sendKeys("5543321130");
        scrollToElement("//input[@name='phone']");
        threadSleep(2);
        extentTest.info("telefon bilgisi girildi");

        //gun sec
        WebElement gun = findXpathWebelement("//div[@name='birthday']");
        gun.click();
        scrollToElement("(//button[@class='ty-font-w-semi-bold ty-button ty-bordered ty-transition ty-input-large ty-secondary'])[1]");

        WebElement gunOption = driver.findElement(By.xpath("//div[@class='ty-select-option']/div[text()='1']"));
        gunOption.click();
        threadSleep(2);
        extentTest.info("gun secildi");

        //ay sec
        WebElement ay = driver.findElement(By.xpath("//div[@name='birthmonth']"));
        ay.click();
        threadSleep(2);

        List<WebElement> options = ay.findElements(By.xpath(".//div[@class='ty-select-option']"));
        WebElement option3 = options.get(2);
        option3.click();
        extentTest.info("ay secildi");

        //yil sec
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement dropdown = driver.findElement(By.xpath("//div[@name='birthyear']"));
        dropdown.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("return document.evaluate(\"//div[@class='ty-select-option']//div[text()='1980']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");
        if (element != null) {
            threadSleep(2);
            element.click();
        }
        extentTest.info("yil secildi");

        //Güncelle butonuna tikla
        findByXpathClick("(//button[@class='ty-font-w-semi-bold ty-button ty-bordered ty-transition ty-input-large ty-secondary'])[1]");
        extentTest.info("Güncelle butonuna tiklandi");
                extentTest.pass("Test sonlandırıldı");
    }
}
