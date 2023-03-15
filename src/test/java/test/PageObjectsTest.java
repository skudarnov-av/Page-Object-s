package test;

import com.codeborne.selenide.Configuration;
import data.DataHelp;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

 public class PageObjectsTest {

     @BeforeEach
     void prepareForTest() {
         open("http://localhost:9999");
         Configuration.holdBrowserOpen = true;
         var loginPage = new LoginPage();
         var authInfo = DataHelp.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
         verificationPage.validVerify(verificationCode);
     }

     @Test
     void shouldTransferMoneyToFirstCard() {
         var dashboardPage = new DashboardPage();
         int amountValue = 10_000;
         var expAmountOfMoneyOnFirstCard = dashboardPage.getFirstCardBalance() + amountValue;
         var expAmountOfMoneyOnSecondCard = dashboardPage.getSecondCardBalance() - amountValue;
         var depositingFunds = dashboardPage.firstCardDepositing();
         new LoginPage(amountValue, DataHelp.getSecondCard());
         var actAmountOfMoneyOnFirstCard = dashboardPage.getFirstCardBalance();
         var actAmountOfMoneyOnSecondCard = dashboardPage.getSecondCardBalance();
         assertEquals(expAmountOfMoneyOnFirstCard, actAmountOfMoneyOnFirstCard);
         assertEquals(expAmountOfMoneyOnSecondCard, actAmountOfMoneyOnSecondCard);

     }
     @Test
     void shouldTransferMoneyToSecondCard() {
         var dashboardPage = new DashboardPage();
         int amountValue = 10_000;
         var expAmountOfMoneyOnFirstCard = dashboardPage.getFirstCardBalance() - amountValue;
         var expAmountOfMoneyOnSecondCard = dashboardPage.getSecondCardBalance() + amountValue;
         var depositingFunds = dashboardPage.secondCardDepositing();
         new LoginPage(amountValue, DataHelp.getFirstCard());
         var actAmountOfMoneyOnFirstCard = dashboardPage.getFirstCardBalance();
         var actAmountOfMoneyOnSecondCard = dashboardPage.getSecondCardBalance();
         assertEquals(expAmountOfMoneyOnFirstCard, actAmountOfMoneyOnFirstCard);
         assertEquals(expAmountOfMoneyOnSecondCard, actAmountOfMoneyOnSecondCard);
     }

}




