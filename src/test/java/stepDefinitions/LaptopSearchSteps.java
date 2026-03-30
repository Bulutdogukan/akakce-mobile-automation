package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;

public class LaptopSearchSteps {
    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();
    ProductPage productPage = new ProductPage();
    String expectedPrice;

    @Given("User opens Akakce app")
    public void user_opens_akakce_app() { }

    @When("User skips login if requested")
    public void user_skips_login_if_requested() {
        homePage.skipLoginIfPresent();
    }

    @When("User searches for {string}")
    public void user_searches_for(String query) {
        homePage.searchFor(query);
    }

    @When("User filters by Bluetooth {string}")
    public void user_filters_by_bluetooth(String version) {
        searchPage.selectBluetoothVersion(version);
    }

    @When("User sorts by {string}")
    public void user_sorts_by(String sortOption) {
        if (sortOption.equals("En Düşük Fiyat")) {
            searchPage.sortByLowestPrice();
        }
    }

    @Then("^User clicks the (\\d+)(?:st|nd|rd|th) real product$")
    public void user_clicks_the_nth_real_product(int index) {
        expectedPrice = searchPage.getNthRealProductPrice(index);
        searchPage.clickNthRealProduct(index);
    }

    // GÜNCELLEME: Feature dosyasındaki buton adını dinamik olarak alır
    @Then("User verifies price and {string} button on detail page")
    public void user_verifies_price_and_button(String buttonName) {
        // Buton doğrulama
        Assert.assertTrue(productPage.isGoToSellerButtonDisplayed(), "HATA: '" + buttonName + "' butonu ekranda yok!");

        // Fiyat doğrulama (Yeni mantıkla)
        Assert.assertTrue(productPage.isPriceMatching(expectedPrice), "HATA: Fiyatlar eşleşmiyor! Beklenen fiyat rakamı: " + expectedPrice);

        System.out.println("⭐⭐⭐ TEST BAŞARIYLA TAMAMLANDI! YEMYEŞİL! ⭐⭐⭐");
    }
}