package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.ContactCardConstants;

/**
 * Created by cn40580 at 2018-06-13 4:14 PM.
 */
@Entity
@Table(name = "CONTACT_CARD")
public class ContactCardDTO extends AbstractPersistableEntity<Long> {

//    private LongText contactTitle;

    @JoinColumn(name = "TITLE_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText contactTitle;

    @JoinColumn(name = "SUB_TITLE_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText contactSubTitle;

    @JoinColumn(name = "ADDRESS_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText address;

    @JoinColumn(name = "ADDRESS_SUB_TITLE_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText addressSubTitle;

    private String lng;
    private String lat;

    private String email;
    private String tel;
    private String fax;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private ContactCardConstants type;

    @JoinColumn(name = "ADDR_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AddressDTO addr;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public AddressDTO getAddr() {
        return addr;
    }

    public void setAddr(AddressDTO addr) {
        this.addr = addr;
    }

    public ContactCardConstants getType() {
        return type;
    }

    public void setType(ContactCardConstants type) {
        this.type = type;
    }

    public LongText getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(LongText contactTitle) {
        this.contactTitle = contactTitle;
    }

    public LongText getContactSubTitle() {
        return contactSubTitle;
    }

    public void setContactSubTitle(LongText contactSubTitle) {
        this.contactSubTitle = contactSubTitle;
    }

    public LongText getAddress() {
        return address;
    }

    public void setAddress(LongText address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public LongText getAddressSubTitle() {
        return addressSubTitle;
    }

    public void setAddressSubTitle(LongText addressSubTitle) {
        this.addressSubTitle = addressSubTitle;
    }
}
