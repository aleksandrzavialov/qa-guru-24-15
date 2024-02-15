package data;

import com.github.javafaker.Faker;
import models.Student;

import java.io.File;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StudentData {

    public static Faker faker = new Faker();
    public static final int minimumStudentAge = 16;
    public static final String[] subjectList =
            new String[]{"Maths", "Accounting", "Arts", "Social Studies", "Physics", "Chemistry"};
    public static final String[] hobbiesList =
            new String[] { "Sports","Reading", "Music" };
    public static String[] statesList = new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    public static Map<String, String[]> statesToCityMap = new HashMap<>();
    private static final String name = getRandomName();
    private static final String lastName = getRandomLastName();
    private static final String email = getRandomEmail(name, lastName);
    private static final String gender = getRandomGender();
    private static final String phone = getRandomPhone();
    private static final int yearOfBirth = getRandomYear(minimumStudentAge);
    private static final String monthOfBirth = getRandomMonth();
    private static final String dayOfBirth = getRandomDayOfMonth(monthOfBirth);
    private static final String[] subjects = getRandomSubjectList();
    private static final String[] hobbies = getRandomHobbiesList();
    public static final String picture = getRandomPictureFromFolder();
    private static final String address = getRandomAddress();
    private static final String state = getRandomState();
    public static final String city = fillCitiesListAndGetRandomCity(state);

    public static Student studentFullData = new Student(name, lastName, email, gender, phone, yearOfBirth,
            monthOfBirth, dayOfBirth, subjects, hobbies, picture, address, state, city);

    public static String getRandomName() {

        return faker.name().firstName();
    }

    public static String getRandomLastName() {

        return faker.name().lastName();
    }

    public static String getRandomPictureFromFolder () {
        File folder = new File("src/test/resources/img");
        File[] listOfFiles = folder.listFiles();
        try {
            return listOfFiles[(int)(Math.random() * listOfFiles.length) ].getName();
        } catch (NullPointerException e) {
            throw new RuntimeException("NullPointerException: The list of files is null", e);
        } catch (SecurityException e) {
            throw new RuntimeException("Security exception while accessing files", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    public static String getRandomEmail(String name, String lastName) {
        String template = faker.internet().emailAddress();
        return template.replace(template.substring(0, template.indexOf('@') - (int) (Math.random() * 3)),
                new StringBuilder().append(name).append(lastName));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return faker.options().option(genders);
    }

    public static String getRandomPhone(){
        return faker.number().digits(10);
    }

    public static int getRandomYear(int minStudentAge){
        //student cannot be younger than minimum age for entering university
        return faker.number().numberBetween(1900, Year.now().getValue() - minStudentAge);
    }

    public static String getRandomMonth(){
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public static String getRandomDayOfMonth(String month){
        Month javaMonth = Month.valueOf(month.toUpperCase());
        int daysInMonth = javaMonth.length(true);
        return Integer.toString(faker.number().numberBetween(1, daysInMonth));
    }

    public static String[] getRandomSelectionFromList(String[] list){
        int numberOfElements = (int) ((Math.random() * list.length) + 1);
        String[] studentList = new String[numberOfElements];
        for (int i = 0; i < numberOfElements;){
            int randomIndex = (int) (Math.random() * numberOfElements);
            String randomSubject = list[randomIndex];
            if (!Arrays.asList(studentList).contains(randomSubject)){
                studentList[i] = randomSubject;
                i++;
            }
        }
        return studentList;
    }

    public static String[] getRandomSubjectList(){
        return getRandomSelectionFromList(subjectList);
    }

    public static String[] getRandomHobbiesList(){
        return getRandomSelectionFromList(hobbiesList);
    }

    public static String getRandomAddress(){
        return faker.address().fullAddress();
    }

    public static String getRandomState() {
        return statesList[faker.number().numberBetween(0, statesList.length)];
    }

    public static String fillCitiesListAndGetRandomCity(String state){
        statesToCityMap.put(statesList[0], new String[]{"Delphi", "Gurgaon", "Noida"});
        statesToCityMap.put(statesList[1], new String[]{"Agra", "Lucknow", "Merrut"});
        statesToCityMap.put(statesList[2], new String[]{"Karnal", "Panipat"});
        statesToCityMap.put(statesList[3], new String[]{"Jaipur", "Jaiselmer"});
        String[] cityArray = statesToCityMap.get(state);

        return cityArray[faker.number().numberBetween(0, cityArray.length)];

    }

}
