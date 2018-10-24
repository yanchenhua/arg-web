package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by cn40580 at 2018-06-13 4:17 PM.
 */
@Entity
@Table(name = "INVEST")
public class InvestDTO extends AbstractPersistableEntity<Long> {

    private String name;
    private String companyName;
    private String tel;
    private String fax;
    private String email;
    private String title;

    @JoinColumn(name = "ADDR_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AddressDTO addr;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AddressDTO getAddr() {
        return addr;
    }

    public void setAddr(AddressDTO addr) {
        this.addr = addr;
    }
}
