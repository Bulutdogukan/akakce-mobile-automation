package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.DriverManager;

import java.time.Duration;
import java.util.Collections;

public abstract class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void sendKeys(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // YENİ: Parmağınla kaydırıyormuş gibi yapan güvenli kaydırma metodu!
    public void scrollDownSafe() {
        int startX = driver.manage().window().getSize().getWidth() / 2;
        int startY = (int) (driver.manage().window().getSize().getHeight() * 0.7);
        int endY = (int) (driver.manage().window().getSize().getHeight() * 0.3);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    // YENİ: Metni bulana kadar yumuşakça aşağı inen akıllı arama
    protected void scrollToText(String text) {
        By locator = By.xpath("//*[@text='" + text + "' or contains(@text, '" + text + "')]");

        for (int i = 0; i < 7; i++) {
            try {
                if (driver.findElement(locator).isDisplayed()) {
                    return; // Bulduk, döngüden çık
                }
            } catch (Exception e) {
                // Ekranda yoksa bir tur kaydır
                scrollDownSafe();
            }
        }
        System.out.println(text + " bulunamadı, kaydırma bitti.");
    }
}