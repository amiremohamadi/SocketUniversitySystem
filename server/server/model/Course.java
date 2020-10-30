package model;

public class Course {
    private String name;
    private int unit;
    private float grade;

    public Course(String name, int unit, float grade) {
        this.setName(name);
        this.setUnit(unit);
        this.setGrade(grade);
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(int unit) {
        if (unit < 0 || unit > 4) {
            // throw exception
        }
        this.unit = unit;
    }

    public void setGrade(float grade) {
        if (grade > 20 || grade < 0) {
            // throw exception
        }
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public int getUnit() {
        return this.unit;
    }

    public float getGrade() {
        return this.grade;
    }
}
