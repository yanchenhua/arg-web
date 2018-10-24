package ltd.ontsol.core.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by cn40580 at 2018-06-13 10:46 AM.
 */
@Entity
@Table(name = "HONOR")
public class HonorDTO extends AbstractPersistableEntity<Long> {

    @Temporal(TemporalType.DATE)
    private Date createdDate;

//    private String textStr;

    @JoinColumn(name = "TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText text = new LongText();

    @JoinColumn(name = "ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO attachment;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        if (null == createdDate) {
            createdDate = new Date();
        }
        this.createdDate = createdDate;
    }

    //
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

//    public String getTextStr() {
//        return textStr;
//    }
//
//    public void setTextStr(String textStr) {
//        this.textStr = textStr;
//    }
}

