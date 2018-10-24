package ltd.ontsol.core.dto;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.ITranslation;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Translation extends AbstractPersistableEntity<Long> implements ITranslation {


    @Column(name = "LANG", nullable = false)
    private Locale language = Locale.CHINA;

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    @Override
    public abstract String getText();

    @Override
    public Locale getLocale() {
        return language;
    }

    @Override
    public String toString() {
        return String.format("Translation [id=%s, type=%s, language=%s, text=%s]", getId(), language, getText());
    }
}
