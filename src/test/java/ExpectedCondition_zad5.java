import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class ExpectedCondition_zad5 {

    private FirefoxDriver driver;
    private WebDriverWait wait;

    public ExpectedCondition_zad5(FirefoxDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void test1(){
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        boolean elementExists= wait
                .until(new ExpectedCondition<Boolean>(){
                    @Override
                    public Boolean apply(WebDriver d){
                        return isElementPresent(By.id("best-sellers_block_right"));
                    }
                });
        assertTrue(elementExists);
    }

    @Test
    public void test2(){
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.id("search_query_top")).sendKeys("summer");
        WebElement message = new WebDriverWait(driver, 5)
                .until(new ExpectedCondition<WebElement>(){
                    @Override
                    public WebElement apply(WebDriver d){
                        return d.findElement(By.className("ac_results"));
                    }
                });
        assertTrue(message.getText().toLowerCase().contains("summer"));
    }

    @Test
    public void test3(){
        driver.get("http://automationpractice.com/index.php?controller=contact");
        driver.findElement(By.xpath("//div/button[@class='button btn btn-default button-medium']")).click();
        WebElement message = new WebDriverWait(driver, 5)
                .until(new ExpectedCondition<WebElement>(){
                    @Override
                    public WebElement apply(WebDriver d){
                        return d.findElement(By.xpath("//div[@class='alert alert-danger']"));
                    }
                });
        assertTrue(message.getText().toLowerCase().contains("invalid email address"));
    }

}
