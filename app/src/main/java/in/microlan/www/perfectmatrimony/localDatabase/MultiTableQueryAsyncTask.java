package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;


public class MultiTableQueryAsyncTask extends AsyncTask<Object, Void, Cursor> {

    Context context;
    DatabaseManager databaseManager;
    IDBCallback retrieveCallback;
    int responseType = 0;
    private String TAG = MultiTableQueryAsyncTask.class.getSimpleName();

    public MultiTableQueryAsyncTask(Context _context, IDBCallback _retrieveCallback) {
        this.context = _context;
        databaseManager = DatabaseManager.getDatabaseManager(_context);
        retrieveCallback = _retrieveCallback;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }


    @Override
    protected Cursor doInBackground(Object... params) {

        Cursor cursor = null;

        databaseManager.WriteOnDatabase();
        try {

            String query = (String) params[0];
            String[] selectionArgs = (String[]) params[1];
            responseType = (int) params[2];

            cursor = databaseManager.processMultiTableOps(query, selectionArgs);

        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }


        return cursor;
    }

    @Override
    protected void onPostExecute(Cursor result) {
        /**
         * 1. Get the result after successful query operation
         * 2. Send the result back to Activity/Fragment
         */
        //databaseManager.CloseDatabaseConnection();
        retrieveCallback.OnRetrieveQuerySuccess(result, responseType);

    }
}
