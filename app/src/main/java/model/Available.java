package model;

import java.io.Serializable;
import java.util.List;

public class Available implements Serializable{

    private int id;
    private List<Day> days;
    private Teacher teacher;

    public Available() {
    }

    public Available(List<Day> days, Teacher teacher) {
        this.days = days;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Available available = (Available) o;

        return id == available.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Available{" +
                "id=" + id +
                ", days=" + days +
                ", teacher=" + teacher +
                '}';
    }
}
