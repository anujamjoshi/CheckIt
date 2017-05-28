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

import io.paperdb.Book;
import io.paperdb.Paper;

import static android.R.id.input;
import static android.R.id.list;
import static android.system.Os.remove;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    LinearLayout lLayout;
    private EditText et;
    private ArrayList<String> cbTextList = new ArrayList<>();
    private ArrayList<CheckBox> cbList = new ArrayList<>();

    public ArrayList<String> getCbList() {
        return cbTextList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Paper.book().write("Test", cbList);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //Auto-populate
        cbTextList = Paper.book().read("Test");
        if (cbTextList!=null && cbTextList.size()>0){
            Log.d("NUM" , cbTextList.size()+"");
            for(String s : cbTextList) {
                lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
                CheckBox cb = new CheckBox(ToDo.this);
                cb.setText(s);
                cbList.add(cb);
                lLayout.addView(cb);
            }

        }
        else
        {
            Log.d("NO NUM" , "bhggftd");
        }
        btnAdd = (Button) findViewById(R.id.addToDoButton1);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = et.getText().toString();
//                String input = Paper.book().read("Test");
                if (input.length() > 0) {

                    lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
                    final CheckBox cb = new CheckBox(ToDo.this);
                    cb.setText(input);

//                    final CheckBox checkBox = new CheckBox(ToDo.this);
//                    checkBox.setText(input);
//                    checkBox.setId(cbList.size());
                    cbTextList.add(input);
                    Log.d("NUM ON CLICK" , cbTextList.size()+"");
                    cbList.add(cb);
                    lLayout.addView(cb);
                    cb.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            Log.d("Hello", cbTextList.size() +"");
                            cbTextList.remove(cb.getText());
                            cbList.remove(cb);
                            lLayout.removeView(view);

                            return true;
                        }
                    });



                }
                Paper.book().write("Test", cbTextList);

            }
        });

        et = (EditText) findViewById(R.id.editTodoTextText);
    }
//    protected void onStop(Bundle savedInstanceState) {
//        Log.d("TNUm" , cbList.size()+"");
//
//    }



}