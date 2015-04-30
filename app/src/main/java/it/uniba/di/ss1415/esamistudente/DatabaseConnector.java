package it.uniba.di.ss1415.esamistudente;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bakapo on 30/04/15.
 */
public class DatabaseConnector {

    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            String createQuery = "CREATE TABLE LOGIN(" +
                    "ID CHAR(10) PRIMARY KEY, " +
                    "PW CHAR(10)" +
                    ");";
            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
    }

    private static final String DATABASE_NAME = "EsamiStudenti";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public DatabaseConnector(Context context){
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    public void open() throws SQLException{
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close(){
        if (database != null){
            database.close();
        }
    }

    public boolean login(String id, String password){
        open();
        SQLiteCursor result = (SQLiteCursor) database.query("LOGIN", null, "ID = '" + id + "' AND PW = '" + password +"'", null, null, null, null);
        return result.getCount() == 1; //esiste un utente con l'ID e la password specificati: accesso consentito
    }

    private boolean existsUser(){
        return database.query("LOGIN", null, null, null, null, null, null).getCount() != 0;
    }

    public void register(String id, String password){
        ContentValues newUser = new ContentValues();
        newUser.put("ID", id);
        newUser.put("PW", password);
        open();
        if(existsUser()){
            database.delete("LOGIN", null,null);
        } else {
            database.insert("LOGIN", null, newUser);
        }
    }

}
