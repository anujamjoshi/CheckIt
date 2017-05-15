package anuja.checkit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import static android.R.id.input;
import static android.R.id.list;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    LinearLayout lLayout;
    private EditText et;
    private ArrayList<CheckBox> cbList = new ArrayList<>();
    private String PREF_NAME = "toDo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //Auto-populate

        btnAdd = (Button) findViewById(R.id.addToDoButton1);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = et.getText().toString();
                if (input.length() > 0) {

                    lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
                    final CheckBox checkBox = new CheckBox(ToDo.this);
                    checkBox.setText(input);
                    checkBox.setId(cbList.size());
                    cbList.add(checkBox);

                    checkBox.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            cbList.remove(checkBox);
                            lLayout.removeView(view);
                            return true;
                        }
                    });
                    lLayout.addView(checkBox);
                }
            }
        });
        et = (EditText) findViewById(R.id.editTodoTextText);

    }
    public void saveCheckBoxStates() {

        for(CheckBox c: cbList){
            saveToPreferences(c.getText().toString(), c.isChecked());
        }
    }
    public void saveToPreferences(String s, boolean b) {
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        editor.putString(s, ""+b);
        editor.commit();
    }
    // HELPER METHOD - Autopopulate data from SharedPreferences File to activity
    public void retrieveFromPreference() {
        SharedPreferences prefs = this.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Map<String, ?> keys = prefs.getAll();

        for(Map.Entry<String, ?> entry : keys.entrySet()) {
            String key = entry.getKey();

            if(entry.getValue().equals("true")) {

                lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
                final CheckBox checkBox = new CheckBox(ToDo.this);
                checkBox.setText(key);
                checkBox.setId(cbList.size());
                checkBox.setChecked(true);
                cbList.add(checkBox);
                checkBox.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        cbList.remove(checkBox);
                        lLayout.removeView(view);
                        return true;
                    }
                });

            }

            else {
                if (entry.getValue().equals("false")) {
                    lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
                    final CheckBox checkBox = new CheckBox(ToDo.this);
                    checkBox.setText(key);
                    checkBox.setId(cbList.size());
                    checkBox.setChecked(false);
                    cbList.add(checkBox);
                    checkBox.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            cbList.remove(checkBox);
                            lLayout.removeView(view);
                            return true;
                        }
                    });
                }
            }
        }
    }


}