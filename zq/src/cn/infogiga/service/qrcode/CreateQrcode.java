
package cn.infogiga.service.qrcode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="in0" type="{http://qrcode.service.infogiga.cn}QrcodeBean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "in0"
})
@XmlRootElement(name = "createQrcode")
public class CreateQrcode {

    @XmlElement(required = true, nillable = true)
    protected QrcodeBean in0;

    /**
     * Gets the value of the in0 property.
     * 
     * @return
     *     possible object is
     *     {@link QrcodeBean }
     *     
     */
    public QrcodeBean getIn0() {
        return in0;
    }

    /**
     * Sets the value of the in0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link QrcodeBean }
     *     
     */
    public void setIn0(QrcodeBean value) {
        this.in0 = value;
    }

}
