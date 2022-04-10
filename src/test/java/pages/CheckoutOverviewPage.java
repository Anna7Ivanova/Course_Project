package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    protected WebDriver driver;

    @FindBy(xpath = "//button[@id='finish']")
    public WebElement finishBtn;

    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelBtn;

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutCompletePage finishingPurchase(){
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));
        //CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);

        fluentWait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        finishBtn.click();

        return new CheckoutCompletePage(driver);
    }

}
