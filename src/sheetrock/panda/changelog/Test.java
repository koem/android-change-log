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
    private Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cl = new ChangeLog(this);
        
        TextView tv = (TextView) this.findViewById(R.id.textview);
        String s = tv.getText() + "\nlast version: '" + cl.getLastVersion()
        		+ "'\nversion in manifest: '" + cl.getThisVersion()
                + "'\nfirst run: " + cl.firstRun()
                + "\nfirst run ever: " + cl.firstRunEver();

        spinner = (Spinner) this.findViewById(R.id.spinnerLastVersion);
        populatespinner(cl.getLastVersion());

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

    class Version {
        public String versionName;
        public String display;
        public Version(String versionName, String display) {
            this.versionName = versionName;
            this.display = display;
        }
        public String toString() {
            return  this.display;
        }
    }

    private void populatespinner(String lastVersion) {
        ArrayAdapter<Version> adapter = new ArrayAdapter<Version>(
                    this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spinner.setAdapter(adapter);

        // read changelog.txt file
        String n = "", d = "<none>";
        try {
            InputStream ins = this.getResources().openRawResource(
                    R.raw.changelog);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            String line = null;
            while (( line = br.readLine()) != null){
                line = line.trim();
                if (line.startsWith("$")) {
                    if ("".equals(d))
                        d = n;
                    adapter.add(new Version (n, d));
                    if (n.equals(lastVersion))
                        spinner.setSelection(adapter.getCount()-1);
                    // begin of a version section
                    n = line.substring(1).trim();
                    d = "";
                } else if (line.startsWith("%")) {
                    d = line.substring(1).trim();
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setLastVersion() {
        Version v = (Version) this.spinner.getSelectedItem();
        cl.setLastVersion(v.versionName);
    }
}
