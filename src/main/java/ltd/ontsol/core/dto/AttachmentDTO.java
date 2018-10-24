package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractFileAttachmentEntity;
import ltd.ontsol.core.constants.AttachmentConstants;

@Entity
@Table(name = "ATTAHMENT")
public class AttachmentDTO extends AbstractFileAttachmentEntity {

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private AttachmentConstants type;

    @JoinColumn(name = "DESC_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText desc;


    @JoinColumn(name = "DESC2_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText desc2;


    //only for media upload
    @JoinColumn(name = "MEDIA_PIC_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO mediaPic;

    public LongText getDesc() {
        return desc;
    }

    public void setDesc(LongText desc) {
        this.desc = desc;
    }

    public LongText getDesc2() {
        return desc2;
    }

    public void setDesc2(LongText desc2) {
        this.desc2 = desc2;
    }

    public AttachmentConstants getType() {
        return type;
    }

    public void setType(AttachmentConstants type) {
        this.type = type;
    }

    public AttachmentDTO getMediaPic() {
        return mediaPic;
    }

    public void setMediaPic(AttachmentDTO mediaPic) {
        this.mediaPic = mediaPic;
    }
}
