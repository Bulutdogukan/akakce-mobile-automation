package pages;

import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    // Hem Ürüne Git hem Satıcıya Git ihtimallerini kapsar
    private final By goToProductBtn = By.xpath("//*[contains(@text, 'Ürüne Git') or contains(@text, 'Satıcıya Git')]");

    public boolean isGoToSellerButtonDisplayed() {
        // Ürün kartı genişleme animasyonunun bitmesi için mola
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        return isDisplayed(goToProductBtn);
    }

    // Fiyat Eşleştirme (Boşluk/Format hatalarını yoksayan mucize çözüm)
    public boolean isPriceMatching(String expectedPrice) {
        // expectedPrice "19.999,00 TL" olarak gelir. Biz bunu boşluktan bölüp sadece "19.999,00" rakamını alıyoruz.
        String numericPrice = expectedPrice.split(" ")[0];

        // Ekranda içinde bu rakamların geçtiği bir metin var mı diye bakıyoruz
        By dynamicPriceLocator = By.xpath("//android.widget.TextView[contains(@text, '" + numericPrice + "')]");
        return isDisplayed(dynamicPriceLocator);
    }
}