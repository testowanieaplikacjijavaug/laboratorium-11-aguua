import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SeleniumExtension.class)
public class BrowserTest_zad1 {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testSource(){
        String source = driver.getPageSource();
        assertTrue(source.contains("DuckDuckGo"));
    }

    @Test
    public void testClick(){
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        driver.findElement(By.id("search_button_homepage")).click();
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }

    //Czy istnieje inna metoda na kliknięcie niż click()?

    //submit
    @Test
    public void testSubmit()throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        driver.findElement(By.id("search_button_homepage")).submit();
        wait.until(ExpectedConditions.titleIs("testowanie at DuckDuckGo"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("links")));
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }
    // sendKey z argumentem Keys.ENTER
    @Test
    public void testPressEnter() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie", Keys.ENTER);
        wait.until(ExpectedConditions.titleIs("testowanie at DuckDuckGo"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("links")));
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }

    // użycie akcji
    @Test
    public void testClickWithAction(){
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        WebElement searchButton = driver.findElement(By.id("search_button_homepage"));
        Dimension buttonSize = searchButton.getSize();
        Point buttonLocation = searchButton.getLocation();
        Actions act = new Actions(driver);
        act.moveByOffset(
                buttonLocation.x + buttonSize.width/2,
                buttonLocation.y + buttonSize.height/2)
                .click().build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("testowanie at DuckDuckGo"));
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }

    //Jak wejść w pierwszy i trzeci otrzymany wynik?

    @Test
    public void testResultId(){
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        driver.findElement(By.id("search_button_homepage")).click();
        driver.findElement(By.id("r1-0")).click(); // pierwszy element
        String title1 = driver.getTitle();
        driver.navigate().back();
        driver.findElement(By.id("r1-2")).click(); // trzeci element
        String title3 = driver.getTitle();
        assertNotEquals(title1, title3);
    }

    @Test
    public void testResultList() {
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        driver.findElement(By.id("search_button_homepage")).click();
        List<WebElement> results = driver.findElements(By.className("result__a"));
        String title1 = results.get(0).getAttribute("text"); // pierwszy element
        String title3 = results.get(2).getAttribute("text"); // trzeci element
        assertNotEquals(title1, title3);
    }

    //Co w przypadku kiedy nie znajdziemy szukanego elementu, który wyszukujemy na stronie?

    // zwracany wyjątek
    @Test
    public void testNotExistingElement() {
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.linkText("not_found"));
        });
    }

}
