package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ShoppingCartPage {
    protected WebDriver driver;
    private static final String REMOVE_TO_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";

    @FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//button[@id='checkout']")
    public WebElement checkoutBtn;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement burgerMenuButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    public ShoppingCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutInformationPage checkout(){
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));

        if (shoppingCartCounter.getText().isEmpty()) {
            System.out.println("Add items to the shopping cart");
        } else {
            shoppingCartLink.click();
        }
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        fluentWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        shoppingCartPage.checkoutBtn.click();

            return new CheckoutInformationPage(driver);
    }
}
