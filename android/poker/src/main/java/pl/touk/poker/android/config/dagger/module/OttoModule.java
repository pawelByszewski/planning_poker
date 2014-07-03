package pl.touk.poker.android.config.dagger.module;

import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import pl.touk.poker.android.ui.ComponentsActivity;
import pl.touk.poker.android.ui.ComponentsFragment;

import javax.inject.Singleton;

@Module(
        injects = {
                ComponentsActivity.class,
                ComponentsFragment.class
        },
        library = true,
        complete = false
)
public class OttoModule {

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }
}
