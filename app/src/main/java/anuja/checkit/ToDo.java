package anuja.checkit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class ToDo extends Activity  {
    RelativeLayout ll;
    EditText et;
    LinearLayout lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ll = (RelativeLayout)findViewById(R.id.relativeLayoutTodo);
        lv = (LinearLayout) findViewById(R.id.listViewTodo) ;
        et = (EditText)findViewById(R.id.editTodoText);
        Button b = (Button)findViewById(R.id.addTodoBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(et.getText());
                lv.addView(cb);
            }
        });


    }


}
