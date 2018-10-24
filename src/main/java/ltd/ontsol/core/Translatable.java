package ltd.ontsol.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import org.thymeleaf.util.StringUtils;

/**
 * The Interface Translatable indicates that an implementing class has
 * translations.
 *
 * @author lewei
 */
public interface Translatable extends Serializable {

    /**
     * Gets the translations.
     *
     * @return the translations
     */
    Collection<? extends ITranslation> getTranslations();

    default Optional<? extends ITranslation> get(final Locale l) {
        return getTranslations().stream().filter(t -> t.getLocale().equals(l)).findFirst();
    }

    default String getTranslatedStr(final Locale l) {
        Optional<? extends ITranslation> optional = get(l);
        return optional.isPresent() ? optional.get().getText() : "";
    }

    default Boolean hasText(Locale local) {
        return !StringUtils.isEmpty(getTranslatedStr(local));
    }

}
