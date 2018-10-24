package ltd.ontsol.core.dto;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.Translatable;

@Entity
@Table(name = "LONG_TEXT")
public class LongText extends AbstractPersistableEntity<Long> implements Translatable {

    @Transient
    private final Logger LOG = LoggerFactory.getLogger(LongText.class);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "longText", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private Set<LongTextTranslation> translations = new HashSet<>();

    public LongText() {

    }

    @Override
    public Set<LongTextTranslation> getTranslations() {
        return this.translations;
    }

    public void setTranslations(final Set<LongTextTranslation> translations) {
        this.translations = translations;
    }

}
