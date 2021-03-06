 
![fog](https://stash.office/projects/MAS/repos/android-blur/browse/example/res/drawable/icon.png?at=2e95cffd43e15feb9651bc9587507e56394b850a&raw "fog")  
Fogger
====================

Lib to create blurred background under:
* dialogs
* drawer
* context menu

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
Working example of drawer with dynamicly blurred bacground you could find in the [example app](https://stash.office/projects/MAS/repos/android-blur/browse/example)

Dialog
-------------------

To create dialog window with blurred background you can not just invoke dialog window. A the first you must prepare ```Activity``` that extends 
```java
public abstract class DialogWithBlurBackgroundContainer extends Activity
``` 
and implement abstract method ```java protected Dialog createDialog()``` that MUST return the dialog window you want to show to user. 
Specialy dialog launcher must be used. 
```java
public class DialogWithBlurredBackgroundLauncher {

 ...
 
 public synchronized void launchForResult(Activity activity, Intent intent, int requestCode) {
 ...
 }
```
Lets assume you prepared already pointing specialy "Dialog Activity" named ```ExampleDialog```, then you could show dialog form any Activiti
```java
 Intent intent = new Intent(context, ExampleDialog.class);
 DialogWithBlurredBackgroundLauncher dialogWithBlurredBackgroundLauncher = new DialogWithBlurredBackgroundLauncher();
 //'this' pointing to any Activity instance
 int requestCode = 1;
 dialogWithBlurredBackgroundLauncher.launchForResult(this, intent, requestCode);
```
The dialog result could be handled in method already definde Activity method
```java
protected void onActivityResult (int requestCode, int resultCode, Intent data)
```
The source dialog for received result is determined by requestCode that must be passed to ```dialogWithBlurredBackgroundLauncher.launchForResult(...)```

Working example of dialog window with blurred background you could find in the [example app](https://stash.office/projects/MAS/repos/android-blur/browse/example)

Context Menu
-------------------
To create context window with blurred background you must prepare ```Activity``` that extends 
```java
public abstract class ActivityWithContextMenu extends Activity {
``` 

then you must implements one required abstract method
```java
    protected abstract int getContextMenuResId(View view);
```

The method must provide resource id form context menu to show eg.
```java
    @Override
    protected int getContextMenuResId(View view) {
        return R.menu.context_menu;
    }
```
The rest of context menu flow is unchanged, so you have to register context menu on some View and listen click event with Androids method
```java
public boolean onContextItemSelected(MenuItem item) {
```


Road Map
===================
At the moment I have no plan for further features, if you have any idea please let me know.
