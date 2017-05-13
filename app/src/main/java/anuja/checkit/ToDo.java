package anuja.checkit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ToDo extends Activity  {
    RelativeLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ScrollView sv = new ScrollView(this);
        ll = (RelativeLayout)findViewById(R.id.RelativeLayoutToDO);
        sv.addView(ll);
        TextView tv = new TextView(this);
        EditText et = (EditText)findViewById(R.id.editTodoText);
//        ll.addView(et);
        Button b = (Button)findViewById(R.id.addTodoButtonToDo);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < 20; i++) {
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setText("I'm dynamic!");
                    ll.addView(cb);
                }
            }
        });


    }


}
