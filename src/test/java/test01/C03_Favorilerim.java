package test01;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import static org.junit.Assert.assertTrue;

public class C03_Favorilerim extends TestBase {
    //   "https://www.trendyol.com/" sitesine git
    //Giris menusu ustune git
    //menunun acildigini dogrula
    //uyelik bilgilerini gir
    //Kadın menusu ustune git
    //Elbise menusunu tikla
    //ilk uc elbiseyi favori olarak tikla
    //Favorilerim menusunu tikla
    //eklenen uc urunun favorilerim arasinda oldugunu dogrula
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
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("aaa@gmail.com");
        webElementSendKeys("//input[@id='login-password-input']", ",aaaa");
        threadSleep(3);
        findByXpathClick("//button[@class='q-primary q-fluid q-button-medium q-button submit']");
        extentTest.info("Uyelik bilgileri girildi");

        //Kadın menusu ustune git
        threadSleep(2);
        moveToElement("//a[@href='/butik/liste/1/kadin']");
        extentTest.info("Kadın menusu ustune gidildi");

        //Elbise menusunu tikla
        findByXpathClick("(//a[@href='/elbise-x-c56'])[1]");
        extentTest.info("Elbise menusu tiklandi");

        //ilk uc elbiseyi favori olarak tikla
        scrollToElement("(//div[@class='price-promotion-container'])[2]");

        findByXpathClick("(//i[@class='fvrt-btn'])[1]");
        String birinciElbise = findByXpathString("//span[text()='Ekru Dokuma Düğme Detaylı Mini Gömlek Elbise TWOSS22EL2970']");
        threadSleep(2);

        findByXpathClick("(//i[@class='fvrt-btn'])[2]");
        String ikinciElbise = findByXpathString("//span[text()='Siyah Midi Boy Kalem Elbise']");
        threadSleep(2);

        findByXpathClick("(//i[@class='fvrt-btn'])[3]");
        String ucuncuElbise = findByXpathString("//span[text()='Beyaz Keten Kumaş Vual Astar Elbise']");
              threadSleep(2);

        extentTest.info("ilk uc elbiseyi favori olarak tiklandi");

        //Favorilerim menusunu tikla
        scrollTopByJavaScript();
        findByXpathClick("//p[text()='Favorilerim']");
        extentTest.info("Favorilerim menusu tiklandi");

        //eklenen uc urunun favorilerim arasinda oldugunu dogrula
        String expectedBirinciElbise = "Ekru Dokuma Düğme Detaylı Mini Gömlek Elbise TWOSS22EL2970";
        String expectedIkinciElbise = "Siyah Midi Boy Kalem Elbise";
        String expectedUcuncuElbise = "Beyaz Keten Kumaş Vual Astar Elbise";

        assertTrueEquals(expectedBirinciElbise, birinciElbise);
        assertTrueEquals(expectedIkinciElbise, ikinciElbise);
        assertTrueEquals(expectedUcuncuElbise, ucuncuElbise);

                extentTest.pass("eklenen uc urunun favorilerim arasinda oldugu dogrulandı");
        extentTest.pass("Test sonlandırıldı");
    }
}
