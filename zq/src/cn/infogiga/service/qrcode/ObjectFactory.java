
package cn.infogiga.service.qrcode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.infogiga.service.qrcode package. 
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

    private final static QName _QrcodeBeanQrcode_QNAME = new QName("http://qrcode.service.infogiga.cn", "qrcode");
    private final static QName _QrcodeBeanFormat_QNAME = new QName("http://qrcode.service.infogiga.cn", "format");
    private final static QName _QrcodeBeanQrcodePicName_QNAME = new QName("http://qrcode.service.infogiga.cn", "qrcodePicName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.infogiga.service.qrcode
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IsNewResponse }
     * 
     */
    public IsNewResponse createIsNewResponse() {
        return new IsNewResponse();
    }

    /**
     * Create an instance of {@link QrcodeBean }
     * 
     */
    public QrcodeBean createQrcodeBean() {
        return new QrcodeBean();
    }

    /**
     * Create an instance of {@link CreateQrcode1Response }
     * 
     */
    public CreateQrcode1Response createCreateQrcode1Response() {
        return new CreateQrcode1Response();
    }

    /**
     * Create an instance of {@link CreateQrcode1 }
     * 
     */
    public CreateQrcode1 createCreateQrcode1() {
        return new CreateQrcode1();
    }

    /**
     * Create an instance of {@link CreateQrcodeResponse }
     * 
     */
    public CreateQrcodeResponse createCreateQrcodeResponse() {
        return new CreateQrcodeResponse();
    }

    /**
     * Create an instance of {@link CreateQrcode }
     * 
     */
    public CreateQrcode createCreateQrcode() {
        return new CreateQrcode();
    }

    /**
     * Create an instance of {@link IsNew }
     * 
     */
    public IsNew createIsNew() {
        return new IsNew();
    }

    /**
     * Create an instance of {@link ArrayOfQrcodeBean }
     * 
     */
    public ArrayOfQrcodeBean createArrayOfQrcodeBean() {
        return new ArrayOfQrcodeBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qrcode.service.infogiga.cn", name = "qrcode", scope = QrcodeBean.class)
    public JAXBElement<String> createQrcodeBeanQrcode(String value) {
        return new JAXBElement<String>(_QrcodeBeanQrcode_QNAME, String.class, QrcodeBean.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qrcode.service.infogiga.cn", name = "format", scope = QrcodeBean.class)
    public JAXBElement<String> createQrcodeBeanFormat(String value) {
        return new JAXBElement<String>(_QrcodeBeanFormat_QNAME, String.class, QrcodeBean.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qrcode.service.infogiga.cn", name = "qrcodePicName", scope = QrcodeBean.class)
    public JAXBElement<String> createQrcodeBeanQrcodePicName(String value) {
        return new JAXBElement<String>(_QrcodeBeanQrcodePicName_QNAME, String.class, QrcodeBean.class, value);
    }

}
