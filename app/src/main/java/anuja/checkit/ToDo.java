package anuja.checkit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    private EditText et;
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

                    LinearLayout lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
                    CheckBox checkBox = new CheckBox(ToDo.this);
                    checkBox.setText(input);
                    lLayout.addView(checkBox);
                }
            }
        });
        et = (EditText) findViewById(R.id.editTodoTextText);


    }
}