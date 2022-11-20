package configurations;

import org.openqa.selenium.chrome.ChromeOptions;

public class ConfigBrowser {
    private ChromeOptions options;

    public ConfigBrowser(ChromeOptions options) {
        this.options = options;
    }
        public ChromeOptions configBrowser() {
            this.options.addArguments("start-maximized");
            this.options.addArguments("enable-automation");
            this.options.addArguments("--no-sandbox");
            this.options.addArguments("--disable-infobars");
            this.options.addArguments("--disable-dev-shm-usage");
            this.options.addArguments("--disable-browser-side-navigation");
            this.options.addArguments("--disable-gpu");
            this.options.addArguments("--window-size=1920,1080");
            this.options.addArguments("--headless");
            return this.options;
        }
}