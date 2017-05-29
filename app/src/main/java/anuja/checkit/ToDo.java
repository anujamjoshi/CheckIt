package anuja.checkit;

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
import java.util.ArrayList;
import io.paperdb.Paper;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    LinearLayout lLayout;
    private EditText et;
    private ArrayList<ToDoVals> toDoValsArrayList;
   // private int numRemoved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Paper.book().write("Test", cbList);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
        et = (EditText) findViewById(R.id.editTodoTextText);
        toDoValsArrayList = Paper.book().read("ToDOList");
     //  numRemoved=0;
       if (toDoValsArrayList!=null && toDoValsArrayList.size()>0){
           Log.d("Test", toDoValsArrayList.size()+"");
           for (ToDoVals a : toDoValsArrayList){
               final CheckBox cb = new CheckBox(ToDo.this);
               cb.setText(a.getTextVal());
               cb.setChecked(a.isDone());
               cb.setId(a.getId());
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
//               cb.setOnLongClickListener(new View.OnLongClickListener(){
//
//                   @Override
//                   public  boolean onLongClick(View view1){
//
//
//                       toDoValsArrayList.remove(cb.getId()-numRemoved);
//                       lLayout.removeView(view1);
//                       Paper.book().write("ToDOList", toDoValsArrayList);
//                        numRemoved++;
//                       return true;
//                   }
//               });

           }
       }
       else{
           toDoValsArrayList= new ArrayList<>();
//           numRemoved=0;
       }
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
                    toDoValsArrayList.add(tdv);
                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                            Log.d("IS CheckBoxed ", cb.isChecked()+" " +toDoValsArrayList.get(cb.getId()).isDone()+"");
                            int id = cb.getId();
                            toDoValsArrayList.get(id).switchDone();
                            Paper.book().write("ToDOList", toDoValsArrayList);

                        }


                    });
//                  cb.setOnLongClickListener(new View.OnLongClickListener(){
//
//                      @Override
//                      public  boolean onLongClick(View view1){
//                         String text = cb.getText().toString();
//                          int index =
//                          for(ToDoVals tdv : toDoValsArrayList){
//                              if (tdv.getTextVal().equals(text)){
//
//                              }
//                          }
//                          lLayout.removeView(view1);
//                          Paper.book().write("ToDOList", toDoValsArrayList);
//                          numRemoved++;
//                          return true;
//                      }
//                  });
//
                }
//                Log.d("Size" , toDoValsArrayList.size()+"");
                Paper.book().write("ToDOList" , toDoValsArrayList);
            }
        });

    }



}