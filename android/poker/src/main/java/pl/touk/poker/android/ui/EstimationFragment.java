package pl.touk.poker.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class EstimationFragment extends Fragment {
    String estimate;
    String userId;
    String sessionId;

    @InjectView(R.id.estimateValue)
    TextView estimateWidget;

    @Inject
    ServerClient client;

    public EstimationFragment(String estimate, String userId, String sessionId) {
        this.estimate = estimate;
        this.userId = userId;
        this.sessionId = sessionId;
        Injector.inject(this);
        ButterKnife.inject(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.estimate, container, false);
    }

    @OnClick(R.id.sendButton)
    public void onSend(View view) {
        EstimateRequest req = new EstimateRequest();
        req.setUserId(userId);
        req.setEstimate(estimate);
        client.getApi().estimate(req, sessionId, new ToastResponse());
    }

    @Override
    public void onResume() {
        estimateWidget.setText(estimate);
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
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }
}
