package data;

import controller.LessonMaintainingController;

import java.util.ArrayList;

public class Courses {
    private String durationInMonths, name, description;
    private ArrayList<Lessons> lessons;
    private int lvlOfDificulty;
    private User Tutor;

    public Courses(String durationInMonths, String name, String description, int lvlOfDificulty, User tutor) {
        this.durationInMonths = durationInMonths;
        this.name = name;
        this.description = description;
        this.lvlOfDificulty = lvlOfDificulty;
        Tutor = tutor;
    }

    public Courses(String duracion, String nombre, String descripcion, int nivel) {//this is temporal remove
        this.durationInMonths = durationInMonths;
        this.name = name;
        this.description = description;
        this.lvlOfDificulty = lvlOfDificulty;
    }


    public String getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(String durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLvlOfDificulty() {
        return lvlOfDificulty;
    }

    public void setLvlOfDificulty(int lvlOfDificulty) {
        this.lvlOfDificulty = lvlOfDificulty;
    }

    public ArrayList<Lessons> getLessons() {
        return lessons;
    }

    public User getTutor() {
        return Tutor;
    }

    public void setTutor(User tutor) {
        Tutor = tutor;
    }

    public void setLessons(ArrayList<Lessons> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "durationInMonths='" + durationInMonths + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lvlOfDificulty=" + lvlOfDificulty +
                '}';
    }
}
