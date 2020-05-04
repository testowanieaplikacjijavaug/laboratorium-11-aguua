import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SeleniumExtension.class)
public class HtmlUnitDriver_zad6 {

    @Test
    public void testTitlePage(HtmlUnitDriver driver) {
        driver.get("https://duckduckgo.com/");
        assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
    }

    @Test
    public void testClick(HtmlUnitDriver driver){
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie");
        driver.findElement(By.id("search_button_homepage")).click();
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }

    @Test
    public void testNotExistingElement(HtmlUnitDriver driver){
        driver.get("https://duckduckgo.com/");
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.linkText("not_found"));
        });
    }
    @Test
    public void testPressEnter(HtmlUnitDriver driver){
        driver.get("https://duckduckgo.com/");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("testowanie", Keys.ENTER);
        wait.until(ExpectedConditions.titleIs("testowanie at DuckDuckGo"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("links")));
        assertEquals("testowanie at DuckDuckGo", driver.getTitle());
    }


}


