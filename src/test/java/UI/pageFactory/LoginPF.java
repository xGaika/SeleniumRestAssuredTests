package UI.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPF {
    @FindBy(id = "UserLogin_username")
    private WebElement username;

    @FindBy(id = "UserLogin_password")
    private WebElement password;

    @FindBy(xpath = "//input[@value=\"Sign in\"]")
    private WebElement loginBtn;

    WebDriver driver;

    public LoginPF(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        this.username.sendKeys(username);
    }

    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }

    public void clickOnLogin() {
        this.loginBtn.click();
    }

    public WebElement getUsernameField(){
        return this.username;
    }

    public WebElement getPasswordField(){
        return this.password;
    }

    public WebElement getLoginButton(){
        return this.loginBtn;
    }
}
