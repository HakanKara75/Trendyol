package test01;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.awt.*;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class C01_Erkek_Saat_SporAyakkabi extends TestBase {
/*
        "https://www.trendyol.com/" sitesine git
         sayfanin handle degerini al
        Erkek menusu ustune git
        saat menüsüne tıkla
        Casio, Daniel Klein ve Alizee saatlerini sec
        //Doliche ve Longines'in secili olmadigini test et
        //ayakkabi/canta menusune git
        //erkek spor ayakkabi'yi yeni pencerede ac
        //onceki pencereye don ve ilk pencerede oldugunu test et
 */

    @Test
    public void test() throws InterruptedException, AWTException {

        //"https://www.trendyol.com/" sitesine git
        driver.get("https://www.trendyol.com/");
        try {
            driver.findElement(By.id("Rating-Review")).click();
            driver.findElement(By.id("rejectAllButton")).click();
        } catch (Exception e) {

        }

        //sayfanin handle degerini al
        String ilkSayfa = driver.getWindowHandle();

        //Erkek menusu ustune git
        Actions actions = new Actions(driver);
        WebElement webElement = driver.findElement(By.xpath("//a[@href='/butik/liste/2/erkek']"));
        actions.moveToElement(webElement).perform();
        threadSleep(2);

        //saat menüsüne tıkla
        driver.findElement(By.xpath("//a[@href='/erkek-saat-x-g2-c34']")).click();

//        Casio, Daniel Klein ve Alizee saatlerini sec
        WebElement casio = findXpathWebelement("//div[text()='Casio']");
        casio.click();


        WebElement saatMenu = findXpathWebelement("//div[@class='ReactVirtualized__Grid__innerScrollContainer']");
        scrollIntoViewByJavaScript(saatMenu);

        WebElement danileKlein = findXpathWebelement("//div[text()='Daniel Klein']");
        danileKlein.click();


        List<WebElement> list = driver.findElements(By.xpath("(//div[@class='ReactVirtualized__Grid__innerScrollContainer'])[2]"));

        for (WebElement w : list) {
            if (w.getText().contains("Alizee")) {
                actions.scrollToElement(w).perform();
                w.click();

                WebElement alizee = w;
                assertTrue(alizee.isSelected());
            }
        }

        threadSleep(2);

        //Doliche ve Longines'in secili olmadigini test et
        for (WebElement w : list) {
            if (w.getText().contains("Doliche")) {
                WebElement doliche = w;
                assertFalse(doliche.isSelected());


            } else if (w.getText().contains("Longines")) {
                WebElement longines = w;
                assertFalse(longines.isSelected());
            }
        }

        threadSleep(2);

        //ayakkabi/canta menusune git
        WebElement ayakkabi = findByIdWebelement("sub-nav-9");
        actions.scrollToElement(ayakkabi).perform();

        //erkek spor ayakkabi'yi yeni pencerede ac
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.trendyol.com/erkek-spor-ayakkabi-x-g2-c109");
        String ikinciSayfa = driver.getWindowHandle();

//onceki pencereye don ve ilk pencerede oldugunu test et
        Set<String> element = driver.getWindowHandles();

        switchToWindow1(0);


        assertFalse(ilkSayfa.equals(ikinciSayfa));

    }
}