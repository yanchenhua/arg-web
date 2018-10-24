package ltd.ontsol.core.constants;

/**
 * The Enum AttachmentType.
 *
 * @author cn40580
 */
public enum AttachmentConstants {

    /**
     * file
     */
    FILE(0),

    /**
     * image
     */
    IMAGE(1),

    /**
     * home
     */
    HOME(2),

    /**
     * pdf
     */
    PDF(3),

    /**
     * pdf
     */
    VIDEO(4),

    /**
     * others
     */
    OTHER(-1);

    private final int index;

    AttachmentConstants(final int index) {
        this.index = index;
    }

    public static AttachmentConstants of(final int index) {
        switch (index) {
            case 0:
                return AttachmentConstants.FILE;
            case 1:
                return AttachmentConstants.IMAGE;
            case 2:
                return AttachmentConstants.HOME;
            case 3:
                return AttachmentConstants.PDF;
            case 4:
                return AttachmentConstants.VIDEO;
            default:
                return AttachmentConstants.OTHER;
        }
    }

    public int getIndex() {
        return index;
    }
}
