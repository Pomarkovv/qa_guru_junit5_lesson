package components;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BurgerMenuComponent {

    private final SelenideElement droppedCategories = $(".menu-burger__first");
    private final SelenideElement mainCategoryButton = $("ul.menu-burger__main-list");


    public void openCategoryList(String name) {
        mainCategoryButton.$(byText(name)).shouldBe(visible).hover();
    }

    public void checkDroppedCategory(String expected) {
        droppedCategories.shouldBe(visible).shouldHave(text(expected));
    }

    public void checkDroppedCategories(List<String> expectedList) {
        expectedList.forEach(this::checkDroppedCategory);
    }
}
