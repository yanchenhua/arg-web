package ltd.ontsol.core.dto;

import ltd.ontsol.core.AbstractAuditingEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LoginNode")
public class LoginDTO extends AbstractAuditingEntity {
    private String staffTel;
    private String staffEmail;
    private String staffName;
    private String staffCode;
    private String userid;
    private String staffOrg;
    private String apartment;
    private String password;
    private Integer status;
    public String getstaffTel() {
        return staffTel;
    }
    public void setstaffTel(String staffTel) {
        this.staffTel = staffTel;
    }
    public String getstaffEmail() {
        return staffEmail;
    }
    public void setstaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }
    public String getstaffName() {
        return staffName;
    }
    public void setstaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getstaffCode() {
        return staffCode;
    }

    public void setstaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getstaffOrg() {
        return staffOrg;
    }
    public void setstaffOrg(String staffOrg) {
        this.staffOrg = staffOrg;
    }

    public String getapartment() {
        return apartment;
    }
    public void setapartment(String apartment) {
        this.apartment = apartment;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getStatus(){return status;}
    public void setStatus(Integer status){this.status = status;}


}
