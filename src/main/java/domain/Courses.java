package domain;

public class Courses {
    private String durationInMonths, name, description;
    private int lvlOfDificulty;

    public Courses(String durationInMonths, String name, String description, int lvlOfDificulty) {
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
