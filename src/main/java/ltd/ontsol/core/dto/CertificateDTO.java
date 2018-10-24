package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by cn40580 at 2018-06-13 10:46 AM.
 */
@Entity
@Table(name = "CERTIFICATE")
public class CertificateDTO extends AbstractPersistableEntity<Long> {

    @JoinColumn(name = "TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText text = new LongText();
    @JoinColumn(name = "ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO attachment;
    private Integer sort;

//    @Column(length = PersistenceConstants.LENGTH_4000)
//    private String textStr;

//    public String getTextStr() {
//        return textStr;
//    }
//
//    public void setTextStr(String textStr) {
//        this.textStr = textStr;
//    }

    public LongText getText() {
        return text;
    }

    public void setText(LongText text) {
        this.text = text;
    }

    public AttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentDTO attachment) {
        this.attachment = attachment;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}

