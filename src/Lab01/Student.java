package Lab01;

public class Student {
    private String firstName;
    private String secondName;
    private String email;
    private Integer credits;
    private Double gpa;

    public Student(String firstName, String secondName, String email, Integer credits, Double gpa){
        this.setFirstName(firstName);
        this.setSecondName(secondName);
        this.setEmail(email);
        this.setCredits(credits);
        this.setGpa(gpa);
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getSecondName() + " with email " + this.getEmail() + " has " +
                this.getCredits() + " credits and a GPA of " + this.getGpa() + ".\n";
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}