package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PIMPage extends BasePage {

    private final By pimHeader = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private final By employeeNameInput = By.xpath("//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By tableLoadingSpinner = By.cssSelector("div.oxd-loading-spinner");
    private final By resultRows = By.cssSelector("div.oxd-table-body div.oxd-table-card");
    private final By noRecordsToast = By.xpath("//*[contains(text(),'No Records Found')]");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnPIMPage() {
        return waitForUrlContains("/pim/viewEmployeeList") && isElementVisible(pimHeader);
    }

    public void searchEmployeeByName(String name) {
        WebElement input = waitForVisibility(employeeNameInput);
        input.click();
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(name);
        waitForElementToDisappear(tableLoadingSpinner);
        click(searchButton);
        waitForElementToDisappear(tableLoadingSpinner);
    }

    public boolean hasResults() {
        List<WebElement> rows = driver.findElements(resultRows);
        return !rows.isEmpty();
    }

    public boolean isNoRecordsDisplayed() {
        return isElementVisible(noRecordsToast);
    }
}