package pl.touk.poker.android.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import pl.touk.poker.android.R;
import pl.touk.poker.android.config.dagger.Injector;

public class ComponentsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Injector.inject(this);
        View viewRoot = inflater.inflate(R.layout.components_fragment, container, false);
        ButterKnife.inject(this, viewRoot);
        return viewRoot;
    }


    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }
}
