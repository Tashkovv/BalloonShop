package mk.finki.ukim.mk.laba;


import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.service.BalloonService;
import mk.finki.ukim.mk.laba.service.ManufacturerService;
import mk.finki.ukim.mk.laba.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabAApplicationTests {

    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    private static Manufacturer m1;
    private static boolean dataInitialized = false;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        initData();
    }

    private void initData() {
        if (!dataInitialized) {
            m1 = manufacturerService.save("m1", "m1", "m1").get();
            manufacturerService.save("m2", "m2", "m2");

            String user = "user";
            String admin = "admin";

            userService.register(user, "password", "password");
            userService.register(admin, "password", "password");

            dataInitialized = true;

        }
    }

    @Test
    public void getBalloons() throws Exception {
        MockHttpServletRequestBuilder balloonsRequest = MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(balloonsRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "listBalloons"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));

    }


}
