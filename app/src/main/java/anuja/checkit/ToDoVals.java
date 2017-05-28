package anuja.checkit;

/**
 * Created by Anuja  on 5/28/17.
 */

public class ToDoVals {
    private String textVal;
    private boolean done;
    public ToDoVals(String textVal, boolean done){
        this.textVal= textVal;
        this.done = done;
    }
    public boolean isDone(){
        return  done;
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
}
