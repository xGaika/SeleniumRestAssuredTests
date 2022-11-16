package UI.pageFactory;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersPF {
    @FindBy(xpath = "//*[@id=\"payment-system-transaction-grid\"]")
    @CacheLookup
    private WebElement tableWithPlayers;

    @FindBy(xpath = "//a[text()=\"External ID\"]")
    @CacheLookup
    private WebElement externalID;

    @FindBy(xpath = "//*[@id=\"payment-system-transaction-grid\"]/span[2]/div")
    @CacheLookup
    private WebElement elementCountString;

    @FindBy(xpath = "//*[@id=\"payment-system-transaction-grid\"]/table/tbody/tr/td[3]")
    @CacheLookup
    private List<WebElement> elList;

    WebDriver driver;

    public PlayersPF(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getTableWithPlayers() {
        return this.tableWithPlayers;
    }

    public void clickExternalId() {
        this.externalID.click();
    }

    public int getCountOfElements(){
        String[] strArr = elementCountString.getText().split("-");
        return Integer.parseInt(strArr[1].substring(0, 2));
    }

    public void checkTableSorted(int countPlayersOnPage){
        Assertions.assertEquals(this.elList.size(), countPlayersOnPage);
        ArrayList<String> elListText = new ArrayList<>();
        for (WebElement webElement : this.elList) {
            elListText.add(webElement.getText());
        }
        Collections.sort(elListText);
        for (int i = 0; i < this.elList.size(); i++) {
            Assertions.assertEquals(elListText.get(i), this.elList.get(i).getText());
        }
    }
}
