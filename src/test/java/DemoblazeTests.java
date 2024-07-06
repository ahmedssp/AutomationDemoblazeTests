package com.demoblaze;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pag.cart_page;
import pag.homepage;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoblazeTests {
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    public static homepage homepage;
    static String username;
    static String password;


    @BeforeEach
    void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
         homepage =new homepage(page);
        page.navigate("https://www.demoblaze.com/");
        username= new Faker().internet().domainName()+ new Random(1000);
        password=username;

    }
    @AfterEach
     void teardown() {
        context.close();
        browser.close();
        playwright.close();
    }
    @Test
    void testUserRegistration() throws InterruptedException {

        homepage.registration(new Faker().internet().domainName()+ new Random(1000),password);
        // Add assertion to verify successful registration
        page.onceDialog(dialog -> {
            assertEquals("Sign up successful.", dialog.message());
            dialog.accept(); // Close the alert
        });
    }

    @Test
    void testUserLogin() throws InterruptedException {
        homepage.login(username,password);
        // Assert that the word "Welcome" appears on the page
        page.waitForSelector("text=Welcome");
        String pageContent = page.content();
        assertTrue(pageContent.contains("Welcome"));
    }

    @Test()
    void testUserLogout() {
        homepage.login(username,password);
        page.click("#logout2");
    }

    @Test
    void testCreateOrderForAppleMonitor24() throws InterruptedException {
        homepage.registration(username,password);
        cart_page cart_page_obj=homepage.login(username,password);
        cart_page_obj.addCart(username,"egypt","cairo","232456786","12","2024");
    }
}