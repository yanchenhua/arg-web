package ltd.ontsol.core.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ltd.ontsol.core.constants.PersistenceConstants;

@Entity
@Table(name = "LONG_TEXT_TRANS")
public class LongTextTranslation extends Translation {

    @ManyToOne
    @JoinColumn(name = "LONG_TEXT_ID")
    @JsonIgnore
    private LongText longText;

    @Column(length = PersistenceConstants.LENGTH_16383, name = "TEXT")
    private String text;

    public LongText getLongText() {
        return longText;
    }

    public void setLongText(LongText longText) {
        this.longText = longText;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "LongTextTranslation{" +
                "text='" + text + '\'' +
                '}';
    }
}