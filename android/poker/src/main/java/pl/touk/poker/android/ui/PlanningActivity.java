package pl.touk.poker.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.touk.poker.android.R;
import pl.touk.poker.android.config.dagger.Injector;
import pl.touk.poker.android.network.ServerClient;

import javax.inject.Inject;

public class PlanningActivity extends Activity {
    @Inject
    ServerClient client;

    @InjectView(R.id.sessionId)
    TextView sessionIdView;

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
    }

    private void initCredentials() {
        this.userId = getIntent().getStringExtra("userId");
        this.sessionId = getIntent().getStringExtra("sessionId");
    }

}
