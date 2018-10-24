package ltd.ontsol.core.dto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ltd.ontsol.core.AbstractAuditingEntity;

/**
 * Created by cn40580 at 2018-06-27 10:39 AM.
 */
@Entity
@Table(name = "DIC_SUGGESTION")
public class DicSuggestionDTO extends AbstractAuditingEntity {
    private String text1;
    private String staffCode;
    private String staffName;
    private Boolean checkFlag;
    private String reply;

    @ManyToOne
    @JoinColumn(name = "DICTIONARY_ID")
    @JsonIgnore
    private DictionaryDTO dictionary;

    public String getText() {
        return text1;
    }

    public void setText(String text1) {
        this.text1 = text1;
    }

    public DictionaryDTO getDictionary() {
        return dictionary;
    }

    public void setDictionary(DictionaryDTO dictionary) {
        this.dictionary = dictionary;
    }

    public String getStaffCode() {
        String[] letters = staffCode.split("");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];
            if (letters.length - (i + 1) >= 2) {
                sb.append("*");
            } else {
                sb.append(letter);
            }

        }
        return sb.toString();
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getReply(){return reply;}

    public void setReply(String reply){this.reply = reply;}
}
