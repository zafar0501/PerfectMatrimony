package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class UpdateNotesQueryAsyncTask extends AsyncTask<Object, Void, String> {

    private String TAG = UpdateNotesQueryAsyncTask.class.getSimpleName();
    private Context context;
    private DatabaseManager databaseManager;
    private IDBCallback queryCallback;
    private int responseType;

    public UpdateNotesQueryAsyncTask(Context _context, IDBCallback _queryCallback) {
        this.context = _context;
        databaseManager = DatabaseManager.getDatabaseManager(_context);
        queryCallback = _queryCallback;
    }

    @Override
    protected String doInBackground(Object... params) {

        Log.d(TAG, "doInBackground: calling update");


        databaseManager.WriteOnDatabase();
        try {

            responseType = (int) params[3];
            // bulk update
            databaseManager.updateNotesBulkOps(params);

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
        databaseManager.CloseDatabaseConnection();
        queryCallback.onUpdateQuerySuccess(responseType);

    }
}

