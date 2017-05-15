package anuja.checkit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    LinearLayout lLayout;
    private EditText et;
    private ArrayList<CheckBox> cbList = new ArrayList<>();
    private String PREF_NAME = "toDo.txt";
    protected int id = 0;

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


}