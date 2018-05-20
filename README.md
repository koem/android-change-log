# Overview

![changelog example](https://raw.githubusercontent.com/koem/android-change-log/master/project/shot2.jpg)
changelog example

Using **Android Change Log** you can easily create, show and maintain an Android changelog dialog to display the recent changes / release notes of your Android App on the user’s Android device (tablet / smartphone / whatever).

Features are:


* It’s **open source**, **no license attached**, and **free of charge**!
* display only what’s new or show the whole change log
* display on first start of newly installed app or on new app version
* write the change log in a simplified language but also use HTML and CSS if needed
* just one .java file to add to your project
* Android SDK 1.5 and higher

Test it! [Download the Test App](https://raw.githubusercontent.com/koem/android-change-log/master/apk/Android_Change_Log_2.3.apk) here! – it’s free and it’s open source!

There are several projects using this code:

* Bankomat Card Infos
* Synchrolab
* SMSoiP
* AndroidSU
* Cyclopath
* Bootanimation Utility
* Live Football On TV
* and more …

And there even is a [spin-off](https://github.com/cketti/ckChangeLog) (Apache-License).

# Setting up your Change Log
## Get Code

Copy the class ChangeLog.java into your project ([raw file here](https://github.com/koem/android-change-log/raw/master/src/sheetrock/panda/changelog/ChangeLog.java)).

ChangeLog.java is distributed with a **simple license**. This basically allows you to do whatever you want with it in open, closed, noncommercial and commercial projects as long as you leave the copyright and permission notice intact in your source code.

## Maintain versionName in `AndroidManifest.xml`


`AndroidManifest.xml`:

```<manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="sheetrock.panda.changelog"
          :        :        :
      android:versionName="2.3">
```

* `versionName`: This is the current version name of your app. This does not have to be a number but normally it is. Examples: “2.0.7”, “0.1-beta”, “1.1.0.14”, “Jean-Luc Version”

## Add strings

Add these string constants to your res/values/strings.xml:

```<string name="changelog_full_title">Change Log</string>
<string name="changelog_title">What\'s New</string>
<string name="changelog_ok_button">OK</string>
<string name="changelog_show_full">more...</string>
<string name="background_color">black</string>
``` 

* `changelog_full_title`: This is the title for an **Android Change Log** dialog that displays the whole change log.
* `changelog_title`: This is the title for an **Android Change Log** dialog that only displays the things having changed since the last installed version.
* `changelog_ok_button`: This is the text on the button to dismiss the **Android Change Log** dialog.
* `changelog_show_full`: This text is shown on the button to display the full change log in the show-only-what’s-new-mode.
* `background_color`: The background color of the dialog. You can use color names as defined [here](http://developer.android.com/reference/android/graphics/Color.html#parseColor%28java.lang.String%29) or hex codes like `#002060`.

## Write log

Add a file `res/raw/changelog.txt` to your project and fill it with your change log.

To get a result like this …

![changelog example](https://raw.githubusercontent.com/koem/android-change-log/master/project/shot2.jpg)

… do something like this:

`changelog.txt`:

```<html>
  <head>
    <style type='text/css'>
      a            { color:#a0a0e0 }
      div.title    {
        color:#C0F0C0;
        font-size:1.2em;
        font-weight:bold;
        margin-top:1em;
        margin-bottom:0.5em;
        text-align:center }
      div.subtitle {
        color:#C0F0C0;
        font-size:0.8em;
        margin-bottom:1em;
        text-align:center }
      div.freetext { color:#F0F0F0 }
      div.list     { color:#C0C0F0 }
    </style>
  </head>
  <body>
    $ 2.1.1
      % Version 2.1.1 (Neelix)
      _ 2011-09-15
      * now works with strange symbols: ÆæØøÅå Smørebrød
      * and even stranger symbols, too: 將賦予他們的傳教工作標示為
    $ 2.0.8
      % Version 2.0.8 (Kirk)
      _ 2011-07-12
      * <span style="font-family:monospace">cl.isFirstRunEver()</span> added
      * getting version name from manifest now
    $ 2.0
      % Version 2.0
      _ 2011-06-19
      ! completely overdone and cooler than ever!
      # <a href="http://www.friendface.co.uk/">Friendface</a> integration
      # now with Change Log
      # Billions of bug fixes!
    $ 1.0
      % Version 1.0
      _ 2011-06-01
      ! Finally! <b>Rocking Chair Sound Generator</b> released!
      * please register
      * please donate
    $ 0.1
      % Version 0.1
      _ (beta, not working)
    $ END_OF_CHANGE_LOG
  </body>
</html>
```

Abstract explanation:

* First input HTML as if writing a web page until the body-Tag. Here you can enter CSS for *div.title*, *div.subtitle*, *div.freetext* and *div.list* (see below) and any other elements you may be using, e.g. links.
* To begin a section for any version start the line with a $ sign followed by the version. **This line will not be displayed** in the dialog but it is important for **Android Change Log** for knowing what to display and what not. It corresponds to the *versionName* in the *AndroidManifest.xml*.
* `%` starts a line of a version section title.
* `_` starts a line of a version section subtitle.
* `!` starts a line of free text.
* `#` starts a line within a numbered list.
* `*` starts a line within a bullet list.
* Lines starting without any of these signs (for example the HTML at the beginning of changelog.txt) will be used as they are.
* You can use HTML anywhere you want.
* Insert a line containing `$ END_OF_CHANGE_LOG` after the last version section.
* After that you should enter HTML again, at least the end-body-tag and the end-html-tag.
* You can indent lines, but you don’t have to.
* You don’t need to use these special symbols, you can write your log completely in HTML. Only the lines beginning with `$`-signs are mandatory if you want to be able to only display what’s new instead of a full change log.

**It would be nice if you included a line like this in your change log:**

```
* Now with <a href="https://github.com/koem/android-change-log/">Android Change Log</a>!
```

