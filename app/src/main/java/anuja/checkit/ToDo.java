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

import static android.R.attr.id;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    LinearLayout lLayout;
    private EditText et;
    private ArrayList<ToDoVals> toDoValsArrayList;
    private ArrayList<CheckBox> toDoCheckBoxesArrayList;
   // private int numRemoved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Paper.book().write("Test", cbList);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        lLayout = (LinearLayout) findViewById(R.id.listViewToDo);
        et = (EditText) findViewById(R.id.editTodoTextText);
        toDoCheckBoxesArrayList=new ArrayList<>();
        toDoValsArrayList = Paper.book().read("ToDOList");
     //  numRemoved=0;
       if (toDoValsArrayList!=null && toDoValsArrayList.size()>0){
           Log.d("Test", toDoValsArrayList.size()+"");
           for (ToDoVals a : toDoValsArrayList){
                a.setId(toDoValsArrayList.indexOf(a));
               final CheckBox cb = new CheckBox(ToDo.this);
               cb.setText(a.getTextVal());
               cb.setChecked(a.isDone());
               cb.setId(a.getId());
               lLayout.addView(cb);
               toDoCheckBoxesArrayList.add(cb);

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
              cb.setOnLongClickListener(new View.OnLongClickListener(){

               @Override
               public  boolean onLongClick(View view1){
                   String text = cb.getText().toString();
                   int index = cb.getId();
                   Log.d("INDEX", cb.getId() + " " + toDoCheckBoxesArrayList.get(cb.getId()).getText());
                   toDoCheckBoxesArrayList.remove(cb.getId());
                   toDoValsArrayList.remove(cb.getId());
                   for (int i = toDoValsArrayList.size()-1; i >= index; i--){
                       toDoValsArrayList.get(i).setId(toDoValsArrayList.get(i).getId()-1);
                       toDoCheckBoxesArrayList.get(i).setId(toDoValsArrayList.get(i).getId());
                   }
                   lLayout.removeView(view1);
                   Paper.book().write("ToDOList", toDoValsArrayList);

                   return true;
               }
           });
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
                et.getText().clear();
                if (input.length()>0 ){
                    final ToDoVals tdv = new ToDoVals(input, false, toDoValsArrayList.size());
                    final CheckBox cb = new CheckBox(ToDo.this);
                    cb.setText(input);
                    cb.setId(toDoValsArrayList.size());
                    lLayout.addView(cb);
                    toDoValsArrayList.add(tdv);
                    toDoCheckBoxesArrayList.add(cb);

                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                            Log.d("IS CheckBoxed ", cb.isChecked()+" " +toDoValsArrayList.get(cb.getId()).isDone()+"");
                            int id = cb.getId();
                            toDoValsArrayList.get(id).switchDone();
                            Paper.book().write("ToDOList", toDoValsArrayList);

                        }


                    });
                  cb.setOnLongClickListener(new View.OnLongClickListener(){

                      @Override
                      public  boolean onLongClick(View view1){
                         String text = cb.getText().toString();
                         int index = cb.getId();
                          Log.d("INDEX", cb.getId() + " " + toDoCheckBoxesArrayList.get(cb.getId()).getText());
                          toDoCheckBoxesArrayList.remove(cb.getId());
                          toDoValsArrayList.remove(cb.getId());
                          for (int i = toDoValsArrayList.size()-1; i >= index; i--){
                              toDoValsArrayList.get(i).setId(toDoValsArrayList.get(i).getId()-1);
                              toDoCheckBoxesArrayList.get(i).setId(toDoValsArrayList.get(i).getId());
                          }
                          lLayout.removeView(view1);
                          Paper.book().write("ToDOList", toDoValsArrayList);

                          return true;
                      }
                  });
                }
//                Log.d("Size" , toDoValsArrayList.size()+"");
                Paper.book().write("ToDOList" , toDoValsArrayList);
            }
        });

    }



}