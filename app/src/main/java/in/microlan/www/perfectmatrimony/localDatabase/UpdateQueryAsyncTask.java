package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class UpdateQueryAsyncTask extends AsyncTask<Object, Void, String> {

    private String TAG = UpdateQueryAsyncTask.class.getSimpleName();
    private Context context;
    private DatabaseManager databaseManager;
    private IDBCallback queryCallback;
    private int responseType;

    public UpdateQueryAsyncTask(Context _context, IDBCallback _queryCallback) {
        this.context = _context;
        databaseManager = DatabaseManager.getDatabaseManager(_context);
        queryCallback = _queryCallback;
    }

    @Override
    protected String doInBackground(Object... params) {

        Log.d(TAG, "doInBackground: calling update");

        String tableName = (String) params[0];
        ContentValues contentValue = (ContentValues) params[1];
        String where = (String) params[2];
        String[] whereArgs = (String[]) params[3];
        responseType = (int) params[4];

        databaseManager.WriteOnDatabase();

        try {

            databaseManager.updateOps(tableName, contentValue, where, whereArgs);

        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        /**
         * 1. Get the result after successful query operation
         * 2. Send the result back to Activity/Fragment
         */
        //databaseManager.CloseDatabaseConnection();
        queryCallback.onUpdateQuerySuccess(responseType);

    }
}

