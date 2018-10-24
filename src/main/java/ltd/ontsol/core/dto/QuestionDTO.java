package ltd.ontsol.core.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.QuestionTypeConstants;

/**
 * Created by cn40580 at 2018-06-21 2:55 PM.
 */
@Entity
@Table(name = "QUESTION")
public class QuestionDTO extends AbstractPersistableEntity<Long> {
    @JoinColumn(name = "QUESTION_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText question;

    @JoinColumn(name = "ANSWER_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LongText answer;
    private Integer sort;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionTypeConstants type;

    public LongText getQuestion() {
        return question;
    }

    public void setQuestion(LongText question) {
        this.question = question;
    }

    public LongText getAnswer() {
        return answer;
    }

    public void setAnswer(LongText answer) {
        this.answer = answer;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public QuestionTypeConstants getType() {
        return type;
    }

    public void setType(QuestionTypeConstants type) {
        this.type = type;
    }
}
