package anuja.checkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(anuja.checkit.R.layout.activity_main);
        Button notesButton = (Button)  findViewById(R.id.notes_button);
        notesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Notes.class));
            }
        });
        Button toDoButton = (Button)  findViewById(R.id.toDo_button);
        toDoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ToDo.class));
            }
        });
//        Button collectionsButton = (Button)  findViewById(R.id.collections_button);
//        collectionsButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(MainActivity.this, Collections.class));
//            }
//        });
//        Button dailyLog = (Button)  findViewById(R.id.dailyLog_button);
//        dailyLog.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(MainActivity.this, DailyLog.class));
//            }
//        });
    }
}
