package ltd.ontsol.core.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by cn40580 at 2018-06-13 10:41 AM.
 */
@Entity
@Table(name = "HOME")
public class HomeDTO extends AbstractPersistableEntity<Long> {

    @JoinColumn(name = "WEB_TITLE_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText webTitle = new LongText();
    @JoinColumn(name = "KEYWORDS_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText keywords = new LongText();
    @JoinColumn(name = "WEB_DESC_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText webDesc = new LongText();
    @JoinColumn(name = "COPYRIGHT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText copyRight = new LongText();
    @JoinColumn(name = "CASE_NUM_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText caseNum = new LongText();
    @JoinColumn(name = "LEGAL_TITLE_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText legalTitle = new LongText();
    @JoinColumn(name = "LEGAL_CONTENT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText legalContent = new LongText();

    //查询服务网点链接
    private String serviceLink;

    //查询汽车玻璃链接
    private String searchLink;
    //配件系统连接
    private String fySystemLink;
    //首页banner
    @Transient
    private List<AttachmentDTO> attachment;

    public List<AttachmentDTO> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<AttachmentDTO> attachment) {
        this.attachment = attachment;
    }

    public String getServiceLink() {
        return serviceLink;
    }

    public void setServiceLink(String serviceLink) {
        this.serviceLink = serviceLink;
    }

    public String getSearchLink() {
        return searchLink;
    }

    public void setSearchLink(String searchLink) {
        this.searchLink = searchLink;
    }

    public String getFySystemLink() {
        return fySystemLink;
    }

    public void setFySystemLink(String fySystemLink) {
        this.fySystemLink = fySystemLink;
    }

    public LongText getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(LongText webTitle) {
        this.webTitle = webTitle;
    }

    public LongText getKeywords() {
        return keywords;
    }

    public void setKeywords(LongText keywords) {
        this.keywords = keywords;
    }

    public LongText getWebDesc() {
        return webDesc;
    }

    public void setWebDesc(LongText webDesc) {
        this.webDesc = webDesc;
    }

    public LongText getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(LongText copyRight) {
        this.copyRight = copyRight;
    }

    public LongText getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(LongText caseNum) {
        this.caseNum = caseNum;
    }

    public LongText getLegalTitle() {
        return legalTitle;
    }

    public void setLegalTitle(LongText legalTitle) {
        this.legalTitle = legalTitle;
    }

    public LongText getLegalContent() {
        return legalContent;
    }

    public void setLegalContent(LongText legalContent) {
        this.legalContent = legalContent;
    }
}
