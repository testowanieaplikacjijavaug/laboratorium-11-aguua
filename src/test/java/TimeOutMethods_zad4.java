import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TimeOutMethods_zad4 {
    private static WebDriver driver_page_timeout;
    private static WebDriver driver_script_timeout;

    @BeforeAll
    public static void setUpDriver(){
        WebDriverManager.firefoxdriver().setup();
        driver_page_timeout = new FirefoxDriver();
        driver_script_timeout = new FirefoxDriver();
        //daje 10 sekund na załadowanie strony
        driver_page_timeout.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //daje 10 sekund na wykonanie skryptu
        driver_page_timeout.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver_page_timeout.get("https://duckduckgo.com/");
        driver_script_timeout.get("https://duckduckgo.com/");
    }


    @Test
    public void testClick1(){
        driver_page_timeout.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        driver_page_timeout.findElement(By.id("search_button_homepage")).click();
        assertEquals("testowanie at DuckDuckGo", driver_page_timeout.getTitle());
    }

    @Test
    public void testClick2(){
        driver_script_timeout.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        driver_script_timeout.findElement(By.id("search_button_homepage")).click();
        assertEquals("testowanie at DuckDuckGo", driver_script_timeout.getTitle());
    }


}
