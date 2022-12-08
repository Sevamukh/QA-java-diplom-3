package site.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.stellarburgers.model.LoginPage;
import site.stellarburgers.model.MainPage;

import static com.codeborne.selenide.Selenide.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Переходы на страницы")
public class GoToPageTest {

    MainPage mainPage;
    LoginPage loginPage;

    @BeforeClass
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        Configuration.browserBinary = "Applications/Yandex.app";
        WebDriver yandexDriver = new ChromeDriver();
        WebDriverRunner.setWebDriver(yandexDriver);
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        loginPage = page(LoginPage.class);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        Assert.assertEquals(WebDriverRunner.url(), LoginPage.LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void goToConstructorFromPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickConstructorLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void goToConstructorFromPersonalAccountByLogo() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickLogoLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterAll() {
        closeWebDriver();
    }
}
