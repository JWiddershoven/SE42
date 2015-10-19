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
public class Company {
    
    private String name;
    private ArrayList<Department> departments;

    public Company(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }
    
    public void addDepartment(Department dep)
    {
        this.departments.add(dep);
    }
    
    public void removeDepartment(Department dep)
    {
        if (departments.contains(dep))
        {
            departments.remove(dep);
        }
    }
    
    public ArrayList<Department> getDepartments()
    {
        return this.departments;
    }
    
    public Department getDepartment(int number)
    {
        for (Department d : departments)
        {
            if (d.getDepNumber() == number)
            {
                return d;
            }
        }
        return null;
    }
}
