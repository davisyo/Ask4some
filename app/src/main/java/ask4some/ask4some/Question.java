package ask4some.ask4some;

import java.io.Serializable;

/**
 * Created by David on 19/05/2015.
 */
@SuppressWarnings("serial")
public class Question implements Serializable{
    private String question_text;
    private String answer_text;

    public Question(String question_text, String answer_text) {
        this.question_text = question_text;
        this.answer_text = answer_text;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_text='" + question_text + '\'' +
                ", answer_text='" + answer_text + '\'' +
                '}';
    }
}
