package anuja.checkit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import static android.system.Os.remove;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    LinearLayout lLayout;
    private EditText et;
    private ArrayList<CheckBox> cbList = new ArrayList<>();
    public ArrayList<CheckBox> getCbList() {
        return cbList;
    }
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
//                            Log.d("Hello", cbList.size() +" " +  checkBox.getText() + "");
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




}