package pl.touk.poker.android.ui;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import pl.touk.poker.android.R;
import pl.touk.poker.android.config.dagger.Injector;

public class PlanningActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
        setContentView(R.layout.planning);
        ButterKnife.inject(this);
    }

}
