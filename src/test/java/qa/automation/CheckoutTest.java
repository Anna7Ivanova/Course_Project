package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CsvHelper;

import java.io.IOException;

public class CheckoutTest extends TestUtil {

    @DataProvider(name = "checkoutList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/checkout.csv");
    }

    @Test(dataProvider = "checkoutList")
    public void checkoutTest(String username, String password, String product1, String product2, String firstName, String lastName, String postalCode){
        LoginPage loginPage = new LoginPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);


        ProductsPage productsPage = loginPage.login(username, password);
        productsPage.addItemToTheCart(product1);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "Because we have only one item so far!");

        productsPage.addItemToTheCart(product2);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have 2 items!");

        productsPage.checkShoppingCart();
        Assert.assertTrue(shoppingCartPage.checkoutBtn.isDisplayed());

        shoppingCartPage.checkout();
        Assert.assertTrue(checkoutInformationPage.continueBtn.isDisplayed(), "This shall be visible after pressing the checkout button at the shopping cart page!");

        checkoutInformationPage.fillInData(firstName, lastName, postalCode);
        Assert.assertTrue(checkoutOverviewPage.finishBtn.isDisplayed(), "This shall be visible after pressing the continue button at the checkout information page!");

        checkoutOverviewPage.finishingPurchase();
        Assert.assertTrue(checkoutCompletePage.backHomeBtn.isDisplayed());

        checkoutCompletePage.logout();
        Assert.assertTrue(loginPage.loginBtn.isDisplayed(), "This shall be visible after successful purchasing!");

    }
}
