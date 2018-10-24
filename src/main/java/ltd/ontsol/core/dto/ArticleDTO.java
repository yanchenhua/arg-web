package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractAuditingEntity;
import ltd.ontsol.core.constants.ArticleConstants;

/**
 * Created by cn40580 at 2018-06-13 2:47 PM.
 */
@Entity
@Table(name = "ARTICLE")
public class ArticleDTO extends AbstractAuditingEntity {


    @JoinColumn(name = "TITLE_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText title;

    @JoinColumn(name = "SHORT_CONTENT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText shortContent;

    @JoinColumn(name = "CONTENT_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText content;

    private Integer viewCount;
    private Integer seq;


    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private ArticleConstants articleType;

    @JoinColumn(name = "ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO attachment;

    private Boolean homeFlag;

    private Boolean titleFlag;

    public Boolean getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(Boolean topFlag) {
        this.topFlag = topFlag;
    }

    private Boolean topFlag;

    private String dateStr;

    public AttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentDTO attachment) {
        this.attachment = attachment;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public ArticleConstants getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleConstants articleType) {
        this.articleType = articleType;
    }

    public LongText getShortContent() {
        return shortContent;
    }

    public void setShortContent(LongText shortContent) {
        this.shortContent = shortContent;
    }

    public Boolean getHomeFlag() {
        return homeFlag;
    }

    public void setHomeFlag(Boolean homeFlag) {
        this.homeFlag = homeFlag;
    }

    public Boolean getTitleFlag() {
        return titleFlag;
    }

    public void setTitleFlag(Boolean titleFlag) {
        this.titleFlag = titleFlag;
    }

    public LongText getTitle() {
        return title;
    }

    public void setTitle(LongText title) {
        this.title = title;
    }

    public LongText getContent() {
        return content;
    }

    public void setContent(LongText content) {
        this.content = content;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
