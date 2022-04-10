package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class SuccessfulLoginTest extends TestUtil {

    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/users.csv");
    }

    @Test(dataProvider = "csvUserList")
    public void SuccessfulLogin(String username, String password){

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(username, password);

        WebElement burgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertTrue(burgerMenuButton.isDisplayed(), "This shall be visible after successful login");//version 1

        //version 2. What version 1 or 2 is correct?
        //Assert.assertTrue(productsPage.burgerMenuButton.isDisplayed(), "This shall be visible after successful login");
    }
}
