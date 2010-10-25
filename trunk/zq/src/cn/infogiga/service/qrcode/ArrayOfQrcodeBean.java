
package cn.infogiga.service.qrcode;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfQrcodeBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfQrcodeBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QrcodeBean" type="{http://qrcode.service.infogiga.cn}QrcodeBean" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfQrcodeBean", propOrder = {
    "qrcodeBean"
})
public class ArrayOfQrcodeBean {

    @XmlElement(name = "QrcodeBean", required = true, nillable = true)
    protected List<QrcodeBean> qrcodeBean;

    /**
     * Gets the value of the qrcodeBean property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcodeBean property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQrcodeBean().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QrcodeBean }
     * 
     * 
     */
    public List<QrcodeBean> getQrcodeBean() {
        if (qrcodeBean == null) {
            qrcodeBean = new ArrayList<QrcodeBean>();
        }
        return this.qrcodeBean;
    }

}
