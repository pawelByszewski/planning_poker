package pl.touk.poker.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import pl.touk.poker.android.R;

import java.util.List;
import java.util.Vector;

public class PlanningActivitySwipeEdition extends FragmentActivity {
    private PagerAdapter pagerAdapter;

    String userId;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.planning_viewer);
        initCredentials();
        this.initialisePaging();
    }

    private void initCredentials() {
        this.userId = getIntent().getStringExtra("userId");
        this.sessionId = getIntent().getStringExtra("sessionId");
    }

    private void initialisePaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(new EstimationFragment("1", userId, sessionId));
        fragments.add(new EstimationFragment("2", userId, sessionId));
        fragments.add(new EstimationFragment("3", userId, sessionId));
        fragments.add(new EstimationFragment("5", userId, sessionId));
        fragments.add(new EstimationFragment("8", userId, sessionId));
        this.pagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.pagerAdapter);
    }
}
