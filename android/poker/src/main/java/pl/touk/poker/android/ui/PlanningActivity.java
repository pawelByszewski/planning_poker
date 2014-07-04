package pl.touk.poker.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.touk.poker.android.R;
import pl.touk.poker.android.config.dagger.Injector;
import pl.touk.poker.android.network.EmptyResponse;
import pl.touk.poker.android.network.EstimateRequest;
import pl.touk.poker.android.network.ServerClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import javax.inject.Inject;

public class PlanningActivity extends Activity {
    @Inject
    ServerClient client;

    @InjectView(R.id.sessionId)
    TextView sessionIdView;

    @InjectView(R.id.estimateSpinner)
    Spinner estimateSpinner;

    String userId;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCredentials();
        Injector.inject(this);
        setContentView(R.layout.planning);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionIdView.setText(sessionId);
        String[] estimates = {"1", "2", "3", "5", "8"};
        estimateSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, estimates));
    }

    @OnClick(R.id.sendButton)
    public void onSend(View view) {
//        EstimateRequest req = new EstimateRequest();
//        req.setUserId(userId);
//        req.setEstimate(estimateSpinner.getSelectedItem().toString());
//        client.getApi().estimate(req, sessionId, new ToastResponse());
        client.getApi().estimate(userId, estimateSpinner.getSelectedItem().toString() , sessionId, new ToastResponse());
    }

    private void initCredentials() {
        this.userId = getIntent().getStringExtra("userId");
        this.sessionId = getIntent().getStringExtra("sessionId");
    }

    private class ToastResponse implements Callback<EmptyResponse> {
        @Override
        public void success(EmptyResponse emptyResponse, Response response) {
            toast("Success");
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            toast("Failure");
        }

        protected void toast(String message) {
            Toast.makeText(PlanningActivity.this, message, Toast.LENGTH_LONG).show();
        }
    }

}
