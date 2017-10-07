package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

//import net.sqlcipher.database.SQLiteDatabase;
//import net.sqlcipher.database.SQLiteOpenHelper;


public class DatabaseManager extends SQLiteOpenHelper {

    private static DatabaseManager databaseManager;
    private String TAG = "DatabaseManager";
    private SQLiteDatabase database;
    private int responseType = 0;
    private Object extraParam;

    private DatabaseManager(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
        WriteOnDatabase();
    }

    public static DatabaseManager getDatabaseManager(Context _context) {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(_context);
        }
        return databaseManager;
    }

    public void WriteOnDatabase() {
        //this.database = getWritableDatabase("ETW@567");
        this.database = getWritableDatabase();
    }

    public void CloseDatabaseConnection() {
        database.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creation of Tables in DB
        try {
            db.execSQL(DBConstants.CREATE_SQL_TABLE.CREATE_NOTES_TABLE);
            db.execSQL(DBConstants.CREATE_SQL_TABLE.CREATE_GOALS_TABLE);
            db.execSQL(DBConstants.CREATE_SQL_TABLE.CREATE_PLAN_TABLE);
            db.execSQL(DBConstants.CREATE_SQL_TABLE.CREATE_USERINFO_TABLE);
            db.execSQL(DBConstants.CREATE_SQL_TABLE.CREATE_GOAL_NOTES_TABLE);

        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Upgrade the new version of Database

    }


    @SuppressWarnings("unchecked")
    synchronized void createBulkOps(Object... params) {

        database.beginTransaction();
        try {

            List<Object> objectsList = (List<Object>) params[1];
            responseType = (int) params[2];
            extraParam = params.length != 4 ? 0 : params[3];

            Object object = objectsList.get(0);

            /* else if (object instanceof UserInfo) {
                usersBulkInsert(objectsList);
            }*/


            database.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.d(TAG, "createBulkOps: " + ex);
        } finally {
            database.endTransaction();
            database.close();
        }
    }

    @SuppressWarnings("unchecked")
    synchronized void updateNotesBulkOps(Object... params) {

        database.beginTransaction();
        try {

            String[] objectsList = (String[]) params[1];
            responseType = (int) params[2];
            extraParam = params[3];

            database.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.d(TAG, "createBulkOps: " + ex);
        } finally {
            database.endTransaction();
            database.close();
        }
    }


    //endregion

    //region Update Operations

    public synchronized int updateOps(String table, ContentValues contentValues, String where, String[] whereArgs) {

        Log.d(TAG, "updateOps: ");
        try {
            return database.update(table, contentValues, where, whereArgs);
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }
        return 0;
    }

    //endregion

    //region  Retrieve Operation

    public Cursor retrieveOps(boolean distinct, String tableName, String[] columnName, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {

        Cursor cursor = null;
        try {

            cursor = database.query(distinct, tableName, columnName, selection, selectionArgs, groupBy, having, orderBy, limit);

        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }
        return cursor;
    }

    Cursor processMultiTableOps(String query, String[] selectionArgs) {

        Cursor cursor = null;
        try {
            cursor = database.rawQuery(query, selectionArgs);

        } catch (Exception ex) {
            Log.d(TAG, "processMultiTableOps: " + ex);
        }
        return cursor;

    }

    //endregion

    //region delete records Currently Not In Use

    synchronized boolean deleteRecords(String tableName, String where, String[] args) {
        boolean isDeleteSuccess = true;
        try {
            database.delete(tableName, where, args);
        } catch (Exception ex) {
            isDeleteSuccess = false;
            Log.d(TAG, "deleteRecords: " + tableName);
        }
        return isDeleteSuccess;
    }

    public synchronized boolean deleteMultipleRecords(String query) {
        boolean isDeleteSuccess = true;
        try {
            database.execSQL(query);
        } catch (Exception ex) {
            isDeleteSuccess = false;
            Log.d(TAG, "deleteRecords: ");
        }
        return isDeleteSuccess;
    }


    public synchronized void beginTransaction() {
        try {
            database.beginTransaction();
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }

    }

    public synchronized void endTransaction() {
        try {
            database.endTransaction();
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }

    }

    public synchronized void setTransactionSuccessful() {
        try {
            database.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }

    }


    //endregion

    //region Bulk Insert operation


}
