package com.example;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class FirstTest {

    public static void main(String[] args) throws InterruptedException {

        String username = "Yashwanth_200";
        String password = "Yashwanth@187";

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        System.out.println("Title: " + driver.getTitle());

      
        driver.findElement(By.linkText("Register")).click();

        
        driver.findElement(By.id("customer.firstName")).sendKeys("Yashwanth");
        driver.findElement(By.id("customer.lastName")).sendKeys("Umapathy");
        driver.findElement(By.id("customer.address.street")).sendKeys("MKB Nagar");
        driver.findElement(By.id("customer.address.city")).sendKeys("Chennai");
        driver.findElement(By.id("customer.address.state")).sendKeys("Tamil Nadu");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("600039");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("6382113708");
        driver.findElement(By.id("customer.ssn")).sendKeys("123456789");

        driver.findElement(By.id("customer.username")).sendKeys(username);
        driver.findElement(By.id("customer.password")).sendKeys(password);
        driver.findElement(By.id("repeatedPassword")).sendKeys(password);

        
        driver.findElement(By.xpath("//input[@value='Register']")).click();

        
        if (driver.getPageSource().contains("This username already exists")) {

            System.out.println("User already exists. Logging in...");

            driver.get("https://parabank.parasoft.com/parabank/index.htm");

            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.xpath("//input[@value='Log In']")).click();

        } else {

            System.out.println("New User Registered Successfully.");
        }

     
        driver.findElement(By.linkText("Open New Account")).click();

        // Select SAVINGS Account
        WebElement accountType = driver.findElement(By.id("type"));

        Select select = new Select(accountType);
        select.selectByValue("1"); // SAVINGS

        String selectedAccount = select.getFirstSelectedOption().getText();
        System.out.println("Selected Account Type: " + selectedAccount);

                WebElement accountType1 = driver.findElement(By.id("fromAccountId"));

        Select select1 = new Select(accountType1);
        select.selectByValue("1"); // SAVINGS

        String selectedAccount1 = select1.getFirstSelectedOption().getText();
        System.out.println("Selected Account Number: " + selectedAccount1);

        

        
        driver.findElement(By.xpath("//input[@value='Open New Account']")).click();

        
        String accountNumber = driver.findElement(By.id("fromAccountId")).getText();

        System.out.println("Generated Account Number: " + accountNumber);

        driver.findElement(By.xpath("//input[@value='Open New Account']")).click();

        Thread.sleep(3000);

        String heading = driver.findElement(By.xpath("//div[@id='openAccountResult']//h1")).getText();
        
        if(heading.contains("Account Opened")){

            System.out.println("YOUR ACCOUNT IS CREATED SUCESSFULLY");

        }else{

            System.out.println("Account not created");
        }

        driver.findElement(By.xpath("//a[text()='Accounts Overview']")).click();

        List<WebElement> rows = driver.findElements(
                By.xpath("//table[@id='accountTable']/tbody/tr"));

        System.out.println("===== ACCOUNT OVERVIEW =====");

        for (WebElement row : rows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));

            if (cols.size() >= 3) {

                String accountNo = cols.get(0).getText();
                String balance = cols.get(1).getText();
                String availableAmount = cols.get(2).getText();

                System.out.println("Account Number : " + accountNo);
                System.out.println("Balance        : " + balance);
                System.out.println("Available Amt  : " + availableAmount);
                System.out.println("--------------------------------");

            driver.findElement(By.xpath("//a[text()='Transfer Funds']")).click();

            driver.findElement(By.id("amount")).sendKeys("100");

            Select fromAccount = new Select(driver.findElement(By.id("fromAccountId")));
            Select toAccount = new Select(driver.findElement(By.id("toAccountId")));

            Random random = new Random();

            int fromIndex = random.nextInt(fromAccount.getOptions().size());
            fromAccount.selectByIndex(fromIndex);

            int toIndex;
            do {
            toIndex = random.nextInt(toAccount.getOptions().size());
            } while (toIndex == fromIndex);

            toAccount.selectByIndex(toIndex);   

            System.out.println("From Account: " +
            fromAccount.getFirstSelectedOption().getText());

            System.out.println("To Account: " +
            toAccount.getFirstSelectedOption().getText());

            driver.findElement(By.className("button")).click();

            Actions actions = new Actions(driver);

            actions.sendKeys(Keys.BACK_SPACE).perform();


            driver.findElement(By.linkText("Bill Pay")).click();
            driver.findElement(By.xpath("//input[@name='payee.name']")).sendKeys("Current Bill");
            driver.findElement(By.xpath("//input[@name='payee.address.street']")).sendKeys("NO:41/74,MKB Nager");
            driver.findElement(By.xpath("//input[@name='payee.address.city']")).sendKeys("Chennai");
            driver.findElement(By.xpath("//input[@name='payee.address.state']")).sendKeys("TamilNadu");
            driver.findElement(By.xpath("//input[@name='payee.address.zipCode']")).sendKeys("6000039");
            driver.findElement(By.xpath("//input[@name='payee.phoneNumber']")).sendKeys("6382113708");
            driver.findElement(By.xpath("//input[@name='payee.accountNumber']")).sendKeys("60963");
            driver.findElement(By.xpath("//input[@name='verifyAccount']")).sendKeys("60963");
            driver.findElement(By.xpath("//input[@name='amount']")).sendKeys("200");
            
        Select fromAccount1 = new Select(
        driver.findElement(By.xpath("//select[@name='fromAccountId']")));

        List<WebElement> options = fromAccount1.getOptions();

        Random random1 = new Random();
        fromAccount1.selectByIndex(random1.nextInt(options.size()));

        Thread.sleep(2000);
        WebElement sendPaymentBtn = driver.findElement(
        By.xpath("//input[@value='Send Payment']"));

        Actions action1 = new Actions(driver);
        action1.moveToElement(sendPaymentBtn).click().perform();

        

    //Find Transactions Module

    driver.findElement(By.xpath("//a[text()='Find Transactions']")).click();

    Select fromAccount2 = new Select(
    driver.findElement(By.xpath("//select[@id='accountId']")));

    List<WebElement> option = fromAccount2.getOptions();

    Random random2 = new Random();
    fromAccount2.selectByIndex(random2.nextInt(option.size()));
    
    Thread.sleep(1000);
    driver.findElement(By.xpath("(//input[@class='input'])[5]")).sendKeys("500");

    driver.findElement(By.xpath("//button[@id='findByAmount']")).click();


    // Update Contact Info Module

    driver.findElement(By.xpath("//a[text()='Update Contact Info']")).click();

    Thread.sleep(2000);

    WebElement city = driver.findElement(By.id("customer.address.city"));
    city.clear();
    city.sendKeys("Mumbai");

    WebElement state = driver.findElement(By.id("customer.address.state"));
    state.clear();
    state.sendKeys("Maharashtra");

    driver.findElement(By.xpath("//input[@value='Update Profile']")).click();

    //Request Loan Module

     driver.findElement(By.xpath("//a[text()='Request Loan']")).click();
     driver.findElement(By.xpath("//input[@id='amount']")).sendKeys("5000");
     driver.findElement(By.xpath("//input[@id='downPayment']")).sendKeys("1000");

      Select fromAccount3 = new Select(
      driver.findElement(By.xpath("//select[@id='fromAccountId']")));

      List<WebElement> option1 = fromAccount3.getOptions();

      Random random3 = new Random();
      fromAccount3.selectByIndex(random3.nextInt(option1.size()));

      driver.findElement(By.xpath("//input[@class='button']")).click();
    
      driver.findElement(By.xpath("//a[text()='Log Out']")).click();


       driver.quit();
       

            }
        }


        }

        
    
    }