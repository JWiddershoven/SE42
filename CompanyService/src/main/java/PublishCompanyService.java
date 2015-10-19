
import WebService.WebCompany;
import javax.xml.ws.Endpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jelle
 */
public class PublishCompanyService {

    private static final String url = "http://localhost:8080/CompanyService";

    public static void main(String[] args) {
        Endpoint.publish(url, new WebCompany());
        System.out.println(url + "?wsdl");
    }
}
