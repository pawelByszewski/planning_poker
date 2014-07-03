package pl.touk.poker.android.config.application;


import android.content.SharedPreferences;
import pl.touk.poker.android.content.SharedPreferencesAdapter;

import javax.inject.Inject;

public class ApplicationRunConfiguration {

    private final SharedPreferencesAdapter mSharedPreferencesAdapter;

    @Inject
    public ApplicationRunConfiguration(SharedPreferencesAdapter sharedPreferencesAdapter) {
        mSharedPreferencesAdapter = sharedPreferencesAdapter;
    }

    public static ApplicationRunConfiguration create(SharedPreferences sharedPreferences) {
        SharedPreferencesAdapter sharedPreferencesAdapter = SharedPreferencesAdapter.create(sharedPreferences);
        ApplicationRunConfiguration applicationRunConfiguration = new ApplicationRunConfiguration(sharedPreferencesAdapter);
        return applicationRunConfiguration;
    }


}
