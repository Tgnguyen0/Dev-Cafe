package app.AppFunctions;

import java.util.ArrayList;

import app.Object.Employee;

public class EmployeeManagement {
    private ArrayList<Employee> employeeList;

    public EmployeeManagement() {
        this.employeeList = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public boolean addEmployee(Employee newEmployee) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(newEmployee.getId())) {
                return false;
            }
        }

        return employeeList.add(newEmployee);
    }

    public boolean addAllEmployee(ArrayList<Employee> newList) {
        return employeeList.addAll(newList);
    }

    public boolean deleteEmployee(String id) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(id)) {
                return employeeList.remove(id);
            }
        }

        return false;
    }
}
