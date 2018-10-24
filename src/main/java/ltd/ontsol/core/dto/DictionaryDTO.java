package ltd.ontsol.core.dto;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.DicLevelConstants;
import ltd.ontsol.core.util.HtmlTagUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by cn40580 at 2018-06-27 10:21 AM.
 */
@Entity
@Table(name = "DICTIONARY")
public class DictionaryDTO extends AbstractPersistableEntity<Long> {

    @JoinColumn(name = "TITLE_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText title;

    @JoinColumn(name = "DESC_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText desc;

    @JoinColumn(name = "ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO attachment;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private DictionaryDTO parentDic;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentDic", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @OrderBy("seq asc")
    private Set<DictionaryDTO> childDics = new HashSet<>();

    @Column(name = "LEVEL")
    @Enumerated(EnumType.STRING)
    private DicLevelConstants level;


    @Value("0")
    private Integer seq;
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dictionary", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("created_date desc")
    private Set<DicSuggestionDTO> suggestions = new HashSet<>();

    public LongText getTitle() {
        return title;
    }

    public void setTitle(LongText title) {
        this.title = title;
    }

    public LongText getDesc() {
        return desc;
    }

    public void setDesc(LongText desc) {
        this.desc = desc;
    }

    public DictionaryDTO getParentDic() {
        return parentDic;
    }

    public void setParentDic(DictionaryDTO parentDic) {
        this.parentDic = parentDic;
    }

    public DicLevelConstants getLevel() {
        return level;
    }

    public void setLevel(DicLevelConstants level) {
        this.level = level;
    }

    public Set<DicSuggestionDTO> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Set<DicSuggestionDTO> suggestions) {
        this.suggestions = suggestions;
    }

    public Set<DictionaryDTO> getChildDics() {
        return childDics;
    }

    public void setChildDics(Set<DictionaryDTO> childDics) {
        this.childDics = childDics;
    }

    public AttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentDTO attachment) {
        this.attachment = attachment;
    }

    public String getDescWithOutHtmlTag(Locale lang) {
        String result = "";
        if (desc != null) {
            for (LongTextTranslation t : desc.getTranslations()) {
                if (lang != null && lang.equals(t.getLanguage()))
                    result = HtmlTagUtil.removeHtmlTags(t.getText());
            }
        }
        return result;
    }
}
