package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(css = "[placeholder=Password]")
    private WebElement passwordInput;

    @FindBy(css = "[value=Login]")
    public WebElement loginBtn;

    @FindBy(xpath = "//*[text()='Epic sadface: Username and password do not match any user in this service']")
    private WebElement genericErrorMessage;

    @FindBy(xpath = "//*[text()='Epic sadface: Username is required']")
    private WebElement usernameEmptyErrorMessage;

    @FindBy(xpath = "//*[text()='Epic sadface: Password is required']")
    private WebElement passwordEmptyErrorMessage;

    @FindBy(xpath = "//button[@class='error-button']")
    private WebElement xButtonOfLoginErrorMessages;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public ProductsPage login(String username, String password){
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));
        userNameInput.click();
        userNameInput.sendKeys(username);
        passwordInput.click();
        passwordInput.sendKeys(password);
        fluentWait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
        return new ProductsPage(driver);
    }

    public void tryToLogin(String username, String password){
        userNameInput.click();
        userNameInput.sendKeys(username);
        passwordInput.click();
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public boolean isLoginErrorMessageShown(){

        return xButtonOfLoginErrorMessages.isDisplayed();
    }

}
