package app.Object;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Serializable {
    private String id;
    private String name;
    private LocalDate dob;
    private String phone;
    private String responsibility;

    public Employee() {
    }

    public Employee(String id, String name, LocalDate dob, String phone, String responsibility) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.responsibility = responsibility;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResponsibility() {
        return this.responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }
}

    
