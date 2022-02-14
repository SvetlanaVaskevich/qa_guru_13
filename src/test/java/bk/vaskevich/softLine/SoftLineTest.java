package bk.vaskevich.softLine;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SoftLineTest extends TestBase {

    @Tag("properties")
    @Test
    public void FillFormTest() {
        step("Открываем страницу регистрации", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
        step("Заполняем имя и фамилию", () -> {
            $("#firstName").setValue("Svetlana");
            $("#lastName").setValue("Ivanova");
        });
        step("Заполняем почту", () -> {
            $("#userEmail").setValue("mymail@bk.ru");
        });
        step("Заполняем пол", () -> {
            $(byText("Female")).click();
        });
        step("Заполняем номер", () -> {
            $("#userNumber").setValue("8999456123");
        });
        step("Заполняем дату рождения", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").click();
            $(".react-datepicker__month-select").selectOption("December");
            $(".react-datepicker__year-select").selectOption("1987");
            $(".left-pannel").scrollTo();
            $(".react-datepicker__day.react-datepicker__day--023").click();
        });
        step("Заполняем данные о хобби", () -> {
            $("#subjectsInput").setValue("English").pressEnter();
            $(byText("Sports")).click();
        });
        step("Заполняем данные об адресе", () -> {
            $("#currentAddress").setValue("Russia,Ekaterinburg").scrollTo();
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Noida")).click();
            $("#submit").click();
        });
        step("Проверка таблицы данных", () -> {
            $(".table-responsive").shouldHave(text("Svetlana Ivanova"), text("mymail@bk.ru"),
                    text("Female"), text("23 December,1987"), text("English"), text("Sports"),
                    text("Russia,Ekaterinburg"), text("NCR Noida"));
        });
    }
}
