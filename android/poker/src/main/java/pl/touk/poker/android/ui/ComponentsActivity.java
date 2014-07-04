package pl.touk.poker.android.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
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


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
