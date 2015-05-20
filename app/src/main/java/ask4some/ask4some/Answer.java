package ask4some.ask4some;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Answer extends ActionBarActivity {
    private QuestionList ql;
    private int index;
    private String filename = "questionlist.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        // Retrieving the question passed as parameter
        this.ql = (QuestionList) getIntent().getExtras().getSerializable("parametro");
        this.index = (int) getIntent().getExtras().getSerializable("index");
        // Writing the title of the question
        TextView title_question = (TextView) findViewById(R.id.active_question);
        title_question.setText(ql.getQuestion(index).getQuestion_text());
        // Writing the current answer
        EditText input = (EditText) findViewById(R.id.editText);
        input.setText(ql.getQuestion(index).getAnswer_text());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAnswerClick(View v){
        Button Answer = (Button) v;
        //Answer.setText("Sending Answer");


        // Saving the current answer
        EditText input = (EditText) findViewById(R.id.editText);
        String given_answer = input.getText().toString();
        this.ql.getQuestion(index).setAnswer_text(given_answer);
        ql.Serializa(filename,this);

        // Calling MainActivity2
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
