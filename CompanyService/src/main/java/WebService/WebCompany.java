/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Domain.Department;
import Service.CompanyService;
import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author Jelle
 */
@WebService
public class WebCompany {
    
    private CompanyService service = new CompanyService();
    
    public ArrayList<Department> getDepartments()
    {
        return service.getDepartments();
    }
    
    public Department getDepartment(int number)
    {
        return service.getDepartment(number);
    }
    
    public void addDepartment(Department dep)
    {
        service.addDepartment(dep);
    }
    
    public void removeDepartment(Department dep)
    {
        service.removeDepartment(dep);
    }
}
