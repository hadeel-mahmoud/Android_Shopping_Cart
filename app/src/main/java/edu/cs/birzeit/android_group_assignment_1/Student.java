package edu.cs.birzeit.android_group_assignment_1;

public class Student {
    private String firstName="";
    private String lastName="";

    private String email="";
    private String dateOfBirth="";
    private String address="";
    private String grade="";
    private String gender="";




    public Student(String name, String lName,String Email,String DateOfBirth,String Address,String Grade,String Gender) {
        firstName = name;
        lastName = lName;
        email = Email;
        dateOfBirth = DateOfBirth;
        address = Address;
        grade = Grade;
        gender = Gender;

    }



//    public Student(String FirstName, String LName, String Email, String DateOfBirth) {
//        firstName = FirstName;
//        lastName = LName;
//        email = Email;
//        dateOfBirth = DateOfBirth;
//
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setFirstName(String description) {
        this.lastName = description;
    }
    public void setLastName(String description) {
        this.lastName = description;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String DateOfBirth) {
        this.dateOfBirth = DateOfBirth;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }


}
