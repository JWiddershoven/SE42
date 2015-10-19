
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddResponse_QNAME = new QName("http://webService/", "addResponse");
    private final static QName _Minus_QNAME = new QName("http://webService/", "minus");
    private final static QName _Add_QNAME = new QName("http://webService/", "add");
    private final static QName _NegativeNumberException_QNAME = new QName("http://webService/", "NegativeNumberException");
    private final static QName _TimesResponse_QNAME = new QName("http://webService/", "timesResponse");
    private final static QName _Times_QNAME = new QName("http://webService/", "times");
    private final static QName _DivideResponse_QNAME = new QName("http://webService/", "divideResponse");
    private final static QName _Divide_QNAME = new QName("http://webService/", "divide");
    private final static QName _MinusResponse_QNAME = new QName("http://webService/", "minusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link Minus }
     * 
     */
    public Minus createMinus() {
        return new Minus();
    }

    /**
     * Create an instance of {@link Times }
     * 
     */
    public Times createTimes() {
        return new Times();
    }

    /**
     * Create an instance of {@link NegativeNumberException }
     * 
     */
    public NegativeNumberException createNegativeNumberException() {
        return new NegativeNumberException();
    }

    /**
     * Create an instance of {@link TimesResponse }
     * 
     */
    public TimesResponse createTimesResponse() {
        return new TimesResponse();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link Divide }
     * 
     */
    public Divide createDivide() {
        return new Divide();
    }

    /**
     * Create an instance of {@link MinusResponse }
     * 
     */
    public MinusResponse createMinusResponse() {
        return new MinusResponse();
    }

    /**
     * Create an instance of {@link DivideResponse }
     * 
     */
    public DivideResponse createDivideResponse() {
        return new DivideResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Minus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "minus")
    public JAXBElement<Minus> createMinus(Minus value) {
        return new JAXBElement<Minus>(_Minus_QNAME, Minus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NegativeNumberException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "NegativeNumberException")
    public JAXBElement<NegativeNumberException> createNegativeNumberException(NegativeNumberException value) {
        return new JAXBElement<NegativeNumberException>(_NegativeNumberException_QNAME, NegativeNumberException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "timesResponse")
    public JAXBElement<TimesResponse> createTimesResponse(TimesResponse value) {
        return new JAXBElement<TimesResponse>(_TimesResponse_QNAME, TimesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Times }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "times")
    public JAXBElement<Times> createTimes(Times value) {
        return new JAXBElement<Times>(_Times_QNAME, Times.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivideResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "divideResponse")
    public JAXBElement<DivideResponse> createDivideResponse(DivideResponse value) {
        return new JAXBElement<DivideResponse>(_DivideResponse_QNAME, DivideResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Divide }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "divide")
    public JAXBElement<Divide> createDivide(Divide value) {
        return new JAXBElement<Divide>(_Divide_QNAME, Divide.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MinusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "minusResponse")
    public JAXBElement<MinusResponse> createMinusResponse(MinusResponse value) {
        return new JAXBElement<MinusResponse>(_MinusResponse_QNAME, MinusResponse.class, null, value);
    }

}
