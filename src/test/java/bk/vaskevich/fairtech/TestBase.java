package bk.vaskevich.fairtech;

import bk.vaskevich.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    public static void init(){
        SelenideLogger.addListener("AllureSelenide",new AllureSelenide());

        /*String browser = System.getProperty("browser");
        String version = System.getProperty("version");
        String login = System.getProperty("login","user1");
        String password = System.getProperty("password","1234");
        String url = System.getProperty("url");
        String remoteUrl = "https://" + login + ":" + password + "@" + url;*/

        baseUrl = "https://fairtech.ru/";
        Configuration.browserSize = "1920x1080";
        /*Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.remote = remoteUrl;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo",true);
        Configuration.browserCapabilities = capabilities;*/
    }

    @AfterEach
    public void addAttachments() {
        //Attach.screenshotAs(System.getProperty("browser")+" "+System.getProperty("version"));
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }
}