package models;

public class Student {

    public String name, lastName,  email, gender, phone, monthOfBirth, dayOfBirth, picture, address, state, city;
    public int yearOfBirth;
    public String[] subjects, hobbies;

    public Student(String name, String lastName, String email, String gender, String phone, int yearOfBirth,
                   String monthOfBirth, String dayOfBirth, String[] subjects, String[] hobbies, String picture,
                   String address, String state, String city) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dayOfBirth = dayOfBirth;
        this.subjects = subjects;
        this.hobbies = hobbies;
        this.picture = picture;
        this.address = address;
        this.state = state;
        this.city = city;

    }


}
