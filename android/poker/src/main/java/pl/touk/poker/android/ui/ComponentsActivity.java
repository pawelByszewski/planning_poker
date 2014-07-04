package pl.touk.poker.android.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
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

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragmentPlaceholder, new ComponentsFragment(), "blur").commit();
        }

//        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
//
//        actionBarDrawerToggle = new ActionBarDrawerToggle(
//                this,                  /* host Activity */
//                drawerLayout,         /* DrawerLayout object */
//                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
//                R.string.drawer_open,  /* "open drawer" description */
//                R.string.drawer_close  /* "close drawer" description */
//        ) {
//
//            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(ComponentsActivity.this.getTitle());
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(ComponentsActivity.this.getTitle());
//            }
//        };
//
//        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
//        actionBarDrawerToggle.syncState();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        return false;
    }
}
