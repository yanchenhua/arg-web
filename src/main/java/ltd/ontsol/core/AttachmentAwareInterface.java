package ltd.ontsol.core;

/**
 * This interface allow user to use UploadFilesPanel
 */
public interface AttachmentAwareInterface {

    String getFilePath();

    void setFilePath(String filePath);

    String getDisplayName();

    void setDisplayName(String displayName);
}