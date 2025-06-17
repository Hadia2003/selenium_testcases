package com.najee.tests.selenium.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:5000";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments(
            "--headless=new",            // Chrome 109+ headless
            "--disable-gpu",             // windows CI
            "--no-sandbox",              // linux CI
            "--disable-dev-shm-usage",   // avoid /dev/shm issues
            "--window-size=1920,1080",
            "--remote-allow-origins=*"
        );

        driver = new ChromeDriver(opts);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testHomePageTitle() {
        driver.get(BASE_URL + "/");
        assertEquals(
            "Flask Blog",
            driver.getTitle(),
            "Home page title should be “Flask Blog”"
        );
    }

    @Test
    void testNavbarBrand() {
        // 1. Go to the home page
        driver.get(BASE_URL);

        // 2. Grab the “Flask Blog” brand link
        WebElement brand = driver.findElement(By.cssSelector("a.navbar-brand"));

        // 3. Assert its text is exactly “Flask Blog”
        assertEquals(
            "Flask Blog",
            brand.getText(),
            "Navbar brand should read 'Flask Blog'"
        );
    }
    @Test
    void testNavbarLinksPresent() {
        // 1) Go to home
        driver.get(BASE_URL);

        // 2) Find the “Home” and “About” links in the navbar
        WebElement homeNav = driver.findElement(By.linkText("Home"));
        WebElement aboutNav = driver.findElement(By.linkText("About"));

        // 3) Assert they’re both displayed
        assertTrue(homeNav.isDisplayed(),  "Navbar should show a Home link");
        assertTrue(aboutNav.isDisplayed(), "Navbar should show an About link");
    }
    @Test
    void testAboutLinkNavigates() {
        // 1) Start at home
        driver.get(BASE_URL);

        // 2) Click the “About” navbar link
        WebElement aboutLink = driver.findElement(By.linkText("About"));
        aboutLink.click();

        // 3) Verify the URL ends with “/about”
        String current = driver.getCurrentUrl();
        assertTrue(
            current.endsWith("/about"),
            "Clicking About should navigate to /about but was: " + current
        );
    }
    @Test
    void testNavbarBrandIsPresent() {
        // 1. Go to the home page
        driver.get(BASE_URL);

        // 2. Look up the navbar-brand
        WebElement brand = driver.findElement(By.cssSelector(".navbar-brand"));

        // 3. Assert it reads “Flask Blog”
        assertEquals(
            "Flask Blog",
            brand.getText(),
            "Navbar brand should always be “Flask Blog”"
        );
    }
    @Test
    void testNavbarBrandAlwaysPresent() {
        // 1. Navigate to the home page (you can change this to /login or /register too)
        driver.get(BASE_URL);
        // 2. Grab the element with class "navbar-brand"
        WebElement brand = driver.findElement(By.className("navbar-brand"));
        // 3. Assert that its text is exactly "Flask Blog"
        assertEquals(
            "Flask Blog",
            brand.getText(),
            "The navbar-brand should read “Flask Blog” on every page"
        );
    }
    @Test
    void testNavAboutLinkPresent() {
        driver.get(BASE_URL);
        WebElement aboutLink = driver.findElement(By.linkText("About"));
        assertTrue(aboutLink.isDisplayed(), "About link should be present in the navbar");
    }

    @Test
    void testNavLoginLinkPresent() {
        driver.get(BASE_URL);
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        assertTrue(loginLink.isDisplayed(), "Login link should be present in the navbar");
    }
   
    @Test
    void testSidebarIsPresent() {
        // 1. Open home
        driver.get(BASE_URL);
        // 2. The layout always renders an <h3> “Our Sidebar”
        WebElement sidebar = driver.findElement(By.xpath("//h3[text()='Our Sidebar']"));
        assertTrue(sidebar.isDisplayed(), "Sidebar heading should be visible");
    }
    @Test
    void testAboutNavLink() {
        // 1. Load the home page
        driver.get(BASE_URL);

        // 2. Grab the “About” link
        WebElement aboutLink = driver.findElement(By.linkText("About"));

        // 3a. Check that the href *ends* in “/about”
        String href = aboutLink.getAttribute("href");
        assertTrue(href.endsWith("/about"),
            () -> "About link href should end with '/about' but was: " + href);

        // 3b. (Optional) Click it and verify the URL as well:
        aboutLink.click();
        assertTrue(driver.getCurrentUrl().endsWith("/about"),
            "After click, URL should end in /about");
    }

}
