package ltd.ontsol.core.dto;

import ltd.ontsol.core.AbstractPersistableEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DISTRIBUTOR")
public class DistributorDTO extends AbstractPersistableEntity<Long> {
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private String area;
 private String companyName;
 private String address;
 private String tel;
}
