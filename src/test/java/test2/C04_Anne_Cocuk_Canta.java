package test2;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class C04_Anne_Cocuk_Canta extends TestBase {

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

        //Anne Cocuk menüsü üstüne git
        moveToElement("//a[@href='/butik/liste/3/anne--cocuk']");
        threadSleep(2);

        //okul cantasini tiklayin
        findByXpathClick("//a[@href='/sr/cocuk-okul-cantasi-x-g3-c104204?gag=2-2']");
        threadSleep(2);

        //Solda Çocuk Cinsiyeti'nde diger checkboxlari boş hale getirip Kız Bebek seçin

        WebElement erkekCocuk=findXpathWebelement(("(//div[@class='fltr-item-text chckd'])[1]"));
        scrollToElement("(//div[@class='fltr-item-text chckd'])[1]");
        findByXpathClick("(//i[@class='fvrt-btn'])[1]"); //popup kapatmak icin

        erkekCocuk.click();
        WebElement kizBebek=findXpathWebelement("//div[text()='Kız Bebek']");
        kizBebek.click();
        threadSleep(2);

        //Kız Bebek seçildigini dogrulayin
        assertTrue(kizBebek.isSelected());
        threadSleep(2);

        //Fiyat menüsünü tiklayin
        findByXpathClick("//div[text()='Fiyat']");
        threadSleep(2);

        //'0 TL - 150 TL secenegini secin
        WebElement fiyat=findXpathWebelement("//div[text()='0 TL - 150 TL secenegini secin']");
        fiyat.click();
        threadSleep(2);

        //'0 TL - 150 TL secenegini secin
        WebElement sec=findXpathWebelement("//div[text()='0 TL - 150 TL']");
        sec.click();


        extentTest.pass("Test sonlandırıldı");
    }
}