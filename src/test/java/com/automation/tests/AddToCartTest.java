package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.retry.RetryAnalyzer;
import com.automation.utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addProductToCartTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        productPage.addProductToCart("ZARA COAT 3");
       
        productPage.goToCart();

        Assert.assertTrue(
                cartPage.isProductPresent("ZARA COAT 3")
        );
    }
}