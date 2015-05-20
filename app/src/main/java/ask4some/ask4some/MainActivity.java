package ask4some.ask4some;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private QuestionList question_list;
    protected String filename = "questionlist.dat";

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goto2 = (Button) findViewById(R.id.goto2);

        // Accion del boton
        goto2.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                //intent.putExtra("parametro", question_list);
                startActivity(intent);
            }
        });



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
        String q = mEdit.getText().toString();
        // Create a new question without answer
        Question user_question = new Question (q,"still no answer");

        // Initialize the list of questions by retrieving the file;
        this.question_list = Desserializa(filename) ;

        // if it is empty, we initialize it
        if (this.question_list == null)
                this.question_list = new QuestionList(new ArrayList<Question>());
        // Adding the new question
        this.question_list.getQuestion_list().add(user_question);

        // Save the list of questions into a file
        Serializa(question_list);

        TextView show_question = (TextView) findViewById(R.id.show_question);
        show_question.setText(user_question.getQuestion_text());

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

    // For saving the questions in a file
    protected void Serializa(QuestionList a){
        FileOutputStream fos;
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
            oos.close();
            Toast.makeText(this, "Objeto correctamente serializado y guardado", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error: archivo no encontrado", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    protected QuestionList Desserializa(String filename){
        FileInputStream fin;
        QuestionList afromfile = null;

        try {
            fin = openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fin);
            afromfile = (QuestionList) ois.readObject();
            ois.close();
            Toast.makeText(this, "Objeto des-serializado y extraído", Toast.LENGTH_SHORT).show();
        } catch (StreamCorruptedException e) {
            Toast.makeText(this, "Error en las comprobaciones de consistencia", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (OptionalDataException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return afromfile;
    }


}
