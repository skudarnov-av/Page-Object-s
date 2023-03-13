package test;

import com.codeborne.selenide.Configuration;
import data.DataHelp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageObjectsTest {
    int money = 1000;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var authInfo = DataHelp.getAuthInfo();
        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
    }
    @Test
    void shouldTransferMoneySecondCard() {
        $("[data-test-id=\"action-deposit\"]").click();
        $("[data-test-id=\"amount\"] input").setValue(String.valueOf(money));
    }
}



