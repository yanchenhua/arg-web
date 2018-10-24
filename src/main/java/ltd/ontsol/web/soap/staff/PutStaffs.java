
package ltd.ontsol.web.soap.staff;

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
 *         &lt;element name="staffs" type="{http://www.yourcompany.com/webservice}staff"/>
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
    "staffs"
})
@XmlRootElement(name = "putStaffs", namespace = "http://www.yourcompany.com/webservice")
public class PutStaffs {

    @XmlElement(namespace = "http://www.yourcompany.com/webservice", required = true)
    protected Staff staffs;

    /**
     * Gets the value of the staffs property.
     * 
     * @return
     *     possible object is
     *     {@link Staff }
     *     
     */
    public Staff getStaffs() {
        return staffs;
    }

    /**
     * Sets the value of the staffs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Staff }
     *     
     */
    public void setStaffs(Staff value) {
        this.staffs = value;
    }

}
