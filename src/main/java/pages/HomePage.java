package pages;

import org.openqa.selenium.By;
import java.util.Map;

public class HomePage extends BasePage {

    private final By searchTab = By.id("com.akakce.akakce:id/searchPage");
    private final By skipLoginBtn = By.id("com.akakce.akakce:id/btn_skip");

    public void skipLoginIfPresent() {
        if (isDisplayed(skipLoginBtn)) {
            click(skipLoginBtn);
        }
    }

    public void searchFor(String text) {
        // 1. Alt menüdeki arama sekmesine tıkla
        click(searchTab);

        // 2. Sayfanın ve klavyenin oturması için 3 saniye bekle
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. ID TAKINTISINI BIRAKIYORUZ: Ekrandaki ilk EditText'i bul (Bu her zaman çalışır)
        By genericInput = By.xpath("//android.widget.EditText");

        // Elementin görünmesini bekle ve üzerine tıkla
        waitForVisibility(genericInput);
        click(genericInput);

        // 4. Yazma işlemi (ADB üzerinden en garantisi)
        try {
            driver.executeScript("mobile: shell", Map.of("command", "input", "args", new String[]{"text", text}));
            System.out.println("Metin yazıldı: " + text);
        } catch (Exception e) {
            // Shell olmazsa type komutu
            driver.executeScript("mobile: type", Map.of("text", text));
        }

        // 5. Klavyeden 'Ara' (Search) butonuna bas (Keyevent 66 = Enter)
        try {
            Thread.sleep(1000);
            driver.executeScript("mobile: performEditorAction", Map.of("action", "search"));
        } catch (Exception e) {
            driver.executeScript("mobile: shell", Map.of("command", "input", "args", new String[]{"keyevent", "66"}));
        }
    }
}