package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by cn40580 at 2018-06-13 11:03 AM.
 */
@Entity
@Table(name = "ABOUT")
public class AboutDTO extends AbstractPersistableEntity<Long> {

    @JoinColumn(name = "HEAD_DESC1_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText headDesc1;

    @JoinColumn(name = "HEAD_DESC2_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText headDesc2;

    @JoinColumn(name = "PARAM1_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText param1;

    @JoinColumn(name = "COUNT1_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText count1;

    @JoinColumn(name = "UNIT1_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText unit1;

    @JoinColumn(name = "PARAM2_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText param2;

    @JoinColumn(name = "COUNT2_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText count2;

    @JoinColumn(name = "UNIT2_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText unit2;

    @JoinColumn(name = "PARAM3_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText param3;

    @JoinColumn(name = "COUNT3_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText count3;

    @JoinColumn(name = "UNIT3_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText unit3;

    @JoinColumn(name = "PARAM4_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText param4;

    @JoinColumn(name = "COUNT4_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText count4;

    @JoinColumn(name = "UNIT4_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText unit4;

    @JoinColumn(name = "BANNER_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO bannerAttachment;

    @JoinColumn(name = "WW_CUSTM_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO worldWildCustAttachment;

    @JoinColumn(name = "CN_CUSTM_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO chineseCustAttachment;

    @JoinColumn(name = "ABOUT_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText aboutText;
    @JoinColumn(name = "CUS_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText cusText;

    public LongText getHeadDesc1() {
        return headDesc1;
    }

    public void setHeadDesc1(LongText headDesc1) {
        this.headDesc1 = headDesc1;
    }

    public LongText getHeadDesc2() {
        return headDesc2;
    }

    public void setHeadDesc2(LongText headDesc2) {
        this.headDesc2 = headDesc2;
    }

    public LongText getParam1() {
        return param1;
    }

    public void setParam1(LongText param1) {
        this.param1 = param1;
    }

    public LongText getCount1() {
        return count1;
    }

    public void setCount1Str(LongText count1) {
        this.count1 = count1;
    }

    public LongText getUnit1() {
        return unit1;
    }

    public void setUnit1(LongText unit1) {
        this.unit1 = unit1;
    }

    public LongText getParam2() {
        return param2;
    }

    public void setParam2(LongText param2) {
        this.param2 = param2;
    }

    public LongText getCount2() {
        return count2;
    }

    public void setCount2(LongText count2) {
        this.count2 = count2;
    }

    public LongText getUnit2() {
        return unit2;
    }

    public void setUnit2(LongText unit2) {
        this.unit2 = unit2;
    }

    public LongText getParam3() {
        return param3;
    }

    public void setParam3(LongText param3) {
        this.param3 = param3;
    }

    public LongText getCount3() {
        return count3;
    }

    public void setCount3(LongText count3) {
        this.count3 = count3;
    }

    public LongText getUnit3() {
        return unit3;
    }

    public void setUnit3(LongText unit3) {
        this.unit3 = unit3;
    }

    public LongText getParam4() {
        return param4;
    }

    public void setParam4(LongText param4) {
        this.param4 = param4;
    }

    public LongText getCount4() {
        return count4;
    }

    public void setCount4(LongText count4) {
        this.count4 = count4;
    }

    public LongText getUnit4() {
        return unit4;
    }

    public void setUnit4(LongText unit4) {
        this.unit4 = unit4;
    }

    public AttachmentDTO getBannerAttachment() {
        return bannerAttachment;
    }

    public void setBannerAttachment(AttachmentDTO bannerAttachment) {
        this.bannerAttachment = bannerAttachment;
    }

    public AttachmentDTO getWorldWildCustAttachment() {
        return worldWildCustAttachment;
    }

    public void setWorldWildCustAttachment(AttachmentDTO worldWildCustAttachment) {
        this.worldWildCustAttachment = worldWildCustAttachment;
    }

    public AttachmentDTO getChineseCustAttachment() {
        return chineseCustAttachment;
    }

    public void setChineseCustAttachment(AttachmentDTO chineseCustAttachment) {
        this.chineseCustAttachment = chineseCustAttachment;
    }

    public LongText getAboutText() {
        return aboutText;
    }

    public void setAboutText(LongText aboutText) {
        this.aboutText = aboutText;
    }
    public LongText getCusText() {
        return cusText;
    }

    public void setCusText(LongText cusText) {
        this.cusText = cusText;
    }
}
