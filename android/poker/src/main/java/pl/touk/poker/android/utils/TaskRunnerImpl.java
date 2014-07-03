package pl.touk.poker.android.utils;


public class TaskRunnerImpl implements TaskRunner {
    @Override
    public void execute(SafeAsyncTask task) {
        task.execute();
    }
}
