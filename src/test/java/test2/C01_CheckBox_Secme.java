package test2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

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

     */
    @Test
    public void name() {
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

        //saat&aksesuar altinda cuzdan menusune tikla
        findByXpathClick("//a[@href='/erkek-cuzdan-x-g2-c1032']");

        //tum sayfa sceeenshot al
        tumSayfaScreenShoot();

        //solda marka bolumunde Puma markasini sec

        WebElement puma = driver.findElement(By.xpath("//div[text()='Puma']"));

        try {
            puma.click();
        } catch (Exception e) {
            WebElement pumaElement = driver.findElement(By.linkText("Puma"));
            pumaElement.click();
        }

        //en sondaki cuzdani sec
        List<WebElement> pumaCuzdan = driver.findElements(By.xpath("//div[@class='prc-cntnr discountedPriceBox']"));
        int eleman = pumaCuzdan.size();
        threadSleep(3);
        System.out.println(eleman);
        findByXpathClick("(//div[@class='prc-cntnr discountedPriceBox'])[" + eleman + "]");

        //sayfanin resmini al
        tumSayfaScreenShoot();
    }
}

