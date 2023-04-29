package test2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;
import java.util.List;

public class C01_CheckBox_Secme extends TestBase {
    /*
        "https://www.trendyol.com/" sitesine git
         sayfanin handle degerini al
        Erkek menusu ustune git
 //saat&aksesuar altinda cuzdan menusune tikla
        //tum sayfa sceeenshot al
        //solda marka bolumunde Puma markasini sec
        //en sondaki cuzdani sec
        //sayfanin resmini al
güncellenen html kodlarından locate alma ve checkbox seçme çalışması
     */
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
        extentTest.info("sayfanin handle degeri alindi");

        //Erkek menusu ustune git
        Actions actions = new Actions(driver);
        WebElement webElement = driver.findElement(By.xpath("//a[@href='/butik/liste/2/erkek']"));
        actions.moveToElement(webElement).perform();
        threadSleep(2);
        extentTest.info("Erkek menusu ustune gidildi");

        //saat&aksesuar altinda cuzdan menusune tikla
        findByXpathClick("//a[@href='/erkek-cuzdan-x-g2-c1032']");
        extentTest.info("saat&aksesuar altinda cuzdan menusu tiklandi");

        //tum sayfa sceeenshot al
        tumSayfaScreenShoot();
        extentTest.info("Erkek menusu ustune gidildi");

        //solda marka bolumunde Puma markasini sec
        WebElement puma = driver.findElement(By.xpath("//div[text()='Puma']"));

        try {
            puma.click();
        } catch (Exception e) {
            WebElement pumaElement = driver.findElement(By.linkText("Puma"));
            pumaElement.click();
        }
        extentTest.info("solda marka bolumunde Puma markasi secildi");

        String firstPage=driver.getWindowHandle();

        //en sondaki cuzdani sec
        List<WebElement> pumaCuzdan = driver.findElements(By.xpath("//div[@class='prc-cntnr discountedPriceBox']"));
        int eleman = pumaCuzdan.size();
        threadSleep(2);
        scrollToElement("(//div[@class='prc-cntnr discountedPriceBox'])[" + eleman + "]");


        //popup kapatmak icin
        findByXpathClick("//div[@data-fragment-name='Search']");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement popupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.popup")));

            popupElement.click();
        } catch (TimeoutException e) {
            System.out.println("en sondaki cuzdan secildi");
        }
threadSleep(2);
        findByXpathClick("(//div[@class='prc-cntnr discountedPriceBox'])[" + eleman + "]");
        extentTest.info("Erkek menusu ustune gidildi");

        //sayfanin resmini al
        tumSayfaScreenShoot();
        switchToHhandle(firstPage);
        extentTest.info("sayfanin resmi alindi");

        //urunu sepete ekle
        threadSleep(2);
        findByXpathClick("//div[@class='product-button-container']");
        extentTest.info("urun sepete eklendi");

        //sepete git butonunu tikla
        findByXpathClick("//div[@class='basket-preview-container']");
        extentTest.info("Esepete git butonuna tiklandi");

        //sepetim menusune git
        threadSleep(2);
             findByXpathClick("(//p[@class='link-text'])[3]");
        extentTest.info("sepetim menusune gidildi");

        //Sepeti Onayla butonuna bas
        threadSleep(2);
        String odemeSayfasi=driver.getWindowHandle();
        findByXpathClick("(//a[@class='ty-link-btn-primary'])[1]");
        extentTest.info("Sepeti Onayla butonuna basildi");

        //uyelik bilgilerini gir
        threadSleep(2);
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("gggggggg@gmail.com");
        webElementSendKeys("//input[@id='login-password-input']", "yyyyyyyy");
        threadSleep(3);
        findByXpathClick("//button[@class='q-primary q-fluid q-button-medium q-button submit']");
        extentTest.info("uyelik bilgileri girildi");

        //Kaydet ve Dema Et butonunu tikla
        threadSleep(10);
        findByXpathClick("(//button[@class='ty-primary-btn ty-btn-large'])[1]");
        extentTest.info("Kaydet ve Dema Et butonu tiklandi");

        //Sözleşme ve formlar bölümünün görünürlüğünü test et
        pageDown();
        String actualSozlesme= findByXpathString("//h1[text()='Sözleşmeler ve Formlar']");
        String expectedSozlesme= "Sözleşmeler ve Formlar";
        assertTrueEquals(expectedSozlesme,actualSozlesme);
        extentTest.info("Sözleşme ve formlar bölümünün görünürlüğü test edildi");
        extentTest.pass("Test sonlandırıldı");
    }
}

