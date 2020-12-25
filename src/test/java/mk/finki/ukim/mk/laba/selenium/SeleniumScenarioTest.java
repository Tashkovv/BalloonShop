package mk.finki.ukim.mk.laba.selenium;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.model.User;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import mk.finki.ukim.mk.laba.service.BalloonService;
import mk.finki.ukim.mk.laba.service.ManufacturerService;
import mk.finki.ukim.mk.laba.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    private HtmlUnitDriver driver;

    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;
    private static boolean dataInitialized = false;
    private static Balloon b1;
    private static Balloon b2;

    @BeforeEach
    private void setup() {

        this.driver = new HtmlUnitDriver(true);
        initData();

    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            driver.close();
        }
    }

    private void initData() {
        if (!dataInitialized) {

            m1 = manufacturerService.save("m1", "m1", "m1").get();
            m2 = manufacturerService.save("m2", "m2", "m2").get();

            b1 = balloonService.save("b1", "b1", m1, TYPE.OVAL).get();
            b2 = balloonService.save("b2", "b2", m2, TYPE.HEART).get();

            regularUser = userService.register("tashkov", "tash", "tash");
            adminUser = userService.register("admin", "admin", "admin");

            dataInitialized = true;

        }
    }

    @Test
    public void testScenario() {

        BalloonPage balloonPage = BalloonPage.to(this.driver);
        balloonPage.assertElements(0, 0, 0, 0);

        LoginPage loginPage = LoginPage.openLogin(this.driver);

        balloonPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");
        balloonPage.assertElements(2, 2, 1, 2);

    }

}
