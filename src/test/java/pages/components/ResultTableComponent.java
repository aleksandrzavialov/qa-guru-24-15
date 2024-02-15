package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import models.Student;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    private final SelenideElement tableTitle = $("#example-modal-sizes-title-lg"),
            tableContent = $(".table-responsive"),
            closeTableButton = $("#closeLargeModal");

    @Step("Check result table UI")
    public ResultTableComponent checkResultTableUI() {
        tableTitle.shouldHave(text("Thanks for submitting the form"));
        closeTableButton.shouldBe(visible);

        return this;
    }


    public ResultTableComponent checkResult(String key, String value) {
        String displayValue = value.equals(" ") ? "empty" : value;
        Allure.step("Check that " + key + " has " + displayValue + " value.");
        tableContent.$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }

    @Step("Check student data in submit form")
    public void checkAllStudentFields(Student student) {
        checkResult("Student Name", student.name + " " + student.lastName).
                checkResult("Student Email", student.email).
                checkResult("Gender", student.gender).
                checkResult("Mobile", student.phone).
                checkResult("Date of Birth",
                        student.dayOfBirth + " " + student.monthOfBirth + "," + student.yearOfBirth).
                checkResult("Subjects", String.join(", ", student.subjects)).
                checkResult("Hobbies", String.join(", ", student.hobbies)).
                checkResult("Picture", student.picture).
                checkResult("Address", student.address).
                checkResult("State and City", student.state + " " + student.city);
    }
}
