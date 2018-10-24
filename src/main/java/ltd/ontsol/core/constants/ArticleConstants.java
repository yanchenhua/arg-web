package ltd.ontsol.core.constants;

/**
 * The Enum AttachmentType.
 *
 * @author cn40580
 */
public enum ArticleConstants {

    /**
     * news
     */
    NEWS(1),

    /**
     * industry information
     */
    INDUSTRY(2),

    /**
     * Popular science
     */
    SCIENCE(3),

    /**
     * video and photo
     */
    TECH(6);

    private final int index;

    ArticleConstants(final int index) {
        this.index = index;
    }

    public static ArticleConstants of(final int index) {
        switch (index) {
            case 1:
                return ArticleConstants.NEWS;
            case 2:
                return ArticleConstants.INDUSTRY;
            case 3:
                return ArticleConstants.SCIENCE;
            default:
                return ArticleConstants.NEWS;
        }
    }

    public int getIndex() {
        return index;
    }
}
