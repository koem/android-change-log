<table><tr><td>
<h1>Android Change Log</h1>

<hr />

<wiki:gadget url="https://gcodeadsense.googlecode.com/svn/release/gCodeAdSense/gCodeAdSense.xml" border="0" width="728" height="15" up_ad_client="6957634847281469" up_ad_slot="8380964634" up_ad_width="728" up_ad_height="15" /><br>
<br>
<hr />

With <b>Android Change Log</b> you can easily create, show and maintain an Android changelog dialog to present the recent changes / release notes of your Android App on the user's Android device (tablet / smartphone / whatever).<br>
<br>
Features are:<br>
<ul><li>It's <b>open source</b>, <b>no license</b> attached, and <b>free of charge!</b>
</li><li>display only what's new or show the whole change log<br>
</li><li>display on first start of newly installed app or on new app version<br>
</li><li>write the change log in a simplified language but also use HTML and CSS if needed<br>
</li><li>Android SDK 1.5 and higher</li></ul>

Test it! <a href='http://code.google.com/p/android-change-log/downloads/list'>Download the Test App here!</a> - it's free and it's open source!<br>
<br>
<hr />

<wiki:gadget url="https://gcodeadsense.googlecode.com/svn/release/gCodeAdSense/gCodeAdSense.xml" border="0" width="728" height="90" up_ad_client="6957634847281469" up_ad_slot="9150575031" up_ad_width="728" up_ad_height="90" /><br>
<br>
<hr />

There are several projects using this code:<br>
<ul><li><a href='https://play.google.com/store/apps/details?id=at.zweng.bankomatinfos'>Bankomat Card Infos</a>
</li><li><a href='https://play.google.com/store/apps/details?id=com.nebelcode.synchrolab'>Synchrolab</a>
</li><li><a href='http://code.google.com/p/smsoip/'>SMSoiP</a>
</li><li><a href='http://androidsu.com/'>AndroidSU</a>
</li><li><a href='https://play.google.com/store/apps/details?id=org.cyclopath.android'>Cyclopath</a>
</li><li><a href='http://forum.xda-developers.com/showthread.php?t=1034679'>Bootanimation Utility</a>
</li><li><a href='https://play.google.com/store/apps/details?id=com.visualdesign.livefootballontv'>Live Football On TV</a>
</li><li>and more...</li></ul>

And there even is a <a href='https://github.com/cketti/ckChangeLog'>spin-off</a> under an Apache-License.<br>
<br>
<hr />

<h2>Setting up your Change Log</h2>

<h3>Get code</h3>

Copy the class <a href='https://code.google.com/p/android-change-log/source/browse/trunk/src/sheetrock/panda/changelog/ChangeLog.java'>ChangeLog.java</a> into your project (<a href='https://android-change-log.googlecode.com/svn/trunk/src/sheetrock/panda/changelog/ChangeLog.java'>raw file here</a>).<br>
<br>
<code>ChangeLog.java</code> is distributed with a simple license. This basically allows you to do whatever you want with it in open, closed, noncommercial and commercial projects as long as you leave the copyright and permission notice intact in your source code.<br>
<br>
<h3>Maintain versionName in AndroidManifest.xml</h3>

<pre><code>&lt;manifest xmlns:android="http://schemas.android.com/apk/res/android"<br>
      package="sheetrock.panda.changelog"<br>
          :        :        :<br>
      android:versionName="2.0.7"&gt;<br>
</code></pre>

<ul><li><code>versionName</code>: This is the current version name of your app. This does not have to be a number but normally it is. Examples: "2.0.7", "0.1-beta", "1.1.0.14", "Jean-Luc Version"</li></ul>

<h3>Add strings</h3>

Add these string constants to your <code>res/values/strings.xml</code>:<br>
<br>
<pre><code>    &lt;string name="changelog_full_title"&gt;Change Log&lt;/string&gt;<br>
    &lt;string name="changelog_title"&gt;What\'s New&lt;/string&gt;<br>
    &lt;string name="changelog_ok_button"&gt;OK&lt;/string&gt;<br>
    &lt;string name="changelog_show_full"&gt;more...&lt;/string&gt;<br>
</code></pre>

<ul><li><code>changelog_full_title</code>: This is the title for an <b>Android Change Log</b> dialog that displays the whole change log.<br>
</li><li><code>changelog_title</code>: This is the title for an <b>Android Change Log</b> dialog that only displays the things having changed since the last installed version.<br>
</li><li><code>changelog_ok_button</code>: This is the text on the button to dismiss the <b>Android Change Log</b> dialog.<br>
</li><li><code>changelog_show_full</code>: This text is shown on the button to display the full change log in the show-only-what's-new-mode.<br>
<h3>Write log</h3></li></ul>

Add a file <code>res/raw/changelog.txt</code> to your project and fill it with your change log.<br>
<br>
<table cellpadding='0' border='0' cellspacing='0'>
<tr><td><b>Example code:</b></td><td width='20'></td><td><b>Result:</b></td></tr>
<tr><td>
<pre><code>&lt;html&gt;<br>
  &lt;head&gt;<br>
    &lt;style type='text/css'&gt;<br>
      a            { color:#a0a0e0 }<br>
      div.title    { <br>
          color:#C0F0C0; <br>
          font-size:1.2em; <br>
          font-weight:bold; <br>
          margin-top:1em; <br>
          margin-bottom:0.5em; <br>
          text-align:center }<br>
      div.subtitle { <br>
          color:#C0F0C0; <br>
          font-size:0.8em; <br>
          margin-bottom:1em; <br>
          text-align:center }<br>
      div.freetext { color:#F0F0F0 }<br>
      div.list     { color:#C0C0F0 }<br>
    &lt;/style&gt;<br>
  &lt;/head&gt;<br>
  &lt;body&gt;<br>
$ 2.1.1<br>
  % Version 2.1.1 (Neelix)<br>
  _ 2011-09-15<br>
    * now works with strange symbols: ÆæØøÅå Smørebrød<br>
    * and even stranger symbols, too: 將賦予他們的傳教工作標示為<br>
$ 2.0.8<br>
  % Version 2.0.8 (Kirk)<br>
  _ 2011-07-12<br>
    * &lt;span style="font-family:monospace"&gt;cl.isFirstRunEver()&lt;/span&gt; added<br>
    * getting version name from manifest now<br>
$ 2.0<br>
  % Version 2.0<br>
  _ 2011-06-19<br>
  ! completely overdone and cooler than ever!<br>
    # &lt;a href="http://www.friendface.co.uk/"&gt;Friendface&lt;/a&gt; integration<br>
    # now with Change Log<br>
    # Billions of bug fixes!<br>
$ 1.0<br>
  % Version 1.0<br>
  _ 2011-06-01<br>
  ! Finally! &lt;b&gt;Rocking Chair Sound Generator&lt;/b&gt; released!<br>
    * please register<br>
    * please donate<br>
$ 0.1<br>
  % Version 0.1<br>
  _ (beta, not working)<br>
$ END_OF_CHANGE_LOG<br>
  &lt;/body&gt;<br>
&lt;/html&gt;<br>
</code></pre>
</td><td><code> </code></td><td valign='top'>
<br />
<img src='http://android-change-log.googlecode.com/svn/trunk/doc/shot1.jpg' />
</td></tr></table>

<hr />

Abstract explanation:<br>
<ul><li>First input HTML as if writing a web page until the body-Tag. Here you can enter CSS for div.title, div.subtitle, div.freetext and div.list (see below) and any other elements you may be using, e.g. links.<br>
</li><li>To begin a section for any version start the line with a $ sign followed by the version. This line <b>will not be displayed</b> in the dialog but it is important for <b>Android Change Log</b> for knowing what to display and what not. It corresponds to the <code>versionName</code> in the <code>AndroidManifest.xml</code>.<br>
</li><li>% begins a line of a version section title.<br>
</li><li><code>_</code> begins a line of a version section subtitle.<br>
</li><li>! begins a line of free text.<br>
</li><li># begins a line within a numbered list.<br>
</li><li><code>*</code> begins a line within a bullet list.<br>
</li><li>Lines beginning without any of these signs (for example the HTML at the beginning of <code>changelog.txt</code>) will be used as they are.<br>
</li><li>You can use HTML anywhere you want.<br>
</li><li>Insert a line containing "$ END_OF_CHANGE_LOG" after the last version section.<br>
</li><li>After that you should enter HTML again, at least the end-body-tag and the end-html-tag.<br>
</li><li>You can indent lines, but you don't have to.<br>
</li><li>You don't need to use these special symbols, you can write your log completely in HTML. Only the lines beginning with $-signs are mandatory if you want to be able to only display what's new instead of a full change log.</li></ul>

<b>It would be nice if you included a line like this in your change log:</b>

<ul><li>Now with <code>&lt;a href="http://code.google.com/p/android-change-log/"&gt;</code>Android Change Log<code>&lt;/a&gt;</code>!</li></ul>

<h3>Integrate</h3>

Put this in your Activity to display what's new since the last installed version of your app on that particular device:<br>
<br>
<pre><code>    ChangeLog cl = new ChangeLog(this);<br>
    if (cl.firstRun())<br>
        cl.getLogDialog().show();<br>
</code></pre>

After the OK-Button in the change log dialog is pressed, the new version number is written to the <code>SharedPrefences</code>. This means that the next instantiation of this class will return <code>false</code> when calling <code>firstRun()</code> and <code>getLogDialog()</code> would deliver an empty change log dialog.<br>
<br>
Use this to display the full change log, e.g. when a menu entry named "change log" was chosen:<br>
<br>
<pre><code>    cl.getFullLogDialog().show();<br>
</code></pre>

If you want to build your own dialog, you can retrieve the HTML by calling <code>getLog()</code> or <code>getFullLog()</code>.<br>
<br>
<h3>Localization</h3>

You can provide change logs in different languages. For example use <code>res/raw-de/changelog.txt</code> and <code>res/values-de/strings.xml</code> to provide German logs and labels.<br>
<br>
<h3>Stay up-to-date</h3>

Register for code changes or other page updates <a href='http://code.google.com/p/android-change-log/feeds'>here</a>.<br>
<br>
<h3>Troubleshooting</h3>
<ul><li>When you want to display a percentage sign in the change log, please use %25 instead of only %. This is due to limitations in the use of a <code>WebView</code> in the dialog.<br>
</li><li>Check <a href='http://code.google.com/p/android-change-log/issues/detail?id=6'>issue 6</a> when having problems with special or language specific characters. Basically it is a Windows vs Android problem. You can try to change the file encoding in Eclipse to UTF-8 or use the windows notepad and use "save as ..." to change the encoding.<br>
</li><li>To test the "What's new" Dialog you can use the method <code>dontuseSetLastVersion()</code> to set the Version by hand. Don't forget to kick this call out of your code before you build a release!!<br>
</li><li>Feel free to use the <a href='http://code.google.com/p/android-change-log/issues/list'>Issues tab</a> on this page to report any defects.</li></ul>

<hr />

<h2>to do - what's next to come</h2>

<ul><li>Check the <a href='http://code.google.com/p/android-change-log/issues/list'>Issues tab</a> for accepted enhancements<br>
</li><li>If you made enhancements yourself, please provide any information - to share with other users.</li></ul>

<hr />

<wiki:gadget url="https://gcodeadsense.googlecode.com/svn/release/gCodeAdSense/gCodeAdSense.xml" border="0" width="728" height="90" up_ad_client="6957634847281469" up_ad_slot="5568849836" up_ad_width="728" up_ad_height="90" /><br>
<br>
</td>
<td valign='top'>
<wiki:gadget url="https://gcodeadsense.googlecode.com/svn/release/gCodeAdSense/gCodeAdSense.xml" border="0" width="300" height="600" up_ad_client="6957634847281469" up_ad_slot="4529187834" up_ad_width="300" up_ad_height="600" /><br>
<br />
<br />
<wiki:gadget url="https://gcodeadsense.googlecode.com/svn/release/gCodeAdSense/gCodeAdSense.xml" border="0" width="200" height="90" up_ad_client="6957634847281469" up_ad_slot="2853978239" up_ad_width="200" up_ad_height="90" /><br>
<br>
</td></tr></table>