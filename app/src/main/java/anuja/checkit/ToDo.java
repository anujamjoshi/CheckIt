package anuja.checkit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import io.paperdb.Book;
import io.paperdb.Paper;

import static android.R.attr.id;
import static android.R.id.input;
import static android.R.id.list;
import static android.system.Os.remove;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    LinearLayout lLayout;
    private EditText et;
    private ArrayList<ToDoVals> toDoValsArrayList;
    private ArrayList<CheckBox> toDoCheckBoxList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Paper.book().write("Test", cbList);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
        et = (EditText) findViewById(R.id.editTodoTextText);
        toDoValsArrayList = Paper.book().read("ToDOList");
       if (toDoValsArrayList!=null && toDoValsArrayList.size()>0){
           Log.d("Test", toDoValsArrayList.size()+"");
           for (ToDoVals a : toDoValsArrayList){
               final CheckBox cb = new CheckBox(ToDo.this);
               cb.setText(a.getTextVal());
               cb.setChecked(a.isDone());
               cb.setId(a.getId());
//               toDoCheckBoxList.add(cb);
               lLayout.addView(cb);
               cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                       Log.d("IS Checked ", cb.isChecked()+" " +toDoValsArrayList.get(cb.getId()).isDone()+"");
                       int id = cb.getId();
                       toDoValsArrayList.get(id).switchDone();
                       Log.d("IS Checked ", cb.isChecked()+" " +toDoValsArrayList.get(cb.getId()).getTextVal()+ " " +toDoValsArrayList.get(cb.getId()).isDone()+"");
                        Paper.book().write("ToDOList", toDoValsArrayList);
                   }
               });

           }
       }
       else{
           toDoValsArrayList= new ArrayList<>();
       }
       toDoCheckBoxList= new ArrayList<>();
        btnAdd = (Button) findViewById(R.id.addToDoButton1);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               String input = (et.getText().toString());
                if (input.length()>0 ){
                    final ToDoVals tdv = new ToDoVals(input, false, toDoValsArrayList.size());
                    final CheckBox cb = new CheckBox(ToDo.this);
                    cb.setText(input);
                    cb.setId(toDoValsArrayList.size());
                    lLayout.addView(cb);
            //        toDoCheckBoxList.add(cb);
                    toDoValsArrayList.add(tdv);
                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            Log.d("IS Checked ", cb.isChecked()+" " +toDoValsArrayList.get(cb.getId()).isDone()+"");
                            int id = cb.getId();
                            toDoValsArrayList.get(id).switchDone();
                            Paper.book().write("ToDOList", toDoValsArrayList);

                        }


                    });
                    cb.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            String text = cb.getText().toString();
                            for (int i =0; i < toDoValsArrayList.size(); i++){
                                if(toDoValsArrayList.get(i).getId() == cb.getId()){
                                    toDoValsArrayList.remove(i);
                                }
                            }
                            lLayout.removeView(view);
                            return true;
                        }
                    });

                }
                Log.d("Size" , toDoValsArrayList.size()+"");
                Paper.book().write("ToDOList" , toDoValsArrayList);
            }
        });
    }



}