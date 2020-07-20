package cloud.autotests.tests.web_shop_test;

import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.PressEnter;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import cloud.autotests.utils.FileUtils;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
//import static helpers.Environment.*;


@Epic("QA.GURU automation course")
@Story("Demowebshop tests")
@Tag("webshoptest")
public class DemowebshopTests extends TestBase {

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));

        if (System.getProperty("selenoid_url") != null) {
            Configuration.remote = "http://" + System.getProperty("selenoid_url") + ":4444/wd/hub";
        }

    }

    @Test
    @DisplayName("Регистрация в онлайн магазине")
    @Description("Попытка создать уже существующую учетную запись в онлайн магазине")
    void uNSuccessfulRegistration() {
        step("Заходим на сайт", () -> {
            open("http://demowebshop.tricentis.com/");
        });
        step("Находим название", () -> {
            $("html").shouldHave(text("Demo Web Shop"));
        });
        step("Открываем регистрационную форму", () -> {
            $(byText("Register")).click();
        });
        step("Заполняем регистрационную форму", () -> {
            $("#gender-male").click();
            $("#FirstName").setValue("Victor");
            $("#LastName").setValue("Dmitrich");
            $("#Email").setValue("mazafakinshit@yo.ru");
            $("#Password").setValue("indahouse");
            $("#ConfirmPassword").setValue("indahouse");
            $("#register-button").click();
        });
        step("Проверка наличия уже созданной учетной записи", () -> {
            $("html").shouldHave(text("The specified email already exists"));
        });

    }

    @Test
    @DisplayName("Logging in")
    @Description("Успешная авторизация в онлайн магазине")
    void successfulLogin() {
        step("Заходим в аккаунт на сайте", () -> {
            open("http://demowebshop.tricentis.com/");
            $(byText("Log in")).click();
            $("html").shouldHave(text("Welcome, Please Sign In!"));
            $("#Email").setValue("mazafakinshit@yo.ru");
            $("#Password").setValue("indahouse").pressEnter();
        });
        step("Проверяем успешную авторизацию", () -> {
            $("html").shouldHave(text("mazafakinshit@yo.ru"));
        });

    }

    @Test
    @DisplayName("Product selection")
    @Description("Проверка добавления товаров в корзину и редактирование учетной записи для оформления последующей доставки")
    void successfulProductSelection() {
        step("Заходим в аккаунт на сайте", () -> {
            open("http://demowebshop.tricentis.com/");
            $(byText("Log in")).click();
            $("html").shouldHave(text("Welcome, Please Sign In!"));
            $("#Email").setValue("mazafakinshit@yo.ru");
            $("#Password").setValue("indahouse").pressEnter();
        });
        step("Проверяем успешную авторизацию", () -> {
            $("html").shouldHave(text("mazafakinshit@yo.ru"));
        });
        step("Выбираем компьютер", () -> {
            $(byLinkText("Computers")).click();
            $(byLinkText("Desktops")).click();
            $(byLinkText("Build your own expensive computer")).click();
            $("html").shouldHave(text("Build your own expensive computer"));
            $("#product_attribute_74_5_26_82").click();
            $("#product_attribute_74_6_27_84").scrollIntoView(true).click();
            $("#product_attribute_74_3_28_87").click();
            $("#product_attribute_74_8_29_90").click();
            $("#product_attribute_74_8_29_89").click();
            $("#product_attribute_74_8_29_88").click();
            $("#add-to-cart-button-74").click();
        });
        step("Добавление товара в корзину", () -> {
            $("#bar-notification").shouldHave(text("The product has been added to your shopping cart"));
        });
        sleep(3000);
        step("Выбираем джинсы", () -> {
            $(byLinkText("Apparel & Shoes")).scrollIntoView(true).click();
            $(byLinkText("Blue Jeans")).click();
            $("html").shouldHave(text("Stylish Jeans"));
        });
        step("Добавление товара в корзину", () -> {
            $("#add-to-cart-button-36").click();
            $("#bar-notification").shouldHave(text("The product has been added to your shopping cart"));
        });
        step("Внесение изменений в учетную запись", () -> {
            $(byLinkText("mazafakinshit@yo.ru")).click();
            $(byText("Addresses")).click();
            $(".add-address-button").click();
            $("#Address_FirstName").setValue("Victor");
            $("#Address_LastName").setValue("Dmitrich");
            $("#Address_Email").setValue("mazafakinshit@yo.ru");
            $("#Address_Company").setValue("Boring Company");
            $("#Address_CountryId").click();
            $(byText("Albania")).click();
            $("#Address_City").setValue("Tirana");
            $("#Address_Address1").setValue("Yunosti 4");
            $("#Address_ZipPostalCode").setValue("333111");
            $("#Address_PhoneNumber").setValue("333000");
            $(".save-address-button").click();
        });
        step("Успешное добавление контактных данных", () -> {
            $("html").shouldHave(text("Victor Dmitrich"));
        });

    }

    @Test
    @DisplayName("Sample download")
    @Description("Проверка наличия текста в скачанном файле")
    void successfulTextFileCheck() {
        String expectedFileText = "This is a sample download";
        step("Заходим в аккаунт на сайте", () -> {
            open("http://demowebshop.tricentis.com/");
            $(byText("Log in")).click();
            $("html").shouldHave(text("Welcome, Please Sign In!"));
            $("#Email").setValue("mazafakinshit@yo.ru");
            $("#Password").setValue("indahouse").pressEnter();
        });
        step("Проверяем наличие файла для скачивания", () -> {
            $(byLinkText("Digital downloads")).click();
            $$(byText("Music 2")).get(1).click();
            $("body").shouldHave(text("2nd album - country music"));
        });
        step("Скачиваем текстовый файл", () -> {
            File actualFile = $("[href='/download/sample/52']").download();
            String actualFileText = new FileUtils().readStringFromFile(actualFile.getPath());
            System.out.println("Actual text from file: \n" + actualFileText);
        });

    }

    @Test
    @DisplayName("проверка наличия товаров в корзине")
    @Description("check for selected items")
    void successfulShoppingCartCheck() {
        step("Заходим в аккаунт на сайте", () -> {
            open("http://demowebshop.tricentis.com/");
            $(byText("Log in")).click();
            $("html").shouldHave(text("Welcome, Please Sign In!"));
            $("#Email").setValue("mazafakinshit@yo.ru");
            $("#Password").setValue("indahouse").pressEnter();
        });
        step("Заходим в корзину", () -> {
            $(byText("Shopping cart")).click();
        });
        step("Проверяем наличие выбранных товаров", () -> {
            $(".cart").shouldHave(text("Build your own expensive computer"));
            $(".cart").shouldHave(text("Blue Jeans"));
        });
        step("Соглашаемся с правилами оказания услуг и оформляем заказ", () -> {
            $("#termsofservice").click();
            $("#checkout").click();
            $("html").shouldHave(text("Billing address"));
        });
        step("Оформляем заказ и доставку", () -> {
            $(".new-address-next-step-button").click();
            $("#PickUpInStore").click();
            $("#shipping-buttons-container > input").click();
            $("#paymentmethod_0").click();
            $(".payment-method-next-step-button").click();
            $("html").shouldHave(text("You will pay by COD"));
            $(".payment-info-next-step-button").click();
            $("html").shouldHave(text("Build your own expensive computer"),text("Blue Jeans"));
            $("#checkout-step-confirm-order").click();
        });

    }

}
