package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {
    protected WebDriver driver;

    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishBtn;

    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelBtn;

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutCompletePage finishingPurchase(){
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.finishBtn.click();
        return new CheckoutCompletePage(driver);
    }

}
