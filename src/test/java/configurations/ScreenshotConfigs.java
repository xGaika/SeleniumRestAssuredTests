package configurations;

public class ScreenshotConfigs {
    public String getScreenPathForCurrentTest() {
        return ".//src//test//screenshots//currentTest//";
    }

    public String getScreenPathForScreenArchive() {
        return ".//src//test//screenshots//lastTest//";
    }

    public String getScreenPathForResult() {
        return ".//src//test//screenshots//result//";
    }
}
