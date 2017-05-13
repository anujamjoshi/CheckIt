package anuja.checkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class Notes extends AppCompatActivity {
    private Button btnNote;
    private EditText etHead;
    private EditText etNote;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        etHead = (EditText)findViewById(R.id.editHeaderText);
        // Adding child data
        btnNote = (Button)findViewById(R.id.addnewNoteButton);
        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etNote.getText().toString();
                String header = etHead.getText().toString();
                if (header.length()<=0){
                    Toast.makeText(Notes.this, "PLEASE ENTER A HEADER", Toast.LENGTH_SHORT).show();
                }
               else if(input.length() > 0  && header.length()>0)
                {
                    if(listDataHeader.indexOf(header)>=0){
                        // we have an arraylist of this value
                        listDataChild.get(header).add(input);
                    }
                    else{
                        listDataHeader.add(header);
                        ArrayList<String> a = new ArrayList<String>();
                        a.add(input);
                        listDataChild.put(header, a);
                    }
                    listAdapter.notifyDataSetChanged();
                }
            }
        });
        etNote = (EditText)findViewById(R.id.editNoteContent);
    }
}