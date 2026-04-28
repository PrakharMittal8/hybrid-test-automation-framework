Feature: Add product to cart

  Scenario: User adds product successfully

    Given user launches application
    When user logs in with valid credentials
    And user adds product "ZARA COAT 3" to cart
    And user navigates to cart page
    Then product "ZARA COAT 3" should be visible in cart