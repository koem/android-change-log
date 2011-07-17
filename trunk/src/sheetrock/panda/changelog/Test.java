package sheetrock.panda.changelog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test extends Activity {
    /** Called when the activity is first created. */
    private ChangeLog cl = null;
    private String realLastVersion;
    private Spinner spinner;
    private static final String ITEM_NONE = "<none>";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tv = (TextView) this.findViewById(R.id.textview);
        spinner = (Spinner) this.findViewById(R.id.spinnerLastVersion);

        populatespinner();
            
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

    private void populatespinner() {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                    this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spinner.setAdapter(adapter);

        adapter.add(ITEM_NONE);

        // read changelog.txt file
        try {
            InputStream ins = this.getResources().openRawResource(
                    R.raw.changelog);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            String line = null;
            while (( line = br.readLine()) != null){
                line = line.trim();
                if (line.startsWith("$")) {
                    // begin of a version section
                    adapter.add(line.substring(1).trim());
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setLastVersion() {
//    	String overwrittenversion = this.et.getText().toString();
//    	if (overwrittenversion == null  ||  overwrittenversion.length() == 0)
    		cl.setLastVersion(this.realLastVersion);
//    	else
//    		cl.setLastVersion(overwrittenversion);

    }
}
