package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=\"dashboard\"]");

    private ElementsCollection cards = $$(".list_item");
    private ElementsCollection button = $$("[data-test-id=\"action-deposit\"]");

    private ElementsCollection card1 = $$("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private ElementsCollection card2 = $$("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");

    private final String balanceStart = "баланс: ";

    private final String balanceFinish = "р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);//indexOf - возвращает позицию, с которой начинается подстрока в строке
        val finish = text.indexOf(balanceFinish);//indexOf - возвращает позицию, с которой начинается подстрока в строке
        val value = text.substring(start + balanceStart.length(), finish);//substring - вырезает подстроку из строки
        return Integer.parseInt(value);
    }
}
