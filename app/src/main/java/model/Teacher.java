package model;

import java.io.Serializable;

public class Teacher implements Serializable{

    private int id, rg;
    private String name;
    private String phone;
    private String language;

    public Teacher() {
    }

    public Teacher(int rg, String name, String phone, String language) {
        this.rg = rg;
        this.name = name;
        this.phone = phone;
        this.language = language;
    }

    public Teacher(int id, int rg, String name, String phone, String language) {
        this.id = id;
        this.rg = rg;
        this.name = name;
        this.phone = phone;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        return rg == teacher.rg;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rg;
        return result;
    }
}
