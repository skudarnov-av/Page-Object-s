package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement yoursCards = $("h1");
    private SelenideElement firstCardDepositingButton = $x("//li[1]/div/button");
    private SelenideElement secondCardDepositingButton = $x("//li[2]/div/button");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public DashboardPage() {
        heading.shouldBe(visible);
        yoursCards.shouldBe(visible);
    }


    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    public TopUpPage firstCardDepositing() {
        firstCardDepositingButton.click();
        return new TopUpPage();
    }

    public TopUpPage secondCardDepositing() {
        secondCardDepositingButton.click();
        return new TopUpPage();
    }

}


