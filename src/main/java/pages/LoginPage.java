package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/";

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By loginTitle = By.cssSelector("h5.oxd-text--h5");
    private final By errorAlert = By.cssSelector("div.oxd-alert--error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        driver.get(LOGIN_URL);
        waitForUrlContains("/auth/login");
    }

    public void enterUserName(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void loginAs(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        click(loginButton);
    }

    public boolean isOnLoginPage() {
        return waitForUrlContains("/auth/login") && isElementVisible(usernameInput) && isElementVisible(loginTitle);
    }

    public boolean isErrorDisplayed() {
        return isElementVisible(errorAlert);
    }

    public void clickLogin() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

}