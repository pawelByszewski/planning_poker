package pl.touk.poker.android.ui;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.touk.poker.android.R;
import pl.touk.poker.android.config.dagger.Injector;
import pl.touk.poker.android.network.Request;
import pl.touk.poker.android.network.Response;
import pl.touk.poker.android.network.ServerApi;
import pl.touk.poker.android.network.ServerClient;
import retrofit.Callback;
import retrofit.RetrofitError;

import javax.inject.Inject;

public class ComponentsActivity extends Activity {

//    @InjectView(R.id.drawer_layout)
//    public DrawerLayout drawerLayout;

//    ActionBarDrawerToggle actionBarDrawerToggle;
    @Inject ServerClient client;

    @InjectView(R.id.sessionIdInput)
    EditText sessionInput;

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
        Injector.inject(this);
    }

    public void onJoinSession(View view) {
        Request request = new Request();
        request.setName("Adam");
        //ServerClient.getApi().joinSession(request, sessionInput.getText().toString(), new SessionJoined());
//        showAlert();
        client.getApi().joinSession(request, sessionInput.getText().toString(), new SessionJoined());
    }

    public void showAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Ugabuga!")
                .setMessage(sessionInput.getText().toString())
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

    private class SessionJoined implements Callback<Response> {

        @Override
        public void success(Response response, retrofit.client.Response response2) {
            Toast.makeText(ComponentsActivity.this, "Success", Toast.LENGTH_LONG).show();
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Toast.makeText(ComponentsActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

}
