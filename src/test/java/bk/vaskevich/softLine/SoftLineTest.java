package bk.vaskevich.softLine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SoftLineTest extends TestBase {

    @Test
    public void openPageTest() {
        step("Открываем страницу", () -> {
            open("https://softline.com/");
            $(byPartialLinkText("https://softline.com/investor-relations"))
                    .shouldHave(text("Softline объявляет о сильных финансовых результатах  H1 2021"));
        });
    }
}
