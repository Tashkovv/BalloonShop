package mk.finki.ukim.mk.laba.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class AbstractPage {

    protected WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    static void get(WebDriver webDriver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        webDriver.get(url);
    }

}