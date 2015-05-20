package ask4some.ask4some;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.Manifest;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity2 extends ActionBarActivity {

    protected String filename = "questionlist.dat";
    protected ListView listView;
    private QuestionList ql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Read the question passed as parameter from the first activity
/*        ArrayList <Question> question_collection = (ArrayList <Question>) getIntent().getExtras().getSerializable("parametro");
        TextView question_list = (TextView) findViewById(R.id.question_list);

        // Concatenate all questions
        String all_questions = "";
        for (int i = 0; i < question_collection.size(); i++){
            all_questions = all_questions + question_collection.get(i).getQuestion_text() + ";    ";
        }
        // Write them
        question_list.setText(all_questions);*/

        // Retrieve saved question from the file
        this.ql = Desserializa(filename);
        //TextView question_saved = (TextView) findViewById(R.id.saved_question);
        //if(ql != null) question_saved.setText(ql.toString());

        // Create the list view:

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        //String [] values;

        if(ql != null) {
            //values = ql.toArrayString();
            final ListAdapter listAdapter = ql.createListAdapter(this);
            listView.setAdapter(listAdapter);
        } else {
            //values = new String[] { "" };
        }


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        //listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position).toString();

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                // Jumping to Answer screen
                gotoAnswer(itemPosition);


            }


        });
    }

    public void gotoAnswer(int itemPosition){
        Intent intent = new Intent(this, Answer.class);
        intent.putExtra("parametro",this.ql);
        intent.putExtra("index",itemPosition);
        startActivity(intent);
    }

    public void onGoto1Click(View v){
        Button Goto1 = (Button) v;
        Goto1.setText("going to 1");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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

    public QuestionList Desserializa(String filename){
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
