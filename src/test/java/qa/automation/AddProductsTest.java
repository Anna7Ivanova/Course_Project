package qa.automation;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class AddProductsTest extends TestUtil {

    @Test
    public void addProductsToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user","secret_sauce");
        productsPage.addItemToTheCart("bolt-t-shirt");

        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "Because we have only one item so far!");

        productsPage.addItemToTheCart("fleece-jacket");
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have 2 items!");



    }

}
