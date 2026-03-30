Feature: Laptop Search and Detail Verification

  Scenario: Search, filter, sort, and verify product details
    Given User opens Akakce app
    When User skips login if requested
    And User searches for "Laptop"
    And User filters by Bluetooth "5.3"
    And User sorts by "En Düşük Fiyat"
    Then User clicks the 1st real product
    And User verifies price and "Ürüne Git" button on detail page