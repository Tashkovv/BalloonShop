package mk.finki.ukim.mk.laba.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonPage extends AbstractPage {

    @FindBy(css = ".delete-button")
    private List<WebElement> deleteButtons;

    @FindBy(css = ".edit-button")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-button")
    private List<WebElement> addButton;

    @FindBy(css = ".order-button")
    private List<WebElement> orderButtons;

    public BalloonPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static BalloonPage to(WebDriver webDriver) {

        get(webDriver, "/balloons");
        return PageFactory.initElements(webDriver, BalloonPage.class);

    }

    public void assertElements(int numberOfDeleteButtons, int numberOfEditButtons, int numberOfAddButtons, int numberOfOrderButtons) {

        Assert.assertEquals("delete buttons do not match!", numberOfDeleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit buttons do not match!", numberOfEditButtons, this.getEditButtons().size());
        Assert.assertEquals("add button is visible!", numberOfAddButtons, this.getAddButton().size());
        Assert.assertEquals("order buttons do not match!", numberOfOrderButtons, this.getOrderButtons().size());

    }


}