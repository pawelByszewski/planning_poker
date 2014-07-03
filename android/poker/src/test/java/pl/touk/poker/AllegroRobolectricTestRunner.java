package pl.touk.poker;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.res.FsFile;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class AllegroRobolectricTestRunner extends RobolectricTestRunner {

    private static final String MAVEN_APKLIBS_DIR_PATH = "target/unpack/apklibs";

    public AllegroRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest createAppManifest(FsFile manifestFile, FsFile resDir, FsFile assetsDir) {
        return new MavenAndroidManifest(manifestFile, resDir, assetsDir);
    }

    public static class MavenAndroidManifest extends AndroidManifest {

        public MavenAndroidManifest(FsFile baseDir) {
            super(baseDir);
        }

        public MavenAndroidManifest(FsFile manifestFile, FsFile resDir, FsFile assetsDir) {
            super(manifestFile, resDir, assetsDir);
        }

        @Override
        protected List<FsFile> findLibraries() {
            FsFile unpack = getBaseDir().join(MAVEN_APKLIBS_DIR_PATH);
            if (unpack.exists()) {
                FsFile[] libs = unpack.listFiles();
                if (libs != null) {
                    return asList(libs);
                }
            }
            return emptyList();
        }

        @Override
        protected AndroidManifest createLibraryAndroidManifest(FsFile libraryBaseDir) {
            return new MavenAndroidManifest(libraryBaseDir);
        }
    }
}
