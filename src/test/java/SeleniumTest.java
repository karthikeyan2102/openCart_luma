import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    public static  WebDriver driver = new ChromeDriver();

    public static void dropdown(String xpath1,String xpath2){
        click(xpath1);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        click(xpath2);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
   public static void click(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }

    //created a method to enter the desired value into the text
   public static void entervalue(String xpath,String value) {
       WebElement text = driver.findElement(By.xpath(xpath));
       text.click();
       text.sendKeys(value);
   }

   @Test
 public static void BagCheckout () throws IOException, InterruptedException {
       System.setProperty("webdriver.chrome.driver","/Users/karthikeyan/IdeaProjects/openCart/src/test/resources/chromedriver");

       //makes the chrome wait for 5 seconds and maximize the window for better view
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      driver.manage().window().maximize();

      //Goes into the website
      driver.get("https://magento.softwaretestingboard.com/");
      WebElement gear = driver.findElement(By.id("ui-id-6"));
       Actions actions = new Actions(driver);
       // hover action
       actions.moveToElement(gear).perform();
       driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


       // Locate the dropdown option element and click it
       WebElement option = driver.findElement(By.xpath("//*[@id=\"ui-id-25\"]"));
       option.click();

       //scroll down to increase the page limit

       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
       dropdown("/html/body/div[2]/main/div[3]/div[1]/div[4]/div[3]/div/select","/html/body/div[2]/main/div[3]/div[1]/div[4]/div[3]/div/select/option[2]");
       driver.navigate().refresh();

       //clicking the first item and adding it to the cart
       click("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a/span/span/img");
       click("//input[@id='qty']");
       WebElement quantity = driver.findElement(By.xpath("//input[@id='qty']"));
       quantity.clear();   // clear the existing quantity
       quantity.sendKeys("10");
       click("//button[@id='product-addtocart-button']");


       //waiting for the cart to completely load


       Thread.sleep(7000);


       //clicking the cart icon
       click("/html/body/div[2]/header/div[2]/div[1]/a");
       click("//a[@class='action viewcart']");


       click("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[1]/ul/li[1]/button/span");

       //waiting for the page to completely load

       Thread.sleep(4000);

       //Entering the values/ filling up the form

       entervalue("//*[@id='customer-email']","karthi14srini@gmail.com");  //Email
       entervalue("//input[@name='firstname']","karthikeyan");
       entervalue("//input[@name='lastname']","Srinivasan");
       entervalue("//input[@name='company']","Appscrip");
       entervalue("//input[@name='street[0]']","41/75");
       entervalue("//input[@name='city']","Chennai");
       dropdown("(//select[@name='country_id'])[1]","(//option[@data-title='India'])[1]");
       dropdown("(//select[@name='region_id'])[1]","(//option[@data-title='Tamil Nadu'])");
       entervalue("//input[@name='postcode']","123456");
       Thread.sleep(3000);
       entervalue("//input[@name='telephone']","7708920269");
       click("//*[@id=\"shipping-method-buttons-container\"]/div/button");
       Thread.sleep(6000);
       click("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button/span");
       Thread.sleep(8000);
       driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span")).isDisplayed();











   }
}

//String path = ".\\src\\test\\java\\xls\\baglist.xlsx";
//       XlUtility xlutil = new XlUtility(path);
//
//
//       //headers
//       xlutil.setCellData("Sheet1",0,0,"Product name");
//       xlutil.setCellData("Sheet1",0,1,"Product price");
//
//       WebElement list = driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div[3]/ol"));
//       int rows;
//       rows = list.findElements(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li")).size();
//
//
//       for(int r=1;r <= rows;r++){
//         String prodName =   list.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li["+r+"]/div/div/strong/a")).getText();
//         String ProdPrice = list.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li["+r+"]/div/div/div[2]/span/span/span")).getText();
//
//         System.out.println(prodName+ProdPrice);
//
//         xlutil.setCellData("Sheet1",r,0,prodName);
//         xlutil.setCellData("Sheet1",r,1,ProdPrice);
//
//       }
//
//       System.out.println("data got updated");