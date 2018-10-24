package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractAuditingEntity;

/**
 * Created by cn40580 at 2018-06-13 2:47 PM.
 */
@Entity
@Table(name = "PRODUCT")
public class ProductDTO extends AbstractAuditingEntity {
    @JoinColumn(name = "TITLE_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText title;

    @JoinColumn(name = "CONTENT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText content;

    @JoinColumn(name = "TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText text = new LongText();
    @JoinColumn(name = "ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO attachment;
    private Integer sort;

    public AttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentDTO attachment) {
        this.attachment = attachment;
    }

    public LongText getContent() {
        return content;
    }

    public void setContent(LongText content) {
        this.content = content;
    }

    public LongText getTitle() {
        return title;
    }

    public void setTitle(LongText title) {
        this.title = title;
    }

    //
    public LongText getText() {
        return text;
    }

    public void setText(LongText text) {
        this.text = text;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
