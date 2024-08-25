package app.Object;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable {
    private String id;
    private String name;
    private LocalDate dob;
    private int points;
    private String phone;

    public Member() {
    }
    
    public Member(String id, String name, LocalDate dob, int points, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.points = points;
        this.phone = phone;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
