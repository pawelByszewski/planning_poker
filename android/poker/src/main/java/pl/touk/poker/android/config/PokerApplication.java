package pl.touk.poker.android.config;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import pl.touk.poker.android.config.application.ApplicationRunConfiguration;
import pl.touk.poker.android.config.dagger.Injector;
import pl.touk.poker.android.config.dagger.module.RootModule;

public class PokerApplication extends Application {

    private static PokerApplication sInstance;

    private ApplicationRunConfiguration mApplicationRunConfiguration;

    public PokerApplication() {
    }

    public PokerApplication(final Context context) {
        super();
        attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        initDaggerOnApplicationCreationStep();
        Injector.inject(this);
    }

    private void initDaggerOnApplicationCreationStep() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mApplicationRunConfiguration = ApplicationRunConfiguration.create(sharedPreferences);

        Object[] modules = new Object[]{new RootModule()};
        Injector.init(modules);
        Injector.injectStatics();
    }

    private static void setInstance(PokerApplication pokerApplication) {
        sInstance = pokerApplication;
    }

    public PokerApplication(final Instrumentation instrumentation) {
        super();
        attachBaseContext(instrumentation.getTargetContext());
    }

    public static PokerApplication getInstance() {
        return sInstance;
    }

}
