package data;

public class Endpoints {
    private String url = "http://test-app.d6.dev.devcaz.com";
    private String loginUrn = "admin/login";

    public String getLoginUri() {
        return this.url + "/" + this.loginUrn;
    }
}
