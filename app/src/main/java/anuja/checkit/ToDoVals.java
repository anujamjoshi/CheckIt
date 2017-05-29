package anuja.checkit;

import android.widget.CheckBox;

/**
 * Created by Anuja  on 5/28/17.
 */

public class ToDoVals {
    private String textVal;
    private boolean done;
    int id;
    public ToDoVals(String textVal, boolean done, int id){
        this.textVal= textVal;
        this.done = done;
        this.id = id;
    }
    public boolean isDone(){
        return  done;
    }

    public int getId() {
        return id;
    }

    public String getTextVal() {
        return textVal;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setTextVal(String textVal) {
        this.textVal = textVal;
    }

    public void switchDone() {
        done = !done;
    }
}
