package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.GlobalConstants;

/**
 * Created by cn40580 at 2018-06-28 2:25 PM.
 */
@Entity
@Table(name = "GLOBAL")
public class GlobalDTO extends AbstractPersistableEntity<Long> {

    @JoinColumn(name = "SHOP_NAME_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText shopname;
    @JoinColumn(name = "SHOP_CONTACT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText shopcontact;
    private String shoptel;

    public String getVerNumber() {
        return verNumber;
    }

    public void setVerNumber(String verNumber) {
        this.verNumber = verNumber;
    }

    private String verNumber;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    private String zipCode;

    //店铺图片多张
    @JoinColumn(name = "GLOBAL_BANNER_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO globalBannerAttachment;

    @JoinColumn(name = "SHOP_ADDRESS_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText shopaddress;
    private String shoptype;
    private Double shoplongitude;
    private Double shoplatitude;

    //
    @Enumerated(EnumType.STRING)
    private GlobalConstants type;

    //顶层name
    //static\front_static\json\countries.json
    @JoinColumn(name = "GLOBAL_NAME_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText globalName;

    //次层name
    //static\front_static\json\countries.json
    @JoinColumn(name = "CITY_NAME_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText cityName;

    public LongText getAreaName() {
        return areaName;
    }

    public void setAreaName(LongText areaName) {
        this.areaName = areaName;
    }

    @JoinColumn(name = "AREA_NAME_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText areaName;

    public LongText getGlobalName() {
        return globalName;
    }

    public void setGlobalName(LongText globalName) {
        this.globalName = globalName;
    }

    public LongText getCityName() {
        return cityName;
    }

    public void setCityName(LongText cityName) {
        this.cityName = cityName;
    }

    public LongText getShopname() {
        return shopname;
    }

    public LongText getShopcontact() {
        return shopcontact;
    }

    public void setShopname(LongText shopname) {
        this.shopname = shopname;
    }

    public void setShopcontact(LongText shopcontact) {
        this.shopcontact = shopcontact;
    }

    public String getShoptel() {
        return shoptel;
    }

    public void setShoptel(String shoptel) {
        this.shoptel = shoptel;
    }

    public LongText getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(LongText shopaddress) {
        this.shopaddress = shopaddress;
    }

    public String getShoptype() {
        return shoptype;
    }

    public void setShoptype(String shoptype) {
        this.shoptype = shoptype;
    }

    public Double getShoplongitude() {
        return shoplongitude;
    }

    public void setShoplongitude(Double shoplongitude) {
        this.shoplongitude = shoplongitude;
    }

    public Double getShoplatitude() {
        return shoplatitude;
    }

    public void setShoplatitude(Double shoplatitude) {
        this.shoplatitude = shoplatitude;
    }

    public GlobalConstants getType() {
        return type;
    }

    public void setType(GlobalConstants type) {
        this.type = type;
    }

    public AttachmentDTO getGlobalBannerAttachment() {
        System.out.println("getShopBannerAttachment-------");
        return globalBannerAttachment;
    }

    public void setGlobalBannerAttachment(AttachmentDTO globalBannerAttachment) {
        System.out.println("setShopBannerAttachment-------");
        this.globalBannerAttachment = globalBannerAttachment;
    }
}
