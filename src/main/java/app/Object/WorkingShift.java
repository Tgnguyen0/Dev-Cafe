package app.Object;

import java.time.LocalDate;

public class WorkingShift {
    public String shiftId;
    public String empId;
    public LocalDate dayWorked;
    public int startHours;
    public int endHours;
    public double revenue;

    public WorkingShift() {
    }

    public WorkingShift(String shiftId, String empId, LocalDate dayWorked, int startHours, int endHours,
            double revenue) {
        this.shiftId = shiftId;
        this.empId = empId;
        this.dayWorked = dayWorked;
        this.startHours = startHours;
        this.endHours = endHours;
        this.revenue = revenue;
    }

    public String getShiftId() {
        return this.shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getEmpId() {
        return this.empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public LocalDate getDayWorked() {
        return this.dayWorked;
    }

    public void setDayWorked(LocalDate dayWorked) {
        this.dayWorked = dayWorked;
    }

    public int getStartHours() {
        return this.startHours;
    }

    public void setStartHours(int startHours) {
        this.startHours = startHours;
    }

    public int getEndHours() {
        return this.endHours;
    }

    public void setEndHours(int endHours) {
        this.endHours = endHours;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
