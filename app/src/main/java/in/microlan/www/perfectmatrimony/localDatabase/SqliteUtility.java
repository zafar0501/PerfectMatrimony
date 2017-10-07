package in.microlan.www.perfectmatrimony.localDatabase;

import android.content.ContentValues;
import android.content.Context;

import in.microlan.www.perfectmatrimony.network.NetworkConstants;


public class SqliteUtility {


    //delete record
    public static void deleteRecordInSqlite(Context context, String tableName, String where, String[] args, IDBCallback idbQueryCallbackListener, int responseType) {

        Object[] params = new Object[]{tableName, where, args, responseType};
        DeleteQueryAsyncTask deleteQueryAsyncTask = new DeleteQueryAsyncTask(context, idbQueryCallbackListener);
        deleteQueryAsyncTask.execute(params);
    }


    //retrieve record
    public static void retrieveRecordInSqlite(Context context, boolean distinct, String tableName, String[] columnName, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, int responseType, IDBCallback idbQueryCallbackListener) {

        Object[] params = new Object[]{distinct, tableName, columnName, selection, selectionArgs, groupBy, having, orderBy, limit, responseType};
        RetrieveQueryAsyncTask queryAsyncTask = new RetrieveQueryAsyncTask(context, idbQueryCallbackListener);
        queryAsyncTask.execute(params);
    }


    //update record
    public static void updateRecordInSqlite(Context context, String tableName, ContentValues contentValues, String selection, String[] selectionArgs, int responseType, IDBCallback idbQueryCallbackListener) {

        Object[] params = new Object[]{tableName, contentValues, selection, selectionArgs, responseType};
        UpdateQueryAsyncTask queryAsyncTask = new UpdateQueryAsyncTask(context, idbQueryCallbackListener);
        queryAsyncTask.execute(params);
    }

    public static void updateNotesArchiveStatueInSqlite(Context context, String[] noteMessageId, int isArchive, IDBCallback idbQueryCallbackListener) {

        Object[] params = new Object[]{DBConstants.Tables.TBL_NOTES, noteMessageId, NetworkConstants.RESPONSE_TYPE.UPDATE_TYPE, isArchive};
        UpdateNotesQueryAsyncTask queryAsyncTask = new UpdateNotesQueryAsyncTask(context, idbQueryCallbackListener);
        queryAsyncTask.execute(params);
    }


}
