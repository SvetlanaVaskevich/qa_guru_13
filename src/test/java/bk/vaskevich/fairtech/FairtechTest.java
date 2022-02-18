package bk.vaskevich.fairtech;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class FairtechTest extends TestBase {

    @DisplayName("Проверка заголовка главной страницы")
    @Test
    public void openPageTest() {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Проверяем заголовок на странице",()->{
            $(".header__title").shouldHave(Condition.text("Современные и безопасные решения для бизнеса"));
        });
    }

    @DisplayName("Проверка работы меню")
    @Test
    public void checkMenuTest() {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Открываем ссылки в меню", () -> {
            String[] menu_name = new String[]{"О компании","Решения","Вакансии","Контакты"};
            ElementsCollection elements = $$(".menu__item a[href]");
            for (int i = 0; i< elements.size(); i++){
                elements.get(i).click();
                $$("h2[class*='title title']").get(i).shouldHave(Condition.text(menu_name[i]));
            }
        });
    }

    @DisplayName("Проверка номера телефона компании")
    @Test
    public void checkPhoneNumberTest() {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Проверяем номер телефона компании",() -> {
            $$("a.phone__number").last().click();
            $$("a.phone__number").last().shouldHave(text("+7 495 145 98 70"));
        });
    }

    @DisplayName("Проверка кнопки Подробнее")
    @Test
    public void checkButtonPodrobneeTest() {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Проверяем кнопку Подробнее",() -> {
            $("a.header__button").click();
            $("section.decisions").$("p").shouldHave(text("Компания Fairtech (ООО “Честные технологии”)"));
        });
    }

    @DisplayName("Проверка кноки листания слайдов вперед")
    @Test
    public void checkButtonSlickNextTest() {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Проверяем кнопку Следующий слайд",() -> {
            $("button.slick-next.slick-arrow").click();
            $$("div.advantages__item.item.slick-slide.slick-active").last().
                    $(withText("методологии разработки — Agile, Scrum, Kanban.")).shouldBe(visible);
        });
    }

    @DisplayName("Проверка кнопки листания слайдов назад")
    @Test
    public void checkButtonSlickPreviewTest() {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Проверяем кнопку Предыдущий слайд",() -> {
            $("button.slick-next.slick-arrow").click();
            $("button.slick-prev.slick-arrow").click();
            $("div.slick-track").$("div").
                    $(withText("Мы обеспечиваем полный цикл разработки программного обеспечения (ПО)")).shouldBe(visible);
        });
    }

    @CsvSource(value = {
            "/vacancy/php-developer,Разработчик PHP / PHP Developer",
            "/vacancy/systems-analyst,Системный аналитик ",
            "/vacancy/analyst-sql-tableau,Аналитик (SQL/Tableau)"})
    @DisplayName("Проверка раздела Вакансии")
    @ParameterizedTest
    public void checkVacanciesTest(String link,String expected) {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Проверяем ссылку вакансии",() -> {
            $("a[href='"+link+"']").click();
            $("h1.vacancy__title").shouldHave(text(expected));
        });
    }

    Faker faker = new Faker();

    String surname = faker.name().lastName();
    String name = faker.name().firstName();
    String mobilePhone = faker.phoneNumber().subscriberNumber(10);


    @DisplayName("Проверка формы заполнения данных на вакансию")
    @Test
    public void checkResponseTest() {
        step("Открываем страницу", () -> {
            open(baseUrl);
        });
        step("Заполняем фамилию",() -> {
            $("input[name='Feedback[surname]']").setValue(surname);
        });
        step("Заполняем имя",() -> {
            $("input[name='Feedback[name]']").setValue(name);
        });
        step("Заполняем отчество",() -> {
            $("input[name='Feedback[patronymic]']").setValue("Ивановна");
        });
        step("Заполняем мобильный телефон",() -> {
            $("input[name='Feedback[phone]']").setValue(mobilePhone);
        });
        step("Прикрепляем резюме",() -> {
            $("input[name='Feedback[file]']").uploadFromClasspath("resume.pdf");
        });
        step("Нажимаем кнопку Отправить",() -> {
            $("button.vacancy__btn.btn.btn--dark-blue").click();
        });
        step("Проверяем результат отправки",() -> {
            $("div[role='alert']").shouldBe(visible);
        });
    }
}
