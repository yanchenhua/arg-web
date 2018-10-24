package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.MediaConstants;

/**
 * Created by cn40580 at 2018-06-13 11:03 AM.
 */
@Entity
@Table(name = "MEDIA")
public class MediaDTO extends AbstractPersistableEntity<Long> {

    @JoinColumn(name = "TITLE_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText title;

    @JoinColumn(name = "ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO attachment;

    @JoinColumn(name = "PIC_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO picture;

    @Enumerated(EnumType.STRING)
    private MediaConstants type;

    public LongText getTitle() {
        return title;
    }

    public void setTitle(LongText title) {
        this.title = title;
    }

    public MediaConstants getType() {
        return type;
    }

    public void setType(MediaConstants type) {
        this.type = type;
    }

    public AttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentDTO attachment) {
        this.attachment = attachment;
    }

    public AttachmentDTO getPicture() {
        return picture;
    }

    public void setPicture(AttachmentDTO picture) {
        this.picture = picture;
    }
}
