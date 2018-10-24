package ltd.ontsol.core.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by cn40580 at 2018-06-13 2:37 PM.
 */
@Entity
@Table(name = "DEVELOPMENT")
public class DevelopmentDTO extends AbstractPersistableEntity<Long> {

    //    private String developTextStr;
    @JoinColumn(name = "DEVELOP_TEXT_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText developText;

    private Date occurDate;

    private String occurDateStr;

    //此字段只作为前端接收，不做持久化处理
    @Transient
    private Long timestamp;

    private Integer sort;

    public LongText getDevelopText() {
        return developText;
    }

    public void setDevelopText(LongText developText) {
        this.developText = developText;
    }

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        this.occurDate = new Date(timestamp);
    }

    public String getOccurDateStr() {
        return occurDateStr;
    }

    public void setOccurDateStr(String occurDateStr) {
        this.occurDateStr = occurDateStr;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
