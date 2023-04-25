package test01;

import org.junit.Test;
import utilities.TestBase;

public class C03 extends TestBase {
    @Test
    public void name() {
        driver.get("https://www.trendyol.com/uyelik?cb=https%3A%2F%2Fwww.trendyol.com%2F");

         findByXpathClick("(//div[@class='ty-mgr-2 ty-relative ty-checkbox-container'])[2]");

        //    findByXpathClick("(//div[@class='ty-bg-beige ty-mg-zero ty-input ty-checkbox ty-bordered'])[2]");
    }
}
