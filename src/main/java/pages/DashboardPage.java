package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {

    private final WebDriverWait wait;

    private final By tituloDashboard = By.xpath("//h6[text()='Dashboard']");
    private final By menuPIM = By.xpath("//span[text()='PIM']");
    private final By menuUsuario = By.cssSelector(".oxd-userdropdown-tab");
    private final By opcionLogout = By.xpath("//a[text()='Logout']");

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnDashboard() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(tituloDashboard)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tituloDashboard)).getText();
    }

    public void goToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(menuPIM)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuUsuario)).click();
        wait.until(ExpectedConditions.elementToBeClickable(opcionLogout)).click();
    }
}