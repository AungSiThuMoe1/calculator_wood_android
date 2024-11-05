package com.example.aungsithumoe.calculatorforwood;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class dbhelper extends SQLiteOpenHelper {
    String DB_PATH = null;
	private Context myContext;
    SQLiteDatabase db;
    public dbhelper(Context ctx) {
        super(ctx, "wood.db", null, 1);
        this.myContext = ctx;
        DB_PATH = "/data/data/" + myContext.getPackageName() + "/"
                + "databases/wood.db";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            db.execSQL("create table cut(cid integer primary key autoincrement,thick text not null,width text not null,length text not null,quantity text not null,totalcu text not null,total text not null)");
            db.execSQL("create table round(rid integer primary key autoincrement,length text not null,perimeter text not null,quantity text not null,totalcu text not null,total text not null)");
            Log.d("create DB", "Success");
        }
    }
    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
// database does't exist yet.
            System.out.println("DB does't exist yet!");
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("drop table if exists cut");
        onCreate(db);
    }
    public void clear()
    {   SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("drop table if exists cut");
        db.execSQL("drop table if exists round");
        db.execSQL("create table cut(cid integer primary key autoincrement,thick text not null,width text not null,length text not null,quantity text not null,totalcu text not null,total text not null)");
        db.execSQL("create table round(rid integer primary key autoincrement,length text not null,perimeter text not null,quantity text not null,totalcu text not null,total text not null)");
        Log.d("create DB", "Success");
    }
    public Long insertCut(double thick,double width,double length,double quantity,double cuft,double cumt)
    {
        ContentValues cv=new ContentValues();
        cv.put("thick",thick+"");
        cv.put("width",width+"");
        cv.put("length",length+"");
        cv.put("quantity",quantity+"");
        cv.put("totalcu",cuft+"");
        cv.put("total",cumt+"");
        SQLiteDatabase db=this.getWritableDatabase();
        Long cid=db.insert("cut",null,cv);
        db.close();
        return cid;
    }
    public void open()
    {
        SQLiteDatabase db=this.getWritableDatabase();
    }
    public Long insertRound(double length,double perimeter,double quantity,double cuft,double cumt)
    {
        ContentValues cv=new ContentValues();
        cv.put("length",length+"");
        cv.put("perimeter",perimeter+"");
        cv.put("quantity",quantity+"");
        cv.put("totalcu",cuft+"");
        cv.put("total",cumt+"");
        SQLiteDatabase db=this.getWritableDatabase();
        Long rid=db.insert("round",null,cv);
        db.close();
        return rid;
    }
   public List getAllCut()
   {
       List res=new ArrayList<Cutsize>();
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor = db.rawQuery("SELECT * FROM cut", null);
       cursor.moveToFirst();
       while (!cursor.isAfterLast()) {
           Cutsize cut=new Cutsize();
           cut.setId(cursor.getInt(0));
           cut.setThick(Double.parseDouble(cursor.getString(1)));
           cut.setWidth(Double.parseDouble(cursor.getString(2)));
           cut.setLength(Double.parseDouble(cursor.getString(3)));
           cut.setQuantity(Double.parseDouble(cursor.getString(4)));
           res.add(cut);
           cursor.moveToNext();
       }
       cursor.close();
       db.close();
       return res;
   }
    public List getAllRound()
    {
        List res=new ArrayList<Round>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM round", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Round cut=new Round();
            cut.setId(cursor.getInt(0));
            cut.setLength(Double.parseDouble(cursor.getString(1)));
            cut.setPerimeter(Double.parseDouble(cursor.getString(2)));
            cut.setQuantity(Double.parseDouble(cursor.getString(3)));
            res.add(cut);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return res;
    }

    public double totalcutft()
    {
        double total=0.0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT totalcu FROM cut", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            total+=Double.parseDouble(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
       return total;
    }
    public double totalcut()
    {
        double total=0.0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT total FROM cut", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            total+=Double.parseDouble(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return total;
    }
    public double totalrountft()
    {
        double total=0.0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT totalcu FROM round", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            total+=Double.parseDouble(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return total;
    }
    public double totalround()
    {
        double total=0.0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT total FROM round", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            total+=Double.parseDouble(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return total;
    }
public void deleteRound(int i)
{
    SQLiteDatabase db=this.getWritableDatabase();
    db.execSQL("DELETE FROM round WHERE rid="+i);
    db.close();
}
    public void deleteCut(int i)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM cut WHERE cid="+i);
        db.close();
    }
    public void updateRound(int i,double length,double perimeter,double quantity,double cuft,double cumt)
    {
        ContentValues cv=new ContentValues();
        cv.put("length",length+"");
        cv.put("perimeter",perimeter+"");
        cv.put("quantity",quantity+"");
        cv.put("totalcu",cuft+"");
        cv.put("total",cumt+"");
        SQLiteDatabase db=this.getWritableDatabase();
        db.update("round",cv,"rid="+i,null);
        db.close();
    }
public void updateCut(int i,double thick,double width,double length,double quantity,double cuft,double cumt)
{
    ContentValues cv=new ContentValues();
    cv.put("thick",thick+"");
    cv.put("width",width+"");
    cv.put("length",length+"");
    cv.put("quantity",quantity+"");
    cv.put("totalcu",cuft+"");
    cv.put("total",cumt+"");
    SQLiteDatabase db=this.getWritableDatabase();
    db.update("cut",cv,"cid="+i,null);
    db.close();

}
}
