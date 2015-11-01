/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyserviceclient;

import webservice.Department;
import webservice.Employee;

/**
 *
 * @author Jelle
 */
public class CompanyServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                   
        System.out.println("getDepartments");
        for (Department d : getDepartments())
        {
            System.out.format("Number %d - Name %s\n", d.getDepNumber(), d.getDepName());
        }
        
        System.out.println("getDepartment (0)");
        Department dept = getDepartment(0);
        System.out.format("Number %d - Name %s\n", dept.getDepNumber(), dept.getDepName());
        
        System.out.println("removeDepartment(0)");
        removeDepartment(dept);
        for (Department d : getDepartments())
        {
            System.out.format("Number %d - Name %s\n", d.getDepNumber(), d.getDepName());
        }
        
//        System.out.println("getEmployees (1)");
//        Department dept1 = getDepartment(1);
//        for (Employee e : getEmployees(dept1))
//        {
//            System.out.format("Firstname %s - Lastname %s\n", e.getFirstname(), e.getLastname());
//        }
        
    }

    private static void addDepartment(webservice.Department arg0) {
        webservice.WebCompanyService service = new webservice.WebCompanyService();
        webservice.WebCompany port = service.getWebCompanyPort();
        port.addDepartment(arg0);
    }

    private static void removeDepartment(webservice.Department arg0) {
        webservice.WebCompanyService service = new webservice.WebCompanyService();
        webservice.WebCompany port = service.getWebCompanyPort();
        port.removeDepartment(arg0);
    }

    private static Department getDepartment(int arg0) {
        webservice.WebCompanyService service = new webservice.WebCompanyService();
        webservice.WebCompany port = service.getWebCompanyPort();
        return port.getDepartment(arg0);
    }

    private static java.util.List<webservice.Department> getDepartments() {
        webservice.WebCompanyService service = new webservice.WebCompanyService();
        webservice.WebCompany port = service.getWebCompanyPort();
        return port.getDepartments();
    }

    private static java.util.List<webservice.Employee> getEmployees(webservice.Department arg0) {
        webservice.WebCompanyService service = new webservice.WebCompanyService();
        webservice.WebCompany port = service.getWebCompanyPort();
        return port.getEmployees(arg0);
    }
       
}
