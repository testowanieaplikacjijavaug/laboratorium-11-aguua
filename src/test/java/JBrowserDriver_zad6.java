import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SeleniumExtension.class)
public class JBrowserDriver_zad6 {

    JBrowserDriver driver = new JBrowserDriver(Settings.builder().
            timezone(Timezone.EUROPE_WARSAW).build());

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }
    @Test
    public void testTitlePage() {
        assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
    }

    @Test
    public void testNotExistingElement(){
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.linkText("not_found"));
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }

}
