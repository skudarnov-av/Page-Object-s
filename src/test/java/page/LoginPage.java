package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelp;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public LoginPage(int amountValue, DataHelp.Cards secondCard) {

    }

    public LoginPage() {

    }

    public VerificationPage validLogin(DataHelp.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

}


