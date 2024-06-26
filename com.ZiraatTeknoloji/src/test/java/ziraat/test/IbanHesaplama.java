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

public class IbanHesaplama {
    WebDriver driver;
    By subeKoduXPath = By.xpath("//label[@for='radio-1']");
    By subeXPath = By.xpath("//div[@class='select-box']");
    By musteriNoXPath = By.xpath("//input[@id='musteriNo']");
    By ekNoXPath = By.xpath("//input[@id='ekNo']");
    By hesaplaXPath = By.xpath("//a[@class='btn btn-red iban-calculate']");

    @Test
    public void IbanHesaplama() throws InterruptedException {
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

        driver.get("https://www.ziraatbank.com.tr/tr/hesaplama-araclari/iban-hesaplama");
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");

        //Şube Sıralamasını Seçme
        WebElement subeKoduElement = driver.findElement(subeKoduXPath);
        subeKoduElement.click();
        Thread.sleep(5000);

        //Şube Seçme
        WebElement subeElement = driver.findElement(subeXPath);
        WebElement selectElement = subeElement.findElement(By.tagName("select"));
        subeElement.click();
        Thread.sleep(2000);

        Select dropdown = new Select(selectElement);
        dropdown.selectByVisibleText("ADANA ŞUBESİ-(13)");
        //Tekrar elemente click atma sebebi açılan dropdown kapatılması
        subeElement.click();

        //Müşteri Numarası girilir.
        WebElement musteriNoElement = driver.findElement(musteriNoXPath);
        musteriNoElement.clear();
        musteriNoElement.sendKeys("863739278");
        Thread.sleep(2000);

        //Ekno girilir.
        WebElement ekNoElement = driver.findElement(ekNoXPath);
        ekNoElement.clear();
        ekNoElement.sendKeys("3425");
        js.executeScript("window.scrollBy(0, 200)");
        Thread.sleep(2000);

        //Hesaplama
        Thread.sleep(2000);
        WebElement hesaplaElement = driver.findElement(hesaplaXPath);
        hesaplaElement.click();

        //TODO SAYFA 5 SANİYE SONRA KAPATILIYOR
        Thread.sleep(5000);
        driver.quit();

    }
}
