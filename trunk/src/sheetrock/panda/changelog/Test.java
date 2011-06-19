package sheetrock.panda.changelog;

import android.app.Activity;
import android.os.Bundle;

public class Test extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ChangeLog cl = new ChangeLog(this);
//        if (cl.firstRun())
        	cl.getFullLogDialog().show();
    }
}