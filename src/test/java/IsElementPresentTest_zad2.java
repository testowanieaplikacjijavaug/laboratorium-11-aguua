import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


@ExtendWith(SeleniumExtension.class)
public class IsElementPresentTest_zad2 {

    private ChromeDriver driver;

    public IsElementPresentTest_zad2(ChromeDriver driver) {
        this.driver = driver;
    }


    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @BeforeEach
    public void setUp() {
        driver.get("https://duckduckgo.com/");
    }

    @Test
    public void testSearchInput(){
        boolean isPresent = isElementPresent(By.id("search_form_input_homepage"));
        assertTrue(isPresent);
    }

    @Test
    public void testSubmitButton(){
        boolean isPresent = isElementPresent(By.xpath("//input[@type='submit']"));
        assertTrue(isPresent);
    }

    @Test
    public void testClassName(){
        boolean isPresent = isElementPresent(By.className("js-search-form"));
        assertTrue(isPresent);
    }

    @Test
    public void testCssSelector(){
        boolean isPresent = isElementPresent(By.cssSelector("input"));
        assertTrue(isPresent);
    }

    @Test
    public void testNotExistingElement(){
        //driver.findElement(By.id("no_such_id_on_website"));
        boolean isPresent = isElementPresent(By.id("no_such_id_on_website"));
        assertFalse(isPresent);
    }



}
