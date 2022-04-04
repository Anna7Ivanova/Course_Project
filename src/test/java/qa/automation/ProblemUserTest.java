package qa.automation;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ProblemUserTest extends TestUtil {
    @Test
    public void problemUserTest(){
        LoginPage loginPage = new LoginPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        ProductsPage productsPage = loginPage.login("problem_user","secret_sauce");
        productsPage.addItemToTheCart("bike-light");

        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "Because we have only one item so far!");

        productsPage.addItemToTheCart("backpack");
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have 2 items!");

        productsPage.checkShoppingCart();
        Assert.assertTrue(shoppingCartPage.checkoutBtn.isDisplayed());


        shoppingCartPage.checkout();
        Assert.assertTrue(checkoutInformationPage.continueBtn.isDisplayed(), "This shall be visible after pressing the checkout button at the shopping cart page!");

        checkoutInformationPage.continueChecking("Anna", "Ivanova", "1696");
        Assert.assertTrue(checkoutOverviewPage.finishBtn.isDisplayed(), "This shall be visible after pressing the continue button at the checkout information page!");

        checkoutOverviewPage.finishingPurchase();
        Assert.assertTrue(checkoutCompletePage.backHomeBtn.isDisplayed());

        checkoutCompletePage.logout();
        Assert.assertTrue(loginPage.loginBtn.isDisplayed(), "This shall be visible after successful purchasing!");


    }
}
