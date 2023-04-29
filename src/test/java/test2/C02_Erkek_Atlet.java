package test2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;
import java.util.List;
//"https://www.trendyol.com/" sitesine git
//sayfanin handle degerini al
//arama kutusuna "erkek atlet" yaz
//arama sonuclarindan kac urun ciktigini al
//sol tarafta marka bolumunda Tutku checkbox sec
//secim sonuclarindan kac urun ciktigini al
public class C02_Erkek_Atlet extends TestBase {

    @Test
    public void name() {
        extentTest = extentReports.createTest("ExtentTest", "Trendyol Sözleşme ve Formlar bölümü Test Raporu");
        //"https://www.trendyol.com/" sitesine git
        driver.get("https://www.trendyol.com/");
        try {
            driver.findElement(By.id("Rating-Review")).click();
            driver.findElement(By.id("rejectAllButton")).click();
        } catch (Exception e) {

        }
        extentTest.info("https://www.trendyol.com/ sitesine gidildi");

        //sayfanin handle degerini al
        String ilkSayfa = driver.getWindowHandle();
        threadSleep(2);
        extentTest.info("sayfanin handle degeri alindi");

        //arama kutusuna "erkek atlet" yaz
        WebElement aramaKutusu=findXpathWebelement("//input[@data-testid='suggestion']");
        aramaKutusu.sendKeys("erkek atlet", Keys.ENTER);
        threadSleep(2);
        extentTest.info("arama kutusuna 'erkek atlet' yazildi");

        //arama sonuclarindan kac urun ciktigini al
        String aramaSonucu=findByXpathString("//div[@class='dscrptn']");
        threadSleep(2);
             int arama =Integer.parseInt(aramaSonucu.trim().replaceAll("\\D", "")) ;
        System.out.println(arama);

        //sol tarafta marka bolumunda Tutku checkbox sec
        findByXpathClick("(//div[@class='fltr-item-text'])[1]");

        //secim sonuclarindan kac urun ciktigini al
        String secimSonucu=findByXpathString("//div[@class='dscrptn']//h1");
        threadSleep(2);
        int secim =Integer.parseInt(secimSonucu.trim().replaceAll("\\D", "")) ;
        System.out.println(arama);


    }
}
