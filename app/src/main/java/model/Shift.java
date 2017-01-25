package model;

import java.io.Serializable;

public class Shift implements Serializable{

    private int id;
    private String group;

    public Shift() {
    }

    public Shift(int id, String group) {
        this.id = id;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shift shift = (Shift) o;

        return id == shift.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", group='" + group + '\'' +
                '}';
    }
}
