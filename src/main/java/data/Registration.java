package data;

import java.time.LocalDate;

public class Registration {
    private int id;
    private int userId;
    private int courseId;
    private LocalDate registrationDate;

    public Registration(int id, int userId, int courseId, LocalDate registrationDate){
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
