package ltd.ontsol.core.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.AgrrConstants;

/**
 * Created by cn40580 at 2018-06-13 3:00 PM.
 */
@Entity
@Table(name = "AGRR_NODE")
public class AgrrNodeDTO extends AbstractPersistableEntity<Long> {
    @Transient
    MultipartFile file;
    @Transient
    MultipartFile otherfile;

    public MultipartFile getContactfile() {
        return contactfile;
    }

    public void setContactfile(MultipartFile contactfile) {
        this.contactfile = contactfile;
    }

    @Transient
    MultipartFile contactfile;
    @Transient
    List<String> country = new ArrayList<>();
    @Transient
    List<String> province = new ArrayList<>();
    @Transient
    List<String> city = new ArrayList<>();
    @Transient
    List<String> street = new ArrayList<>();
    @Transient
    List<String> postCode = new ArrayList<>();
    @Transient
    private List<String> nodeAddress = new ArrayList<>();
    //认证服务商类型
    private String verifyType;
    private String name;
    private String tel;

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    private String wx;
    private String email;
    private String companyName;
    private String companyCode;
    private Integer shopCount;
    private String shopSize;
    private String business;
    private String lng;
    private String lat;


    private String distributor;
    private String distributorCode;
    private String representative;
    private String purchaser;
    private String purchaserNumber;
    private String years;
    private String storeproperty;
    private String rent;
    private String salesbrand;

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaserNumber() {
        return purchaserNumber;
    }

    public void setPurchaserNumber(String purchaserNumber) {
        this.purchaserNumber = purchaserNumber;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getStoreproperty() {
        return storeproperty;
    }

    public void setStoreproperty(String storeproperty) {
        this.storeproperty = storeproperty;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getSalesbrand() {
        return salesbrand;
    }

    public void setSalesbrand(String salesbrand) {
        this.salesbrand = salesbrand;
    }

    public String getSalesarea() {
        return salesarea;
    }

    public void setSalesarea(String salesarea) {
        this.salesarea = salesarea;
    }

    private String salesarea;

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockRate() {
        return stockRate;
    }

    public void setStockRate(String stockRate) {
        this.stockRate = stockRate;
    }

    public String getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(String markupRate) {
        this.markupRate = markupRate;
    }

    private String purchase;
    private String sales;
    private String purchaseNumber;
    private String channel;
    private String stock;
    private String stockRate;
    private String markupRate;
    private String username;

    public String getVerstatus() {
        return verstatus;
    }

    public void setVerstatus(String verstatus) {
        this.verstatus = verstatus;
    }
    @Value("0")
    private String verstatus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;


    @Enumerated(EnumType.STRING)
    private AgrrConstants type;

    //T营业执照
    @JoinColumn(name = "SHOP_CERT_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO shopCertAttachment;


    //店铺图片多张
    @JoinColumn(name = "SHOP_BANNER_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO shopBannerAttachment;

    public AttachmentDTO getShopContactAttachment() {
        return shopContactAttachment;
    }

    public void setShopContactAttachment(AttachmentDTO shopContactAttachment) {
        this.shopContactAttachment = shopContactAttachment;
    }

    @JoinColumn(name = "SHOP_CONTACTACT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO shopContactAttachment;

    @OneToMany(mappedBy = "agrrNode", fetch = FetchType.EAGER)
    private Set<AddressDTO> addrs = new HashSet<>();
//
//    @JoinColumn(name = "TEXT_ID")
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private LongText text;

    private String textStr;

    //主营业务
    private String serviceType;

    private Integer depotSize;
    //工位数量
    private Integer slotCount;
    //玻璃安装技术
    private Integer techCount;
    //工具车
    private Integer tranCount;
    //
    private Boolean goodProvider;

    //for good provider

    @JoinColumn(name = "G_COMPANY_NAME_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText goodCompanyName;

    @JoinColumn(name = "G_COMPANY_TEXT_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText goodText;

    @JoinColumn(name = "G_NAME_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText goodName;

    @JoinColumn(name = "G_TEL_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText goodTel;

    @JoinColumn(name = "G_ADDR_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText goodAddr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getShopCount() {
        return shopCount;
    }

    public void setShopCount(Integer shopCount) {
        this.shopCount = shopCount;
    }

    public String getShopSize() {
        return shopSize;
    }

    public void setShopSize(String shopSize) {
        this.shopSize = shopSize;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AttachmentDTO getShopCertAttachment() {
        return shopCertAttachment;
    }

    public void setShopCertAttachment(AttachmentDTO shopCertAttachment) {
        this.shopCertAttachment = shopCertAttachment;
    }


    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getDepotSize() {
        return depotSize;
    }

    public void setDepotSize(Integer depotSize) {
        this.depotSize = depotSize;
    }

    public Integer getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(Integer slotCount) {
        this.slotCount = slotCount;
    }

    public Integer getTechCount() {
        return techCount;
    }

    public void setTechCount(Integer techCount) {
        this.techCount = techCount;
    }

    public Integer getTranCount() {
        return tranCount;
    }

    public void setTranCount(Integer tranCount) {
        this.tranCount = tranCount;
    }

    public Set<AddressDTO> getAddrs() {
        return addrs;
    }

    public void setAddrs(Set<AddressDTO> addrs) {
        this.addrs = addrs;
    }

    public String getTextStr() {
        return textStr;
    }

    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }

    public Boolean getGoodProvider() {
        return goodProvider;
    }

    public void setGoodProvider(Boolean goodProvider) {
        this.goodProvider = goodProvider;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public AttachmentDTO getShopBannerAttachment() {
        return shopBannerAttachment;
    }

    public void setShopBannerAttachment(AttachmentDTO shopBannerAttachment) {
        this.shopBannerAttachment = shopBannerAttachment;
    }

    public AgrrConstants getType() {
        return type;
    }

    public void setType(AgrrConstants type) {
        this.type = type;
    }

    public LongText getGoodCompanyName() {
        return goodCompanyName;
    }

    public void setGoodCompanyName(LongText goodCompanyName) {
        this.goodCompanyName = goodCompanyName;
    }

    public LongText getGoodText() {
        return goodText;
    }

    public void setGoodText(LongText goodText) {
        this.goodText = goodText;
    }

    public LongText getGoodName() {
        return goodName;
    }

    public void setGoodName(LongText goodName) {
        this.goodName = goodName;
    }

    public LongText getGoodTel() {
        return goodTel;
    }

    public void setGoodTel(LongText goodTel) {
        this.goodTel = goodTel;
    }

    public LongText getGoodAddr() {
        return goodAddr;
    }

    public void setGoodAddr(LongText goodAddr) {
        this.goodAddr = goodAddr;
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

    public MultipartFile getotherFile() {
        return otherfile;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<String> getStreet() {
        return street;
    }

    public void setStreet(List<String> street) {
        this.street = street;
    }

    public MultipartFile getOtherfile() {
        return otherfile;
    }

    public void setOtherfile(MultipartFile otherfile) {
        this.otherfile = otherfile;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getProvince() {
        return province;
    }

    public void setProvince(List<String> province) {
        this.province = province;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }
    public List<String> getPostCode() {
        return postCode;
    }

    public void setPostCode(List<String> postCode) {
        this.postCode = postCode;
    }

    public List<String> getNodeAddress() {
        return nodeAddress;
    }

    public void setNodeAddress(List<String> nodeAddress) {
        this.nodeAddress = nodeAddress;
    }
}
