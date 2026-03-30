package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage extends BasePage {

    private final By filterBtn = By.xpath("//*[@text='Filtrele']");
    private final By sortBtn = By.xpath("//*[contains(@text, 'Sırala')]");
    private final By sortOptionLowestPrice = By.xpath("//*[@text='En Düşük Fiyat']");
    private final By applyFilterBtn = By.xpath("//*[contains(@text, 'Ürünleri Gör')]");

    // KESİN ÇÖZÜM: Metinle uğraşmıyoruz. ID'sinin içinde "price" geçen tüm TextView'ları listeliyoruz.
    private final By priceLocator = By.xpath("//android.widget.TextView[contains(@resource-id, 'price')]");

    public void selectBluetoothVersion(String version) {
        try { Thread.sleep(4000); } catch (InterruptedException e) {}

        scrollToText("Filtrele");
        click(filterBtn);

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        scrollToText("Bluetooth Versiyonu");
        scrollToText(version);
        click(By.xpath("//*[@text='" + version + "']"));

        click(applyFilterBtn);
    }

    public void sortByLowestPrice() {
        try { Thread.sleep(4000); } catch (InterruptedException e) {}

        waitForVisibility(sortBtn);
        click(sortBtn);

        waitForVisibility(sortOptionLowestPrice);
        click(sortOptionLowestPrice);
    }

    public String getNthRealProductPrice(int targetIndex) {
        // Sıralamadan sonra listelerin tam oturması için kısa mola
        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        List<WebElement> prices = driver.findElements(priceLocator);
        if (prices.size() >= targetIndex) {
            return prices.get(targetIndex - 1).getText();
        }
        return "Fiyat Bulunamadı";
    }

    public void clickNthRealProduct(int targetIndex) {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        List<WebElement> prices = driver.findElements(priceLocator);

        if (prices.size() >= targetIndex) {
            System.out.println("Tıklanan 2. Ürünün Fiyatı: " + prices.get(targetIndex - 1).getText());
            prices.get(targetIndex - 1).click();
        } else {
            throw new RuntimeException("Ekranda yeterli ürün yok! Bulunan fiyat sayısı: " + prices.size());
        }
    }
}