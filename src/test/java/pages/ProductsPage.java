package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ProductsPage {
    protected WebDriver driver;
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-sauce-labs-%s']";
    private static final String REMOVE_TO_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement dropDownSortingOptions;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement burgerMenuButton;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToTheCart(String productName) {
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));

        String xpathOfElementToBeAdded = String.format(ADD_TO_CART_LOCATOR, productName);
        WebElement addToCartButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));
        fluentWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

    public boolean removeItemFromTheCart(String productName) {
        String xpathOfElementToBeAdded = String.format(REMOVE_TO_CART_LOCATOR, productName);
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));

        WebElement removeButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeButton));
        if (removeButton.getText().equals("Remove")) {
            removeButton.click();
            return true;
        } else {
            return false;
        }
    }

    public int getItemsInTheCart() {
        if (shoppingCartCounter.getText().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(shoppingCartCounter.getText());
        }
    }

    public ShoppingCartPage checkShoppingCart() {

        if (shoppingCartCounter.getText().isEmpty()) {
            System.out.println("Add items to the shopping cart");
        } else {
            shoppingCartLink.click();
        }
        return new ShoppingCartPage(driver);
    }
}