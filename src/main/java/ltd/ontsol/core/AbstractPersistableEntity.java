package ltd.ontsol.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;
import org.springframework.validation.FieldError;

/**
 * Created by lewei at 2018-06-13 11:20 AM.
 */
@MappedSuperclass
public abstract class AbstractPersistableEntity<P extends Serializable> implements Persistable<P> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arg_hibernate_seq")
    @SequenceGenerator(name = "arg_hibernate_seq", sequenceName = "arg_hibernate_seq", allocationSize = 1)
    private P id;

    @Transient
    private String code;
    @Transient
    private List<FieldError> errors;

    @Override
    @Transient
    public boolean isNew() {
        return null == this.getId();
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), this.getId());
    }

    @Override
    public boolean equals(final Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        AbstractPersistableEntity<?> that = (AbstractPersistableEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == this.getId() ? 0 : this.getId().hashCode() * 31;
        return hashCode;
    }

    @Override
    public P getId() {
        return id;
    }

    public void setId(final P id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
