package test01;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.TestBase;

public class C06 extends TestBase {



    @Test
    public void test(){


driver.get("https://www.trendyol.com/Hesabim/KullaniciBilgileri");;

//uyelik bilgilerini gir
        threadSleep(2);
            driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("hakanbatirhan@gmail.com");
            webElementSendKeys("//input[@id='login-password-input']", ",bbbbbb");
            threadSleep(3);
            findByXpathClick("//button[@class='q-primary q-fluid q-button-medium q-button submit']");
            extentTest.info("Uyelik bilgileri girildi");

//uyelik bilgilerini guncelle
        //ad
        WebElement ad=findXpathWebelement("//input[@name='firstname']");
        ad.sendKeys("Hakan");

        //soyad
        WebElement soyad=findXpathWebelement("//input[@name='lastname']");
        soyad.sendKeys("Kara");

        WebElement tel=findXpathWebelement("//input[@name='phone']");
        soyad.sendKeys("5543321130");

        WebElement gun=findXpathWebelement("//div[@name='birthday']");

        Select select=new Select(gun);
        threadSleep(2);
        select.selectByIndex(1);



    }
}
