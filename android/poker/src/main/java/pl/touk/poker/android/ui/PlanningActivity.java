package pl.touk.poker.android.ui;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import pl.touk.poker.android.R;

public class PlanningActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning);
        ButterKnife.inject(this);
    }

}
