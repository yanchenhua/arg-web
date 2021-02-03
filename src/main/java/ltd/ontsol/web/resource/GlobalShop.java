package ltd.ontsol.web.resource;

import ltd.ontsol.core.dto.AttachmentDTO;

/**
 * Created by cn40580 at 2018-06-28 2:32 PM.
 */
public class GlobalShop {
    private String shopname;
    private String shopcontact;
    private String shoptel;
    private String shopaddress;
    private String shoptype;
    private Double shoplongitude;
    private Double shoplatitude;
    private String verNumber;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    private String zipCode;
    public String getVerNumber() {
        return verNumber;
    }

    public void setVerNumber(String verNumber) {
        this.verNumber = verNumber;
    }


    private AttachmentDTO globalBannerAttachment;
    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    public String getShopcontact() {
        return shopcontact;
    }

    public void setShopcontact(String shopcontact) {
        this.shopcontact = shopcontact;
    }
    public String getShoptel() {
        return shoptel;
    }

    public void setShoptel(String shoptel) {
        this.shoptel = shoptel;
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopaddress) {
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

    public void setGlobalBannerAttachment(AttachmentDTO globalBannerAttachment)
    {
        this.globalBannerAttachment=globalBannerAttachment;
    }

    public AttachmentDTO getGlobalBannerAttachment(){
        return this.globalBannerAttachment;
    }
}
