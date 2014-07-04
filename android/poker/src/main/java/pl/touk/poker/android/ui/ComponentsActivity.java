package pl.touk.poker.android.ui;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.touk.poker.android.R;

public class ComponentsActivity extends Activity {

//    @InjectView(R.id.drawer_layout)
//    public DrawerLayout drawerLayout;

//    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.components_view);
//        Injector.inject(this);
        ButterKnife.inject(this);
    }

    public void onJoinSession(View view) {
        showAlert();
    }

    public void showAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Ugabuga!")
                .setMessage("onClock works!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
