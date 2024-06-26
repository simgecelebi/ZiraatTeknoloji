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

public class TasitKredisiHesaplama {
    WebDriver driver;

    By tuketiciKrediXpath = By.xpath("//*[@id=\"ctl00_PlaceHolderMain_ctl04__ControlWrapper_RichHtmlField\"]/div[1]/a");
    By tasitKredisiXPath = By.xpath("//div[@class='list active']//i[@class='icon-tasit']");
    By tasitKrediSecXPath = By.xpath("//div[@class='form-item select-box col-xs-12']");
    By tutarXPath = By.id("calc-tutar");
    By vadeXPath = By.id("calc-vade");
    By hesaplaXPath = By.xpath("//a[@class='btn btn-red calculation-btn']");

    @Test
    public void homeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");

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

        driver.get("https://www.ziraatbank.com.tr/tr/hesaplama-araclari/kredi-hesaplama/tuketici-kredisi");
        WebElement tuketiciKrediElement = driver.findElement(tuketiciKrediXpath);
        Thread.sleep(3000);


        // YENİ EKRAN TESTİ
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Sayfayı 200 piksel (y ekseni) aşağı kaydır
        js.executeScript("window.scrollBy(0, 200)");
        tuketiciKrediElement.click();

        // Taşıt kredisine tıkla
        driver.findElement(tasitKredisiXPath).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0, 400)");
        Thread.sleep(2000);

        WebElement tasitKrediElement = driver.findElement(tasitKrediSecXPath);
        WebElement selectElement = tasitKrediElement.findElement(By.tagName("select"));
        tasitKrediElement.click();

        //Taşıt Kredisi Ürün Paketi seçmek için işlem
        //TODO TODO TODO
        Select dropdown = new Select(selectElement);
        dropdown.selectByVisibleText("Taşıt Kredisi Ürün Paketi");
        //Tekrar elemente click atma sebebi açılan dropdown kapatılması
        tasitKrediElement.click();

        // tutarElement input al clear yap içine yaz
        WebElement tutarElement = driver.findElement(tutarXPath);
        tutarElement.clear();
        tutarElement.sendKeys("100000");

        // vadeElement input al clear yap içine yaz
        WebElement vadeElement = driver.findElement(vadeXPath);
        vadeElement.clear();
        vadeElement.sendKeys("24");

        Thread.sleep(2000);
        WebElement hesaplaElement = driver.findElement(hesaplaXPath);
        hesaplaElement.click();

        //TODO SAYFA 5 SANİYE SONRA KAPATILIYOR
        Thread.sleep(5000);
        driver.quit();

    }



}
