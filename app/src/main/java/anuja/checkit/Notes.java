package anuja.checkit;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import anuja.checkit.R;

public class Notes extends Activity implements AdapterView.OnItemClickListener
{
    private Button btnAdd;
    private EditText et;
    private ListView lv;
    ArrayList<String> list ;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        btnAdd = (Button)findViewById(R.id.addTaskBtn);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = et.getText().toString();
                if(input.length() > 0)
                {
                    // add string to the adapter, not the listview
                    adapter.add(input);
                    // no need to call adapter.notifyDataSetChanged(); as it is done by the adapter.add() method
                }
            }
        });
        et = (EditText)findViewById(R.id.editText);
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);

        // set the lv variable to your list in the xml
        lv=(ListView)findViewById(R.id.listView1);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(Notes.this);

    }
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        list.remove(position);
        adapter.notifyDataSetChanged();
    }



}