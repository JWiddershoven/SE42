/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;

/**
 *
 * @author Jelle
 */
public class Department {

    private String depName;
    private int depNumber;
    private ArrayList<Employee> employees;

    public Department(String depName, int depNumber) {
        this.depName = depName;
        this.depNumber = depNumber;
        this.employees = new ArrayList<>();
    }

    public Department() {
    }
    

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDepNumber() {
        return depNumber;
    }

    public void setDepNumber(int depNumber) {
        this.depNumber = depNumber;
    }

    public void addEmployee(Employee emp) {
        this.employees.add(emp);
    }

    public void deleteEmployee(Employee emp) {
        if (employees.contains(emp)) {
            employees.remove(emp);
        }
    }

    @Override
    public String toString() {
        return "Nummer: " + this.depNumber + " Naam: " + this.depName;
    }

}
