package ltd.ontsol.core.dto;

import ltd.ontsol.core.AbstractPersistableEntity;

/**
 * Created by CN40580 at 2018-07-11 9:55 AM.
 */
public class LegalDTO extends AbstractPersistableEntity<Long> {

    LongText content;
    LongText content2;

    public LongText getContent() {
        return content;
    }

    public void setContent(LongText content) {
        this.content = content;
    }

    public LongText getContent2() {
        return content2;
    }

    public void setContent2(LongText content2) {
        this.content2 = content2;
    }
}
