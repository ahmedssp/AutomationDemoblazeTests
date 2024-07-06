package pag;

import com.microsoft.playwright.Page;

public class homepage {
   public static Page page;

    public homepage(Page page) {
        this.page = page;
    }

    public cart_page login(String username ,String password ){

        page.click("#login2");
        page.fill("#loginusername", username);
        page.fill("#loginpassword", password);
        page.click("button[onclick='logIn()']");
        return new cart_page(page);
    }
    public void registration(String username ,String password ){
        page.click("#signin2");
        page.fill("#sign-username", username);
        page.fill("#sign-password", password);
        page.click("button[onclick='register()']");
    }
}
