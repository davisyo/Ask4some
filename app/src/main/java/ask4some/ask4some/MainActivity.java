package ask4some.ask4some;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import java.lang.reflect.Array;


public class MainActivity extends ActionBarActivity {

    private String question_text;

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goto2 = (Button) findViewById(R.id.button1);

        // Accion del boton
        goto2.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("parametro", question_text);
                startActivity(intent);
            }
        });



    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onSendClick(View v) {

        Button send_question= (Button) v;
        send_question.setText("Question sent");
        //setContentView(R.layout.activity_main);

        EditText mEdit = (EditText)findViewById(R.id.question_input);

        // Store the question in a variable
        this.question_text =  mEdit.getText().toString();

        TextView show_question = (TextView) findViewById(R.id.show_question);
        show_question.setText(this.question_text);

    }

    public void onGoto2Click(View v){
        Button Goto2 = (Button) v;
        Goto2.setText("going to 2");
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
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
}
