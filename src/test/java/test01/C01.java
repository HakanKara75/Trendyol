package test01;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class C01 extends TestBase {
/*
   "https://www.trendyol.com/" sitesine git
Erkek menusu ustune git
 //saat menüsüne tıkla
        //Daniel Klein ve Alizee saatlerini sec, secildigini test et
        //Doliche ve Longines'in secili olmadigini test et
        //ayakkabi/canta menusune git
        //erkek spor ayakkabi'yi yeni pencerede ac
 */

    @Test
    public void test() throws InterruptedException {

        //"https://www.trendyol.com/" sitesine git
        driver.get("https://www.trendyol.com/");
        try {
            driver.findElement(By.id("Rating-Review")).click();
            driver.findElement(By.id("rejectAllButton")).click();
        } catch (Exception e) {

        }

        //Erkek menusu ustune git
        Actions actions = new Actions(driver);
        WebElement webElement = driver.findElement(By.xpath("//a[@href='/butik/liste/2/erkek']"));
        actions.moveToElement(webElement).perform();
        threadSleep(2);

        //saat menüsüne tıkla
        driver.findElement(By.xpath("//a[@href='/erkek-saat-x-g2-c34']")).click();

        //Daniel Klein ve Alizee saatlerini sec, secildigini test et
//        WebElement casio = findXpathWebelement("//div[text()='Casio']");
//        casio.click();
//        threadSleep(2);
//        assertTrue(casio.isSelected());

//        WebElement guess = findXpathWebelement("//div[text()='Guess']");
//        guess.click();
//        threadSleep(2);
//        assertTrue(guess.isSelected());

        WebElement danileKlein = findXpathWebelement("//div[text()='Daniel Klein']");
        danileKlein.click();
        threadSleep(2);
        assertTrue(danileKlein.isSelected());

        List<WebElement> list = driver.findElements(By.xpath("(//div[@class='ReactVirtualized__Grid__innerScrollContainer'])[2]"));

        for (WebElement w : list) {
            if (w.getText().contains("Alizee")) {
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
        WebElement erkek = driver.findElement(By.xpath("//a[@href='/butik/liste/2/erkek']"));
        actions.moveToElement(erkek).perform();
        driver.findElement(By.xpath("(//a[@href='/erkek-spor-ayakkabi-x-g2-c109'])[1]")).click();
        threadSleep(2);

    }
}