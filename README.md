 
![fog](https://stash.office/projects/MAS/repos/android-blur/browse/example/res/drawable/icon.png?at=2e95cffd43e15feb9651bc9587507e56394b850a&raw "fog")  
Fogger
====================

Lib to create blurred background for dialogs, drawer etc.

Issues
--------------------
If you find any bug please create a issue at [JIRA Board](https://jira.office/secure/RapidBoard.jspa?rapidView=1470&view=detail).

Features
--------------------
You can blur view behind drawer, dialog window and context menu.

![example](https://stash.office/projects/MAS/repos/android-blur/browse/readme/fog-example.gif?at=859b9358da5b852327a392940486d9842d361f75&raw "Example")

Examples
===================
Drawer
-------------------
To make drawer with fluent, blurred you have to provide to changes to standard Android drawer. 
At the first the root view must be ```pl.allegro.fogger.ui.drawer.DrawerLayoutWithBlurredBackground``` instead
of ```DrawerLayout```

The second modification is to add tag value 

```xml
 android:tag="fragmentPlaceholder"
``` 
 to main content layout. You could also use string resource id provided with apk
 ```xml
  android:tag="@string/view_with_drawer_tag"
  ```
   
The full example:
   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   
   <pl.allegro.fogger.ui.drawer.DrawerLayoutWithBlurredBackground
           xmlns:android="http://schemas.android.com/apk/res/android"
           android:id="@+id/drawer_layout"
           android:layout_width="match_parent"
           android:layout_height="match_parent">
           
       <FrameLayout
           android:id="@+id/fragmentPlaceholder"
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:tag="@string/view_with_drawer_tag"/>
   
       <FrameLayout
               android:id="@+id/drawer_container"
               android:tag="@string/drawer_content_view_tag"
               android:layout_width="240dp"
               android:layout_height="match_parent"
               android:layout_gravity="start">
               <include layout="@layout/settings"/>
       </FrameLayout>
       
   </pl.allegro.fogger.ui.drawer.DrawerLayoutWithBlurredBackground>
   ```
Working example you could find in the  [example app](https://jira.office/secure/RapidBoard.jspa?rapidView=1470&view=detail)

Road Map
--------------------
Blurring background under context menu should be available soon.
