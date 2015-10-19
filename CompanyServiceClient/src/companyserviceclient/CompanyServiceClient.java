/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyserviceclient;

import webservice.Department;

/**
 *
 * @author Jelle
 */
public class CompanyServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
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
    
}
