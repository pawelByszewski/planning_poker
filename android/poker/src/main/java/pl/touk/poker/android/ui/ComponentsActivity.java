package pl.touk.poker.android.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.touk.poker.android.R;
import pl.touk.poker.android.config.dagger.Injector;
import pl.touk.poker.android.network.Request;
import pl.touk.poker.android.network.Response;
import pl.touk.poker.android.network.ServerClient;
import retrofit.Callback;
import retrofit.RetrofitError;

import javax.inject.Inject;

public class ComponentsActivity extends Activity {

    @InjectView(R.id.usernameInput)
    EditText usernameInput;

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
        ButterKnife.inject(this);
        Injector.inject(this);
    }

    public void onJoinSession(View view) {
        Request request = new Request();
        request.setName(usernameInput.getText().toString());
        client.getApi().joinSession(request, sessionInput.getText().toString(), new SessionJoined());
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
            Intent intent = new Intent(ComponentsActivity.this, PlanningActivity.class);
            startActivity(intent);
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Toast.makeText(ComponentsActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

}
