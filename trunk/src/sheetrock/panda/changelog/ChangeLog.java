package sheetrock.panda.changelog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.WebView;

public class ChangeLog {
	
	private final Context context;
	private String oldVersion, thisVersion;
	private SharedPreferences sp;

	private static final String VERSION_KEY = "PREFS_VERSION_KEY";
	
	public ChangeLog(Context context) {
		this.context = context;

		this.sp = PreferenceManager.getDefaultSharedPreferences(context);

    	// get version numbers
    	this.oldVersion = sp.getString(VERSION_KEY, "");
    	Log.d(TAG, "oldVersion: " + oldVersion);
    	this.thisVersion = context.getResources().getString(R.string.version);
    	Log.d(TAG, "thisVersion: " + thisVersion);
    	
    	// save new version number to preferences
    	SharedPreferences.Editor editor = sp.edit();
    	editor.putString(VERSION_KEY, thisVersion);
    	editor.commit();
	}

	public boolean firstRun() {
    	return  ! oldVersion.equals(thisVersion);
	}

	public Dialog getLogDialog() {
		return  this.getDialog(false);
	}
	
	public Dialog getFullLogDialog() {
		return  this.getDialog(true);
	}
	
	private Dialog getDialog(boolean full) {
		
		WebView wv = new WebView(this.context);
        wv.setBackgroundColor(0);
        wv.loadData(this.getLog(full), "text/html", "UTF-8");

		AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
		builder.setTitle(context.getResources().getString(
				full 
			        ? R.string.full_change_log_title
			        : R.string.whats_new_title))
				.setView(wv)
				.setCancelable(false)
				.setPositiveButton(
						context.getResources().getString(
								R.string.change_log_ok_button),
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		return  builder.create();	
	}
	
	public String getLog() {
		return  this.getLog(false);
	}

	public String getFullLog() {
		return  this.getLog(true);
	}

	private enum Listmode {
		NONE,
		ORDERED,
		UNORDERED,
	};
	private Listmode listMode = Listmode.NONE;
	private StringBuffer sb = new StringBuffer();
	private static final String EOF = "END_OF_CHANGE_LOG";

	private String getLog(boolean full) {
    	// read change log file
    	try {
    		InputStream ins = context.getResources().openRawResource(
    				R.raw.changelog);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(ins));

        	String line = null;
        	boolean advanceToEOF = false;
        	while (( line = br.readLine()) != null){
        		line = line.trim();
        		if (line.startsWith("$")) {
        			this.closeList();
    				String version = line.substring(1).trim();
    				// stop output?
        			if (! full  &&  this.oldVersion.equals(version))
    					advanceToEOF = true;
    				else if (version.equals(EOF))
    					advanceToEOF = false;
        		} else if (! advanceToEOF) {
        			if (line.startsWith("%")) {
	        			// line contains version title
	        			this.closeList();
	        			sb.append("<div class='title'>" 
	        					+ line.substring(1).trim() + "</div>\n");
	        		} else if (line.startsWith("_")) {
	        			// line contains version title
	        			this.closeList();
	        			sb.append("<div class='subtitle'>" 
	        					+ line.substring(1).trim() + "</div>\n");
	        		} else if (line.startsWith("!")) {
	        			// line contains free text
	        			this.closeList();
	        			sb.append("<div class='freetext'>" 
	        					+ line.substring(1).trim() + "</div>\n");
	        		} else if (line.startsWith("#")) {
	        			this.openList(Listmode.ORDERED);
	        			sb.append("<li>" 
	        					+ line.substring(1).trim() + "</li>\n");
	    			} else if (line.startsWith("*")) {
	    				this.openList(Listmode.UNORDERED);
	        			sb.append("<li>" 
	        					+ line.substring(1).trim() + "</li>\n");
	        		} else {
	        			this.closeList();
	        			sb.append(line + "\n");
	        		}
        		}
        	}
			this.closeList();
			br.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}

    	Log.d(TAG, sb.toString());
    	return  sb.toString();
    }
	
	private void openList(Listmode listMode) {
		if (this.listMode != listMode) {
			closeList();
			if (listMode == Listmode.ORDERED) {
				sb.append("<div class='list'><ol>\n");
			} else if (listMode == Listmode.UNORDERED) {
				sb.append("<div class='list'><ul>\n");
			}
			this.listMode = listMode;
		}
	}
	private void closeList() {
		if (this.listMode == Listmode.ORDERED) {
			sb.append("</ol></div>\n");
		} else if (this.listMode == Listmode.UNORDERED) {
			sb.append("</ul></div>\n");
		}
		this.listMode = Listmode.NONE;
	}

	private static final String TAG = "ChangeLog";
}
