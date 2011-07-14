package sheetrock.panda.changelog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Test extends Activity {
    /** Called when the activity is first created. */
    private ChangeLog cl = null;
    private String realLastVersion;
    private EditText et;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tv = (TextView) this.findViewById(R.id.textview);
        et = (EditText) this.findViewById(R.id.editversion);
        
        cl = new ChangeLog(this);
        
        String s = tv.getText() + "\nlast version: '" + cl.getLastVersion()
        		+ "'\nversion in manifest: '" + cl.getThisVersion()
                + "'\nfirst run: " + cl.firstRun()
                + "\nfirst run ever: " + cl.firstRunEver();

        this.realLastVersion = cl.getLastVersion();

        tv.setText(s);

        ((Button) this.findViewById(R.id.changelog)).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	Test.this.setLastVersion();
                        Test.this.cl.getLogDialog().show(); 
                    }
                });

        ((Button) this.findViewById(R.id.full_changelog)).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	Test.this.setLastVersion();
                        Test.this.cl.getFullLogDialog().show(); 
                    }
                });

        if (cl.firstRun())
            cl.getLogDialog().show();
    }
    
    private void setLastVersion() {
    	String overwrittenversion = this.et.getText().toString();
    	if (overwrittenversion == null  ||  overwrittenversion.length() == 0)
    		cl.setLastVersion(this.realLastVersion);
    	else
    		cl.setLastVersion(overwrittenversion);

    }
}
