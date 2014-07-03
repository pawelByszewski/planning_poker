package pl.touk.poker.android.content;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesAdapter {

    private final SharedPreferences mSharedPreferences;

    @Inject
    public SharedPreferencesAdapter(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void persistenceAdd(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    public void persistenceAdd(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
    }

    public int get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public static SharedPreferencesAdapter create(SharedPreferences sharedPreferences) {
        return new SharedPreferencesAdapter(sharedPreferences);
    }
}
