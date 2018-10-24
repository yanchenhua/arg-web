package ltd.ontsol.core.util;

import java.util.Locale;
import java.util.Optional;

import ltd.ontsol.core.ITranslation;
import ltd.ontsol.core.Translatable;
import ltd.ontsol.core.dto.LongText;
import ltd.ontsol.core.dto.LongTextTranslation;

/**
 * The Class TranslationConverter.
 */
public class TranslationUtils {

    Translatable translatable;

    public TranslationUtils(Translatable translatable) {
        this.translatable = translatable;
    }

    public static void putTranslation(LongText text) {
        for (LongTextTranslation t : text.getTranslations()) {
            t.setLongText(text);
        }
    }

    public static String convertToString(final Translatable map, final Locale locale) {
        Optional<? extends ITranslation> optional = map.get(locale);
        if (optional.isPresent()) {
            String text = optional.get().getText();
            return text;
        } else {
            return "";
        }
    }

    public Translatable convertToObject(final String string, final Locale locale) {
        Optional<? extends ITranslation> optional = translatable.get(locale);
        if (optional.isPresent()) {
            optional.get().setText(string);
        } else {
            // add new translation
            this.addTranslation(translatable, locale, string);
        }
        return translatable;

    }

    private void addTranslation(final Translatable t, final Locale locale, final String string) {
        if (t instanceof LongText) {
            // create new longtext
            LongText lt = (LongText) t;
            LongTextTranslation ltt = new LongTextTranslation();
            ltt.setLanguage(locale);
            ltt.setText(string);
            ltt.setLongText(lt);
            lt.getTranslations().add(ltt);
        } else {
            throw new IllegalStateException("error converting translation");
        }
    }
}
