package ask4some.ask4some;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by David on 19/05/2015.
 */
public class QuestionList implements Serializable {

    private ArrayList<Question> question_list;
    private final String TEXT1 = "text1";
    private final String TEXT2 = "text2";

    public QuestionList(ArrayList<Question> question_list) {
        this.question_list = question_list;
    }

/*    // Constructor from file
    public QuestionList(String filename){
        QuestionList ql = new QuestionList(new ArrayList<Question>());

        this.question_list = ql.question_list;
    }*/

    public ArrayList<Question> getQuestion_list() {
        return question_list;
    }

    public void setQuestion_list(ArrayList<Question> question_list) {
        this.question_list = question_list;
    }

    @Override
    public String toString() {
        return "QuestionList{" +
                "question_list=" + question_list.toString() +
                '}';
    }


    public String[] toArrayString() {
        String [] questions = new String[this.question_list.size()];
        for (int i=0; i < question_list.size(); i++){
            questions[i] = question_list.get(i).getQuestion_text();
        }

        return questions;
    }

    public List<Map<String, String>> toListMapString() {


        final List<Map<String, String>> listItem =
                new ArrayList<Map<String, String>>(this.question_list.size());

        for (final Question q: question_list) {
            final Map<String, String> listItemQuestion = new HashMap<String, String>();
            listItemQuestion.put(TEXT1, q.getQuestion_text());
            listItemQuestion.put(TEXT2, q.getAnswer_text());
            listItem.add(Collections.unmodifiableMap(listItemQuestion));
        }

        return Collections.unmodifiableList(listItem);
    }

    public ListAdapter createListAdapter(Context c) {
        final String[] fromMapKey = new String[] {TEXT1, TEXT2};
        final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
        final List<Map<String, String>> list = toListMapString();

        return new SimpleAdapter(c, list,
                android.R.layout.simple_list_item_2,
                fromMapKey, toLayoutId);
    }
}
