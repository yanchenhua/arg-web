package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.PersistenceConstants;

/**
 * Created by cn40580 at 2018-06-13 11:52 AM.
 */
@Entity
@Table(name = "LEADER")
public class LeaderDTO extends AbstractPersistableEntity<Long> {

    private String nameTextStr;
    private String roleTextStr;

    @Column(length = PersistenceConstants.LENGTH_4000)
    private String textStr;
    @JoinColumn(name = "NAME_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText nameText;

    @JoinColumn(name = "ROLE_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText roleText;

    @JoinColumn(name = "TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText text = new LongText();

    @JoinColumn(name = "HEADER_ATT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentDTO headerAttachment;

    private Integer sort;

    public LongText getNameText() {
        return nameText;
    }

    public void setNameText(LongText nameText) {
        this.nameText = nameText;
    }

    public LongText getRoleText() {
        return roleText;
    }

    public void setRoleText(LongText roleText) {
        this.roleText = roleText;
    }

    public LongText getText() {
        return text;
    }

    public void setText(LongText text) {
        this.text = text;
    }

    public AttachmentDTO getHeaderAttachment() {
        return headerAttachment;
    }

    public void setHeaderAttachment(AttachmentDTO headerAttachment) {
        this.headerAttachment = headerAttachment;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getNameTextStr() {
        return nameTextStr;
    }

    public void setNameTextStr(String nameTextStr) {
        this.nameTextStr = nameTextStr;
    }

    public String getRoleTextStr() {
        return roleTextStr;
    }

    public void setRoleTextStr(String roleTextStr) {
        this.roleTextStr = roleTextStr;
    }

    public String getTextStr() {
        return textStr;
    }

    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }
}
