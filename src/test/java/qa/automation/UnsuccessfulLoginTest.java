package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvHelper;

import java.io.IOException;

public class UnsuccessfulLoginTest extends TestUtil {

    @DataProvider(name = "wrongUsersList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/wrongUsers.csv");
    }

    @Test(dataProvider = "wrongUsersList")
    public void UnsuccessfulLogin(String username, String password){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.tryToLogin(username,password);

        Assert.assertTrue(loginPage.isLoginErrorMessageShown());
    }
}
