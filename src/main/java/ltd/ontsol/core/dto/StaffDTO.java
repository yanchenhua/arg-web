package ltd.ontsol.core.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.StaffConstants;

/**
 * Created by cn40580 at 2018-07-02 6:05 PM.
 */
@Entity
@Table(name = "STAFF")
public class StaffDTO extends AbstractPersistableEntity<Long> {
    //员工号
    private String staffCode;
    private String name;

    @Enumerated(EnumType.STRING)
    private StaffConstants type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dictionary", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<DicSuggestionDTO> suggestions = new HashSet<>();


    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StaffConstants getType() {
        return type;
    }

    public void setType(StaffConstants type) {
        this.type = type;
    }

    public Set<DicSuggestionDTO> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Set<DicSuggestionDTO> suggestions) {
        this.suggestions = suggestions;
    }
}
