package ziraat.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class MevduatHesaplama {
    WebDriver driver;

    By dovizCinsiXPath = By.xpath("//div[@class='select-box']");
    By vadeSuresiXPath = By.xpath("//input[@name='deposit-calc-vade']");
    By tutarXPath = By.xpath("//input[@name='tutar']");
    By hesaplaXPath = By.xpath("//a[@class='btn btn-red btn-save']");

    @Test
    public void mevduatHesaplama() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        //.exe olmalı for windows
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("disable-plugins");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("disable-extensions");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        chromeOptions.merge(desiredCapabilities);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        driver.get("https://www.ziraatbank.com.tr/tr/hesaplama-araclari/mevduat-getirisi-hesaplama");
        //WebElement tuketiciKrediElement = driver.findElement(mevduatHesaplamaXpath);
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");


        //USD Döviz Cinsini Seçme
        WebElement dovizCinsiElement = driver.findElement(dovizCinsiXPath);
        dovizCinsiElement.click();
        WebElement selectElement = dovizCinsiElement.findElement(By.tagName("select"));
        Select dropdown = new Select(selectElement);
        dropdown.selectByVisibleText("USD");
        //Tekrar elemente click atma sebebi açılan dropdown kapatılması
        Thread.sleep(1000);
        dovizCinsiElement.click();

        Thread.sleep(2000);

        //Vade süresini clear yap içine yaz
        WebElement vadeSüresiElement = driver.findElement(vadeSuresiXPath);
        vadeSüresiElement.clear();
        vadeSüresiElement.sendKeys("46");

        //Tutar değeri girilir.
        WebElement tutarElement = driver.findElement(tutarXPath);
        tutarElement.clear();
        tutarElement.sendKeys("80000");

        js.executeScript("window.scrollBy(0, 200)");
        //Hesaplama
        Thread.sleep(2000);
        WebElement hesaplaElement = driver.findElement(hesaplaXPath);
        hesaplaElement.click();


        //TODO SAYFA 5 SANİYE SONRA KAPATILIYOR
        Thread.sleep(5000);
        driver.quit();
    }
}
