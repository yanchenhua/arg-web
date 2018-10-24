package ltd.ontsol.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import ltd.ontsol.core.constants.PersistenceConstants;

//@Audited TODO
@MappedSuperclass
//@EntityListeners(EntityAuditEventListener.class) TODO
public class AbstractAuditingEntity extends AbstractPersistableEntity<Long> {
    @CreatedBy
//    @NotNull
    @Size(max = PersistenceConstants.LENGTH_100)
    @Column(length = PersistenceConstants.LENGTH_100)
    private String createdBy;

    @CreatedDate
//    @NotNull
    private Date createdDate;

    @LastModifiedBy
    @Size(max = PersistenceConstants.LENGTH_100)
    @Column(length = PersistenceConstants.LENGTH_100)
    private String lastModifiedBy;

    @LastModifiedDate
    private Date lastModifiedDate;


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


}
