package ltd.ontsol.core.dto;

import javax.persistence.*;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.MediaConstants;

import java.util.List;

/**
 * Created by cn40580 at 2018-06-13 11:03 AM.
 */
@Entity
@Table(name = "MEDIA")
public class MediaDTO extends AbstractPersistableEntity<Long> {

    @JoinColumn(name = "TITLE_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText title;

    public LongText getFilePathlocale() {
        return filePathlocale;
    }

    public void setFilePathlocale(LongText filePathlocale) {
        this.filePathlocale = filePathlocale;
    }

    @JoinColumn(name = "FILE_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText filePathlocale;

//    public List<AttachmentDTO> getAttachment() {
//        return attachment;
//    }
//
//    public void setAttachment(List<AttachmentDTO> attachment) {
//        this.attachment = attachment;
//    }

    @JoinColumn(name = "ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//@Transient
//    private List<AttachmentDTO> attachment;
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
