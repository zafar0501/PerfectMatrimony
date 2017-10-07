package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

/**
 * 1. Pass the Sql Retrieve param
 * 2. Call the Retrieve Query Operations
 * 3. Get the result after execution of the Operation
 * 4. Send the result back to main thread (Activity/Fragment)
 */
public class RetrieveQueryAsyncTask extends AsyncTask<Object, Void, Cursor> {

    Context context;
    DatabaseManager databaseManager;
    IDBCallback retrieveCallback;
    int responseType = 0;
    private String TAG = "Retrieve Ops";

    public RetrieveQueryAsyncTask(Context _context, IDBCallback _retrieveCallback) {
        this.context = _context;
        databaseManager = DatabaseManager.getDatabaseManager(_context);
        retrieveCallback = _retrieveCallback;
    }

    @Override
    protected Cursor doInBackground(Object... params) {

        Cursor cursor = null;

        databaseManager.WriteOnDatabase();
        try {

            boolean distinct = (boolean) params[0];
            String tableName = (String) params[1];
            String[] columnName = (String[]) params[2];
            String selection = (String) params[3]; // where clause "column1=? and column2=?"
            String[] selectionArgs = (String[]) params[4]; //new string {"column1 value","column2 value"}
            String groupBy = (String) params[5];
            String having = (String) params[6];
            String orderBy = (String) params[7];
            String limit = (String) params[8];
            responseType = (int) params[9];

            cursor = databaseManager.retrieveOps(distinct, tableName, columnName, selection, selectionArgs, groupBy, having, orderBy, limit);

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

