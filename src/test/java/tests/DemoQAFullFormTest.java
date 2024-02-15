package tests;

import data.StudentData;
import io.qameta.allure.*;
import models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.ResultTableComponent;

@Tag("full")
public class DemoQAFullFormTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableComponent resultTable = new ResultTableComponent();

    @Test
    @Feature("Register student")
    @Story("Register in complex form with high level of test abstraction")
    @Owner("azavialov")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("It is possible to register student with all possible data and verify registration results")
    void successfulRegisterFullDataTest() {

        Student student = StudentData.studentFullData;

        //Preconditions
        registrationPage.openPage();

        //Form filling
        registrationPage.fillAllStudentFields(student);
        registrationPage.submitResult();

        //Checking submit results
        resultTable.checkResultTableUI().
                checkAllStudentFields(student);
    }
}


