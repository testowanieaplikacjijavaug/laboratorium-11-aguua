import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SeleniumExtension.class)
public class WaitTest_zad3 {

    FirefoxDriver driver;

    public WaitTest_zad3(FirefoxDriver driver){
        this.driver = driver;
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }

    // Wykorzystano  sendKey z argumentem Keys.ENTER   zamiast click()
    // ponieważ ten sposób wymaga oczekiwania


    //Element jest widoczny i aktywny 	elementToBeClickable(By locator)
    @Test
    public void test_elementToBeClickable(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie", Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("r1-0")));// pierwszy wynik na stronie gotowy do kliknięcia
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }

    //Element jest zaznaczony 	elementToBeSelected(WebElement element)
    @Test
    public void test_elementToBeSelected(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        assertThrows(TimeoutException.class, () ->
                wait.until(ExpectedConditions.elementToBeSelected(By.linkText("not_found"))));
    }

    //Obecność elementu 	presenceOfElementLocated(By locator)
    @Test
    public void test_presenceOfElementLocated(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie", Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("links")));
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }

    //Element zawiera określony tekst 	textToBePresentInElement(By locator, String text)
    @Test
    public void test_textToBePresentInElement(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("badge-link__title")), "Privacy, simplified."));
        assertTrue(driver.getPageSource().contains("DuckDuckGo"));
    }

    //Wartość elementu 	textToBePresentInElementValue(By locator, String text)
    @Test
    public void test_textToBePresentInElementValue(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie", Keys.ENTER);
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("search_form_input"), "testowanie"));
        assertTrue(driver.getCurrentUrl().contains("testowanie"));
    }

    //Tytuł 	titleContains(String title)
    @Test
    public void test_titleContains(){

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie", Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("testowanie"));
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }



}
