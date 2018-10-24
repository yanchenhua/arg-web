package ltd.ontsol.core;

import java.io.Serializable;
import java.util.Locale;

/**
 * The Interface ITranslation classifies an implementing class as a translation.
 * E.g. used in {@link Translatable} which has a list of {@link ITranslation}s
 *
 * @author lewei
 */
public interface ITranslation extends Serializable {

    /**
     * Gets the locale.
     *
     * @return the locale
     */
    Locale getLocale();

    /**
     * Gets the text.
     *
     * @return the text
     */
    String getText();

    void setText(String text);
}
