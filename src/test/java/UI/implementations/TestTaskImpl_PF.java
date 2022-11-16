package UI.implementations;

import UI.engine.ScreenshotAsserter;
import UI.pageFactory.LoginPF;
import UI.pageFactory.MainPF;
import UI.pageFactory.PlayersPF;
import configurations.ConfigBrowser;
import data.Endpoints;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestTaskImpl_PF {

    ScreenshotAsserter scrAsserter = new ScreenshotAsserter();
    Endpoints endpoints = new Endpoints();
    WebDriver driver;
    int countPlayersOnPage;

    LoginPF login;
    MainPF main;
    PlayersPF players;

    @Before
    public void browser_setup(){
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.com.driver", projectPath + "/src/test/drivers/chromedriver.exe");
        this.driver = new ChromeDriver(new ConfigBrowser(new ChromeOptions()).configBrowser());
    }

    @Given("user is on login page")
    public void user_is_on_login_page() {
        this.driver.navigate().to(endpoints.getLoginUri());
        login = new LoginPF(this.driver);
        Assertions.assertAll(
                () -> Assertions.assertTrue(login.getUsernameField().isDisplayed()),
                () -> Assertions.assertTrue(login.getPasswordField().isDisplayed()),
                () -> Assertions.assertTrue(login.getLoginButton().isDisplayed()));
        scrAsserter.getScreenshot(driver, "loginPage");
    }

    @When("^user login with (.*) and (.*)$")
    public void user_login_with_username_and_password(String username, String password) {
        main = new MainPF(this.driver);
        login.enterUsername(username);
        login.enterPassword(password);
        login.clickOnLogin();
        scrAsserter.getScreenshot(driver, "adminPanel");
        Assertions.assertTrue(main.getPlayersBtn().isDisplayed());
    }

    @And("get players")
    public void get_players() {
        players = new PlayersPF(this.driver);
        main.clickPlayerBtn();
        Assertions.assertTrue(players.getTableWithPlayers().isDisplayed());
        scrAsserter.getScreenshot(driver, "playersTable");
    }

    @And("sort players by ExternalId")
    public void sort_players_by_ExternalId() throws InterruptedException {
        players.clickExternalId();
        countPlayersOnPage = players.getCountOfElements();
        //TODO переделать костыль
        Thread.sleep(3000);
    }

    @Then("check that players sorted by externalId")
    public void check_that_players_sorted_by_externalId() {
        players.checkTableSorted(countPlayersOnPage);
    }

    @After
    public void browserClose() {
        driver.close();
        driver.quit();
    }
}
