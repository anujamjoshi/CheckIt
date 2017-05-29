package anuja.checkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import io.paperdb.Paper;

import static android.R.attr.dial;
import static android.R.attr.key;

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
        expListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, final int groupPosition, final int childPosition, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Notes.this);
                builder.setMessage("Are you sure you want to remove this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String key = listDataHeader.get(groupPosition);
                                listDataChild.get(key).remove(childPosition);
                                if (listDataChild.get(key).size()<=0){
                                    listDataHeader.remove(key);
                                }
                                Paper.book().write("HeaderList", listDataHeader);
                                Paper.book().write("ChildList", listDataChild);
                                listAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = Paper.book().read("HeaderList");
        listDataChild = Paper.book().read("ChildList");
        if(listDataChild==null && listDataHeader==null) {
            listDataHeader = new ArrayList<>();
            listDataChild = new HashMap<>();
        }


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
//                    Log.d("Hello", listDataChild.size()+"" );
                    Paper.book().write("HeaderList", listDataHeader);
                    Paper.book().write("ChildList", listDataChild);
                    listAdapter.notifyDataSetChanged();
                }
            }
        });
        etNote = (EditText)findViewById(R.id.editNoteContent);
    }
}