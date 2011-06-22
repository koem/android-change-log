package sheetrock.panda.changelog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Test extends Activity {
    /** Called when the activity is first created. */
    ChangeLog cl = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tv = (TextView) this.findViewById(R.id.textview);
        cl = new ChangeLog(this);
        
        String s = tv.getText() + "\nsaved version: " + cl.getSavedVersion()
        		+ "\nversion in manifest: " + cl.getManifestVersion()
                + "\nfirst run: " + cl.firstRun();
        
        tv.setText(s);

        ((Button) this.findViewById(R.id.changelog)).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Test.this.cl.getLogDialog().show(); 
                    }
                });

        ((Button) this.findViewById(R.id.full_changelog)).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Test.this.cl.getFullLogDialog().show(); 
                    }
                });

        if (cl.firstRun())
            cl.getLogDialog().show();
    }
}
