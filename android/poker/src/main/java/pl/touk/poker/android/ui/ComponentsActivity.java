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
import butterknife.OnClick;
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

    @Inject ServerClient client;

    @InjectView(R.id.sessionIdInput)
    EditText sessionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.components_view);
        ButterKnife.inject(this);
        Injector.inject(this);
    }

    public void onJoinSession(View view) {
//        Request request = new Request();
//        request.setName(usernameInput.getText().toString());
//        client.getApi().joinSession(request, sessionInput.getText().toString(), new SessionJoined());
        client.getApi().joinSession(usernameInput.getText().toString(), sessionInput.getText().toString(), new SessionJoined());
    }

    @OnClick(R.id.scan)
    public void scan() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
//                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast.makeText(ComponentsActivity.this, "session set to " + contents, Toast.LENGTH_LONG).show();
                sessionInput.getText().clear();
                sessionInput.getText().append(contents);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(ComponentsActivity.this, "unusable code", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class SessionJoined implements Callback<Response> {
        @Override
        public void success(Response response, retrofit.client.Response response2) {
            Intent intent = new Intent(ComponentsActivity.this, PlanningActivity.class);
            intent.putExtra("userId", response.getUserId());
            intent.putExtra("sessionId", sessionInput.getText().toString());
            startActivity(intent);
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Toast.makeText(ComponentsActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

}
