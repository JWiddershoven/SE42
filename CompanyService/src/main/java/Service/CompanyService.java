/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Domain.Company;
import Domain.Department;
import Domain.Employee;
import java.util.ArrayList;

/**
 *
 * @author Jelle
 */
public class CompanyService {
    
    private Company comp = new Company("Philips");
    
    public void addDepartment(Department dep)
    {
        comp.addDepartment(dep);
    }
    
    public void removeDepartment(Department dep)
    {
        comp.removeDepartment(dep);
    }
    
    public ArrayList<Department> getDepartments()
    {
       return comp.getDepartments();
    }
    
    public Department getDepartment(int number)
    {
        return comp.getDepartment(number);
    }
}
