package tests;

import components.BurgerMenuComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class WbNavbarTest extends TestBase{

    MainPage mainPage = new MainPage();
    BurgerMenuComponent burgerMenu = new BurgerMenuComponent();

    @BeforeEach
    public void setUp() {
        open("https://www.wildberries.ru/");
        sleep(1000);
    }

    @Tag("SMOKE")
    @CsvFileSource(resources = "/navbar-test-data.csv")
    @ParameterizedTest
    public void navbarDroppedMenuCsvFileTest(String categoryName, String expectedTypeOfGoods) {
        mainPage.openNavbar();
        burgerMenu.openCategoryList(categoryName);
        burgerMenu.checkDroppedCategory(expectedTypeOfGoods);
    }

    @Tag("SMOKE")
    @CsvSource({
            "Мужчинам, Брюки",
            "Обувь, Детская",
            "Женщинам, Блузки и рубашки",
    })
    @ParameterizedTest
    public void navbarDroppedMenuCsvAnnotationTest(String categoryName, String expectedTypeOfGoods) {
        mainPage.openNavbar();
        burgerMenu.openCategoryList(categoryName);
        burgerMenu.checkDroppedCategory(expectedTypeOfGoods);
    }

    @Tag("SMOKE")

    @MethodSource("navbarDroppedMenuMethodSourceTest")
    @ParameterizedTest
    public void navbarDroppedMenuMethodSourceTest(String categoryName, List<String> expectedTypeOfGoodsList) {
        mainPage.openNavbar();
        burgerMenu.openCategoryList(categoryName);
        burgerMenu.checkDroppedCategories(expectedTypeOfGoodsList);
    }

    static Stream<Arguments> navbarDroppedMenuMethodSourceTest() {
        return Stream.of(
                Arguments.of("Женщинам",
                        List.of("Блузки и рубашки", "Костюмы", "Лонгсливы")),

                Arguments.of("Обувь",
                        List.of("Детская", "Мужская", "Женская")),

                Arguments.of("Мужчинам",
                        List.of("Брюки", "Джинсы", "Майки"))
        );
    }
}
