package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

   public abstract class TestBase {
        //TestBase class'indan obje olusturmanin onune gecilmesi icin abstract yapilabilir
//Orn: TestBase base=new TestBase();
//Bu class'a extend ettigimiz test classlarinda ulasabiliriz.
        protected static WebDriver driver;
        protected static ExtentReports extentReports; //Extent Report icin: Raporlamayı başlatır
        protected static ExtentHtmlReporter extentHtmlReporter;//Extent Report icinÇ Raporu HTML formatında düzenler
        protected static ExtentTest extentTest;//Extent Report icin: Tüm test aşamalarında extentTest objesi ile bilgi ekleriz

        @Before
        public void setUp() throws Exception {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            //Extent Report icin asagisi
            //----------------------------------------------------------------------------------------
            extentReports = new ExtentReports();
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
            extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
            extentReports.attachReporter(extentHtmlReporter);
            //Raporda gözükmesini istediğimiz bilgiler için
            extentReports.setSystemInfo("Browser", "Chrome");
            extentReports.setSystemInfo("Tester", "Hakan");
            extentHtmlReporter.config().setDocumentTitle("Extent Report");
            extentHtmlReporter.config().setReportName("Smoke Test Raporu");
            extentTest = extentReports.createTest("ExtentTest", "Test Raporu");

        }

        @After
        public void tearDown() throws Exception {
            extentReports.flush(); //Extent Report icin
            Thread.sleep(3000);
            driver.quit();
        }

        public static void threadSleep(int sleep) {
            try {
                Thread.sleep(sleep * 1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public WebElement findXpathWebelement(String str) {
            WebElement w = driver.findElement(By.xpath(str));
            return w;
        }

        public void asserTextContainsAssertTrue(String str, String atr) {
            assertTrue(str.contains(atr));
        }

        public void switchAlertAccept() {
            driver.switchTo().alert().accept();
        }

        public void switchAlertDismiss() {
            driver.switchTo().alert().dismiss();
        }

        public void switchAlertSendKey(String str) {
            driver.switchTo().alert().sendKeys(str);
        }

        public String findByXpathString(String str) {
            String location = driver.findElement(By.xpath(str)).getText();
            return location;
        }

       /**
        @param str elementi xpath verilerek bu metot ile tiklanir
        */
        public void findByXpathClick(String str) {
            driver.findElement(By.xpath(str)).click();
        }
       /**
        @param str elementi id verilerek bu metot ile tiklanir
        */
        public void findByIdClick(String str) {
            driver.findElement(By.id(str)).click();
        }

       /** bu metot girilen id locati ile webelement olusturur
        * @param str olarak id locati girilmeli
        */
       public static WebElement findByIdWebelement(String str) {
           WebElement w = driver.findElement(By.id(str));
           return w;
       }

       /**
        bu metot ekrani bir masue tekeri donmesi kadar asagi kaydirir
        */
       public static void pageDown() {
           Actions actions = new Actions(driver);
           actions.sendKeys(Keys.PAGE_DOWN).perform();
       }

       /**
        bu metot ekrani bir masue tekeri donmesi kadar yukari kaydirir
        */
        public void pageUp() {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.PAGE_UP).perform();
        }

       /**
        bu metot ekrani bir tik asagi kaydirir
        */
        public void arrowDown() {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }

       /**
        bu metot ekrani bir tik yukari kaydirir
        */
        public void arrowUp() {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_UP).perform();
        }

       /** bu metot webelementin gorunur oldugunu dogrulamak icindir
        @param  webElement girilecek olan webelementdir
        */
        public void assertDisplayedWebelement(WebElement webElement) {
            assertTrue(webElement.isDisplayed());
        }

       /**bu metot sayfayi girilen string degerindeki elemente goturur
        * @param str girilmesi gereken elementin locatinin string halidir
        */
        public void scrollToElement(String str) {
            WebElement bottom = driver.findElement(By.xpath(str));
            Actions actions = new Actions(driver);
            actions.scrollToElement(bottom).perform();
            //bu kod locati alinan elemana kadar sayfayi asagi goturur
        }

       /**
        * @param element girilmesi gereken locatidir
       bu metot sayfayi girilen elemente goturur
        */
       public static void scrollToElementWithWebElement(WebElement element) {
           WebElement bottom = element;
           Actions actions = new Actions(driver);
           actions.scrollToElement(bottom).perform();
       }

       /**
        bu metot ile tum sayfanin screenshot i alinir
        */
        public static void tumSayfaScreenShoot() {
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String dosyaYolu = "TestOutput/screenshot" + tarih + ".png";
            TakesScreenshot ts = (TakesScreenshot) driver;
            try {
                FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

       /**
        bu metot ile webelementin screenshot i alinir
        @param element girilmesi gereken locate dir
        */
        public static void webElementScreenShoot(WebElement element) {
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String dosyaYolu = "TestOutput/webElementScreenshot" + tarih + ".png";
            try {
                FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

       /**
        bu metot ile acilan ilk pencereye donulur
        @param sayi girilmesi gereken gecilecek pencerenin indexidir
        */
        public static void switchToWindow(int sayi) {
            List<String> tumWindowHandles = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tumWindowHandles.get(sayi));
        }

       /**
        bu metot ile acilan pencereye gecilir
        @param sayi girilmesi gereken gecilecek pencerenin indexidir
        */
        public static void window(int sayi) {
            driver.switchTo().window(driver.getWindowHandles().toArray()[sayi].toString());
        }

       /**
        bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
        @param webElement girilmesi gereken locate dir
        */
        public void clickByJavaScript(WebElement webElement){
            JavascriptExecutor jse= (JavascriptExecutor) driver;

            jse.executeScript("arguments[0].click();", webElement);

        }

        public void scrollIntoViewByJavaScript(WebElement webElement){
            JavascriptExecutor jse=(JavascriptExecutor) driver;//Casting
            jse.executeScript("arguments[0].scrollIntoView(true);",webElement);

        }

       /**
        bu metot javascript kodu ile sayfayi en alta goturur
        */
        public void scrollEndByJavaScript(){
            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }

       /**
        * bu metot javascript kodu ile elemente string gonderir(java sendkey() ile ayni)
        @param webElement girilmesi gereken locate dir
        @param string locate gonderilecek olan deger
        */
       public static void sendKeyWithJavaScript(String string, WebElement webElement) {
           JavascriptExecutor jse = (JavascriptExecutor) driver;//Casting
           jse.executeScript("arguments[0].value = '"+string+"';", webElement);

       }

       /**
        bu metot javascript kodu ile sayfayi en yukari goturur
        */
        public void scrollTopByJavaScript(){
            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        }

        public void typeWithJavaScript(WebElement webElement, String str){
            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('value', '"+str+"')", webElement);
        }

       /**
        @param id girilmesi gereken id degeri
        @param attributeName gonderilmesi gereken attribute ismi
        bu metot ile girilen attribute degerleri ile texti alabilirim
        */
        public String getValueByJavaScript(String id, String attributeName){
            JavascriptExecutor js= (JavascriptExecutor) driver;
            String string= js.executeScript("return document.getElementById('"+id+"')."+attributeName+"").toString();
            System.out.println(string);
            return string;
            //        NOT: document.querySelector("p").value;  -> TAG KULLANILABILIR
//             document.querySelector(".example").value; -> CSS DEGERI KULLANILABILIR
//             document.querySelector("#example").value; -> CSS DEGERI KULLANILABILIR
        }

       /**
        bu metot ile mause element ustunde bekletilir
        @param str xpath olarak girilmesi gereken locate'in text halidir
        */
public void moveToElement(String str){
    WebElement webElement = driver.findElement(By.xpath(str));
    Actions actions = new Actions(driver);
       actions.moveToElement(webElement).perform();


}
       /** bu metot webelementin gorunur oldugunu dogrulamak icindir
        @param  webElement girilecek olan webelementdir
        */
public void assertTrueIsDisplayed(WebElement webElement){
    Assert.assertTrue(webElement.isDisplayed());
}
       /** Bu metot iki string degerin birbirine equal olup olmadigini dogrular
        @param str girilecek 1. metindir
        @param str1 girilecek 2. metindir
        */
       public void assertTrueEquals(String str, String str1){
           Assert.assertTrue(str.equals(str1));
       }

       /**Bu metot bir webelementin secili olup olmadigini dogrular
        *  @param webElement girilecek webelement dir.
        */
       public void assertTrueIsSelected(WebElement webElement){
           Assert.assertTrue(webElement.isSelected());
       }

       /** Bu metot xpath ile alinan locate sendkey gonderir
        * @param xPath buraya elementin xpath locati verilecek
        * @param sendKeys buraya elemente dongerilecek metin girilecek
        */
       public void webElementSendKeys(String xPath, String sendKeys){
          driver.findElement(By.xpath(xPath)).sendKeys(sendKeys);

       }

       /**
        * Bu metot ile pencere degistirilir. ikinci pencereye gecilir.
        * @param firstPage parametresine ilk pencerenin handle degeri girilir.
        */
       public void switchToHhandle( String firstPage){
             firstPage=driver.getWindowHandle();
            Set<String> pagesHandles=driver.getWindowHandles();
            for (String str: pagesHandles){
                if(!str.equals(firstPage)){
                    driver.switchTo().window(str);

                }
            }

       }
    }


