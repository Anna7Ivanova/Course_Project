package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class CheckoutInformationPage {
    protected WebDriver driver;

    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstNameInput;

    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameInput;

    @FindBy(css = "[placeholder='Zip/Postal Code']")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    public WebElement continueBtn;

    @FindBy(xpath = "//button[@class='btn btn_secondary back btn_medium cart_cancel_link']")
    private WebElement cancelBtn;


    public CheckoutInformationPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public CheckoutOverviewPage continueChecking(String firstName, String lastName, String postalCode){

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);
        lastNameInput.click();
        lastNameInput.sendKeys(lastName);
        postalCodeInput.click();
        postalCodeInput.sendKeys(postalCode);

        fluentWait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        continueBtn.click();

        return new CheckoutOverviewPage(driver);
    }

}
