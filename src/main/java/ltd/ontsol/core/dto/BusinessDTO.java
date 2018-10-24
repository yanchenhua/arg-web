package ltd.ontsol.core.dto;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by cn40580 at 2018-06-25 1:59 PM.
 */
@Entity
@Table(name = "BUSINESS")
public class BusinessDTO extends AbstractPersistableEntity<Long> {
    @Transient
    MultipartFile file;
    private String name;
    private String companyName;
    private String address;
    //private String file;

    @Transient
    private String street;
    @Transient
    private String province;
    @Transient
    private String country;
    @Transient
    private String city;
    private String phone;
    private String fax;
    private String mail;
    private String subject;

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    private String subDate;
    @JoinColumn(name = "SHOP_CERT_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO shopCertAttachment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
//    public String getFile() {
//        return file;
//    }
//
//    public void setFile(String file) {
//        this.file = file;
//    }
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public AttachmentDTO getShopCertAttachment() {
        return shopCertAttachment;
    }

    public void setShopCertAttachment(AttachmentDTO shopCertAttachment) {
        this.shopCertAttachment = shopCertAttachment;
    }
}
