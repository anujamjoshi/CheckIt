package anuja.checkit;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import static android.media.CamcorderProfile.get;


public class ToDo extends AppCompatActivity {
    private Button btnAdd;
    private EditText et;
    private ListView lv;
    ArrayList<String> list ;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        btnAdd = (Button)findViewById(R.id.addToDoButton1);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = et.getText().toString();
                if(input.length() > 0)
                {
                    // add string to the adapter, not the listview
                    adapter.add(input);
                    System.out.println(input);
                    // no need to call adapter.notifyDataSetChanged(); as it is done by the adapter.add() method
                }
            }
        });
        et = (EditText)findViewById(R.id.editTodoTextText);
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);

        // set the lv variable to your list in the xml
        lv=(ListView)findViewById(R.id.listViewToDo);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = (TextView) view;
                if ((text.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) > 0) {
                    text.setPaintFlags( text.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }
                else{
                    text.setPaintFlags( text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });
    }





}