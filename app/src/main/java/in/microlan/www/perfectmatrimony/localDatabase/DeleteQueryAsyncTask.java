package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DeleteQueryAsyncTask extends AsyncTask<Object, Void, Boolean> {

    int responseType = 0;
    private String TAG = DeleteQueryAsyncTask.class.getSimpleName();
    private Context context;
    private DatabaseManager databaseManager;
    private IDBCallback queryCallback;

    public DeleteQueryAsyncTask(Context _context, IDBCallback _queryCallback) {
        this.context = _context;
        databaseManager = DatabaseManager.getDatabaseManager(_context);
        queryCallback = _queryCallback;
    }


    @Override
    protected Boolean doInBackground(Object... params) {

        String tableName = (String) params[0];
        String where = (String) params[1];
        String[] whereArgs = (String[]) params[2];
        responseType = (int) params[3];

        databaseManager.WriteOnDatabase();
        try {
            databaseManager.deleteRecords(tableName, where, whereArgs);
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean s) {

        queryCallback.onDeleteQuerySuccess(s, responseType);

    }
}
