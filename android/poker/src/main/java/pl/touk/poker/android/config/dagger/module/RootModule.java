package pl.touk.poker.android.config.dagger.module;

import dagger.Module;
import dagger.Provides;
import pl.touk.poker.android.config.PokerApplication;
import pl.touk.poker.android.config.application.ApplicationRunConfiguration;
import pl.touk.poker.android.network.ServerClient;
import pl.touk.poker.android.ui.ComponentsActivity;
import pl.touk.poker.android.ui.PlanningActivity;
import pl.touk.poker.android.utils.Ln;
import pl.touk.poker.android.utils.TaskRunner;
import pl.touk.poker.android.utils.TaskRunnerImpl;

@Module(
    includes = {
        AndroidModule.class,
        OttoModule.class
    },
    injects = {
            PokerApplication.class,
            TaskRunner.class,
            ApplicationRunConfiguration.class,
            RootModule.class,
            ComponentsActivity.class,
            ServerClient.class,
            PlanningActivity.class
    },
    staticInjections = {
            Ln.class,
    },
    library = true,
    complete = false
)
public class RootModule {

    private ServerClient serverClient;

    @Provides
    TaskRunner provideTaskRunner() {
        return new TaskRunnerImpl();
    }

    @Provides
    ServerClient provideServerClient() {
        if(serverClient == null) serverClient = new ServerClient();
        return serverClient;
    }

}