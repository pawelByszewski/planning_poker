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
import com.google.zxing.client.android.CaptureActivity;
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

    @OnClick(R.id.joinButton)
    public void onJoinSession() {
        Request request = new Request();
        request.setName(usernameInput.getText().toString());
//        client.getApi().joinSession(request, sessionInput.getText().toString(), new SessionJoined());
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 123);
    }

    private class SessionJoined implements Callback<Response> {
        @Override
        public void success(Response response, retrofit.client.Response response2) {
            Intent intent = new Intent(ComponentsActivity.this, PlanningActivity.class);
            intent.putExtra("userId", response.getUserId());
            intent.putExtra("sessionId", response.getSessionId());
            startActivity(intent);
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Toast.makeText(ComponentsActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == 123) {
            // handle scan result
        }
        // else continue with any other code you need in the method
    }
}
