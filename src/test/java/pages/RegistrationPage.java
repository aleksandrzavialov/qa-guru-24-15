package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import models.Student;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.CalendarComponent;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            formWrapper = $(".practice-form-wrapper"),
            acceptCookies = $("[aria-label=Consent]");

    CalendarComponent calendarComponent = new CalendarComponent();

    @Step("Open registration page")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        formWrapper.shouldHave(text("Student Registration Form"));
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.of(3000, ChronoUnit.SECONDS)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        $("#fixedban").shouldBe(visible);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        if (acceptCookies.exists()) {
            acceptCookies.shouldBe(visible).click();
        }
        return this;
    }

    @Step("Set first name")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Set last name")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Set email")
    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Set gender")
    public RegistrationPage setGender(String gender) {
        genderWrapper.$(byText(gender)).parent().click();

        return this;
    }

    @Step("Set phone number")
    public RegistrationPage setUserNumber(String phone) {
        userNumberInput.setValue(phone);

        return this;
    }

    @Step("Set birth date")
    public RegistrationPage setDateOfBirth(String day, String month, int year) {
        calendarComponent.setCalendarDate(year, month, day);

        return this;
    }

    @Step("Set all subjects at once")
    public RegistrationPage setAllSubjects(String[] subjects) {
        for (String subject : subjects) {
            subjectInput.setValue(subject).pressEnter();
        }

        return this;
    }

    @Step("Set hobbies")
    public RegistrationPage setHobbies(String[] hobbiesList) {
        for (String hobby : hobbiesList) {
            $(byText(hobby)).parent().click();
        }
        return this;
    }

    @Step("Upload picture")
    public RegistrationPage uploadPicture(String picture) {
        pictureInput.uploadFromClasspath("img/"+picture);
        return this;
    }

    @Step("Set address")
    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    @Step("Set state")
    public RegistrationPage setState(String state) {
        stateInput.click();
        stateInput.$(byText(state)).click();

        return this;

    }

    @Step("Set city")
    public RegistrationPage setCity(String city) {
        cityInput.click();
        cityInput.$(byText(city)).shouldBe(visible).click();

        return this;
    }

    @Step("Submit result")
    public void submitResult() {
        submitButton.click();
    }

    @Step("Fill student data")
    public void fillAllStudentFields(Student student) {
        setFirstName(student.name);
        setLastName(student.lastName);
        setEmail(student.email);
        setGender(student.gender);
        setUserNumber(student.phone).
                setDateOfBirth(student.dayOfBirth, student.monthOfBirth, student.yearOfBirth).
                setAllSubjects(student.subjects).
                setHobbies(student.hobbies).
                uploadPicture(student.picture).
                setAddress(student.address).
                setState(student.state).
                setCity(student.city);
    }


}