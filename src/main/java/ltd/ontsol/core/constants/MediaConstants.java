package ltd.ontsol.core.constants;

/**
 * Created by cn40580 at 2018-07-01 3:13 PM.
 */
public enum MediaConstants {

    PDF, VIDEO, IMAGE, OTHER;

    public static MediaConstants myValueOf(String value) {
        switch (value.toLowerCase()) {
            case "jpg":
                return MediaConstants.IMAGE;
            case "png":
                return MediaConstants.IMAGE;
            case "pdf":
                return MediaConstants.PDF;
            case "mp4":
                return MediaConstants.VIDEO;
            default:
                return MediaConstants.OTHER;
        }
    }
}
