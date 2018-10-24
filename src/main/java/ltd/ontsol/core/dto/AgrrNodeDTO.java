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
    private String email;
    private String companyName;
    private String companyCode;
    private Integer shopCount;
    private String shopSize;
    private String business;
    private String lng;
    private String lat;
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
