package test;

import com.codeborne.selenide.Configuration;
import data.DataHelp;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageObjectsTest {
    int start_balance1;

    int start_balance2;

    int end_balance1;

    int end_Balance2;

    int sum;

    DashboardPage dashboardPage;

    @BeforeEach
    void SetUp() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelp.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        start_balance1 = dashboardPage.getBalance(dashboardPage.card1);
        start_balance2 = dashboardPage.getBalance(dashboardPage.card2);
    }

    @Test
    @DisplayName("Перевод денег сo второй карты на первую")
    void shouldTransferMoneyFromSecondToFirstCard() {
        sum = 100;
        val topUpPage = dashboardPage.clickTopUp(dashboardPage.card1);
        val cardNum = DataHelp.getSecondCard().getNumber();
        val dashboardPage2 = topUpPage.successfulTopUp(Integer.toString(sum), (String) cardNum);
        end_balance1 = dashboardPage2.getBalance(dashboardPage2.card1);
        end_Balance2 = dashboardPage2.getBalance(dashboardPage2.card2);
        assertEquals(start_balance1 + sum, end_balance1);
        assertEquals(start_balance2 - sum, end_Balance2);
    }

    @Test
    @DisplayName("Перевод денег с первой карты на вторую")
    void shouldTransferMoneyFromFirstToSecondCard() {
        sum = 100;
        val topUpPage = dashboardPage.clickTopUp(dashboardPage.card2);
        val cardNum = DataHelp.getFirstCard().getNumber();
        val dashboardPage2 = topUpPage.successfulTopUp(Integer.toString(sum), (String) cardNum);
        end_balance1 = dashboardPage2.getBalance(dashboardPage2.card1);
        end_Balance2 = dashboardPage2.getBalance(dashboardPage2.card2);
        assertEquals(start_balance1 - sum, end_balance1);
        assertEquals(start_balance2 + sum, end_Balance2);
    }

    @Test
    @DisplayName("Не должен переводить больше, чем есть на карте")
    void shouldNotTransferMoreThanAvailable() {
        sum = start_balance1 + 100;
        val topUpPage = dashboardPage.clickTopUp(dashboardPage.card2);
        val cardNum = DataHelp.getFirstCard().getNumber();
        topUpPage.unsuccessfulTopUp(Integer.toString(sum), cardNum);
    }









}




