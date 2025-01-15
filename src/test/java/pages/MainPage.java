package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private static final SelenideElement navbarButton = $(".nav-element__burger");

    public void openNavbar() {
        navbarButton.shouldBe(visible).click();
    }
}