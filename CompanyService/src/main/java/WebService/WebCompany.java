/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Domain.Department;
import Domain.Employee;
import Service.CompanyService;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Jelle
 */
@WebService
public class WebCompany {
    
    private CompanyService service = new CompanyService();
    
    @WebMethod
    public ArrayList<Department> getDepartments()
    {
        return service.getDepartments();
    }
    
    @WebMethod
    public Department getDepartment(int number)
    {
        return service.getDepartment(number);
    }
    
    @WebMethod
    public void addDepartment(Department dep)
    {
        service.addDepartment(dep);
    }
    
    @WebMethod
    public void removeDepartment(Department dep)
    {
        service.removeDepartment(dep);
    }
    
    @WebMethod
    public ArrayList<Employee> getEmployees(Department dep)
    {
        return dep.getEmployees();
    }
}
