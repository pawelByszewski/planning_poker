package pl.touk.poker.android.config.dagger;

import dagger.ObjectGraph;

public final class Injector {
    public static ObjectGraph sObjectGraph = null;
    private static volatile boolean sInitialized = false;


    public static synchronized void init(Object... rootModules) {
        sObjectGraph = sObjectGraph == null ? ObjectGraph.create(rootModules) : sObjectGraph.plus(rootModules);
    }

    public static void injectStatics() {
        sObjectGraph.injectStatics();
    }

    public static void inject(final Object target) {
        sObjectGraph.inject(target);
    }

    public static <T> T resolve(Class<T> type) {
        return sObjectGraph.get(type);
    }

    public static void add(Object... object) {
        sObjectGraph = ObjectGraph.create(object);
    }
}
