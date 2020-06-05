package sg.edu.rp.c346.id19037610.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTasks;
    Spinner option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        lvTasks = findViewById(R.id.lvTasks);
        btnDelete = findViewById(R.id.btnDelete);
        option = findViewById(R.id.spinner);
        final ArrayList<String> tasks = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, tasks );
        lvTasks.setAdapter(adapter);

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String op = tasks.get(position);
                Toast.makeText(MainActivity.this, ""+op, Toast.LENGTH_SHORT).show();
            }
        });

        option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //Add a task
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etInput.setHint(R.string.newTask);
                        break;
                    case 1:
                        //Remove a task
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etInput.setHint(R.string.deleteTask);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = etInput.getText().toString();
                tasks.add(todo);
                adapter.notifyDataSetChanged();
                etInput.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etInput.getText().toString());
                if(tasks.size()==0){
                    Toast.makeText(MainActivity.this,R.string.noTaskToRemove, Toast.LENGTH_SHORT).show();
                }else if(pos>tasks.size()-1 || pos<0){
                    Toast.makeText(MainActivity.this,R.string.wrongIndex, Toast.LENGTH_SHORT).show();
                }else{
                    tasks.remove(pos);
                    adapter.notifyDataSetChanged();
                }
                etInput.setText("");

            }
        });
    }
}
