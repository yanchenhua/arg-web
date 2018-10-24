package ltd.ontsol.core.constants;

/**
 * The Enum address type.
 *
 * @author cn40580
 */
public enum QuestionTypeConstants {

    /**
     * SERVICE
     */
    SERVICE(1),

    /**
     * PROVIDER
     */
    PROVIDER(2);

    private final int index;

    QuestionTypeConstants(final int index) {
        this.index = index;
    }

    public static QuestionTypeConstants of(final int index) {
        switch (index) {
            case 1:
                return QuestionTypeConstants.SERVICE;
            case 2:
                return QuestionTypeConstants.PROVIDER;
            default:
                return QuestionTypeConstants.SERVICE;
        }
    }

    public int getIndex() {
        return index;
    }
}
