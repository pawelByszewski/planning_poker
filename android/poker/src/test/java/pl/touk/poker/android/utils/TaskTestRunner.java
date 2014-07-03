package pl.touk.poker.android.utils;

public class TaskTestRunner implements TaskRunner {
    @Override
    public void execute(SafeAsyncTask task) {
        try {
            task.onPreExecute();
            Object result = task.call();
            task.onSuccess(result);
        } catch (Exception e) {
            task.onException(e);
        } catch (Throwable t) {
            task.onThrowable(t);
        } finally {
            task.onFinally();
        }
    }
}
