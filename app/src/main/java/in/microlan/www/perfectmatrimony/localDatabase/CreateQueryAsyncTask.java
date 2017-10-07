package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class CreateQueryAsyncTask extends AsyncTask<Object, Void, Long> {

    private Context context;
    private DatabaseManager databaseManager;
    private IDBCallback queryCallback;
    private int responseType = 0;
    private Object extraParam;

    public CreateQueryAsyncTask(Context _context, IDBCallback _queryCallback) {
        this.context = _context;
        databaseManager = DatabaseManager.getDatabaseManager(_context);
        queryCallback = _queryCallback;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Long doInBackground(Object... params) {

        long insertRowValue = 0;
        databaseManager.WriteOnDatabase();
        try {

            responseType = (int) params[2];
            databaseManager.createBulkOps(params);

        } catch (Exception e) {
            String TAG = CreateQueryAsyncTask.class.getSimpleName();
            Log.d(TAG, "Exception " + e);
        }
        return insertRowValue;
    }

    @Override
    protected void onPostExecute(Long result) {
        queryCallback.onCreateQuerySuccess(result, responseType);
    }
}

