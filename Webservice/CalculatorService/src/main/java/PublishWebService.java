import javax.xml.ws.Endpoint;
import webService.WebCalculator;

public class PublishWebService {

    private static final String url = "http://localhost:8090/WebCalculator";

    public static void main(String[] args) {
        Endpoint.publish(url, new WebCalculator());
        System.out.println(url + "?wsdl");
    }
}
