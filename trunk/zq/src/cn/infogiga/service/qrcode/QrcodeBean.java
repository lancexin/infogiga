
package cn.infogiga.service.qrcode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QrcodeBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QrcodeBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcodePicName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scale" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QrcodeBean", propOrder = {
    "format",
    "qrcode",
    "qrcodePicName",
    "scale"
})
public class QrcodeBean {

    @XmlElementRef(name = "format", namespace = "http://qrcode.service.infogiga.cn", type = JAXBElement.class)
    protected JAXBElement<String> format;
    @XmlElementRef(name = "qrcode", namespace = "http://qrcode.service.infogiga.cn", type = JAXBElement.class)
    protected JAXBElement<String> qrcode;
    @XmlElementRef(name = "qrcodePicName", namespace = "http://qrcode.service.infogiga.cn", type = JAXBElement.class)
    protected JAXBElement<String> qrcodePicName;
    protected Integer scale;

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormat(JAXBElement<String> value) {
        this.format = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the qrcode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQrcode() {
        return qrcode;
    }

    /**
     * Sets the value of the qrcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQrcode(JAXBElement<String> value) {
        this.qrcode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the qrcodePicName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQrcodePicName() {
        return qrcodePicName;
    }

    /**
     * Sets the value of the qrcodePicName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQrcodePicName(JAXBElement<String> value) {
        this.qrcodePicName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the scale property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setScale(Integer value) {
        this.scale = value;
    }

}
