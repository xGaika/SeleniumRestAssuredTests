package UI.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPF {
    @FindBy(xpath = "//p[text()=\"Players online / total\"]")
    @CacheLookup
    private WebElement playerBtn;

    WebDriver driver;

    public MainPF(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getPlayersBtn(){
        return this.playerBtn;
    }

    public void clickPlayerBtn(){
        this.playerBtn.click();
    }
}
