package ltd.ontsol.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractFileAttachmentEntity extends AbstractPersistableEntity<Long> implements AttachmentAwareInterface {

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

}
