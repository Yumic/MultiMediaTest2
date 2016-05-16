package yumic.diverbob.love.multimediatest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oathkeeper on 2016/5/5.
 */
public class ListDao {
    private static final String TAG = "OrdersDao";

    // 列定义
    private final String[] ORDER_COLUMNS = new String[] {"category", "listName"};
    private final String[] ORDER_COLUMNS2 = new String[] {"filePath", "listName"};

    private Context context;
    private DataBaseHelper dataBaseHelper;

    public ListDao(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist(String tableName){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dataBaseHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(tableName, new String[]{"COUNT(Id)"}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }



    /**
     * 新增一条数据
     */
    public boolean insertList(String category,String listName){
        SQLiteDatabase db = null;

        try {
            db = dataBaseHelper.getWritableDatabase();
            db.beginTransaction();

            // insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
            ContentValues contentValues = new ContentValues();
            contentValues.put("category", category);
            contentValues.put("listName", listName);
            db.insertOrThrow(DataBaseHelper.TABLE_NAME1, null, contentValues);
            db.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
                Log.d(TAG,"新增一条记录："+"category:"+category+" listName:"+listName);
            }
        }
        return false;
    }

    /**
     * 数据查询  此处将用户名为"Bor"的信息提取出来
     */
    public ArrayList<String> getList(String category){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dataBaseHelper.getReadableDatabase();

            // select * from Orders where CustomName = 'Bor'
            cursor = db.query(dataBaseHelper.TABLE_NAME1,
                    ORDER_COLUMNS,
                    "category = ?",
                    new String[] {category},
                    null, null, null);

            if (cursor.getCount() > 0) {
                ArrayList<String> list = new ArrayList<String>(cursor.getCount());
                while (cursor.moveToNext()) {
                    String order = cursor.getString(cursor.getColumnIndex("listName"));

                    Log.d("ListDao","类别："+category+"ListName"+order);
                    list.add(order);
                }
                return list;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 数据查询 提取特定列表中的所有文件路径
     */
        public ArrayList<String> getMediaList(String listName){
            SQLiteDatabase db = null;
            Cursor cursor = null;

        try {
            db = dataBaseHelper.getReadableDatabase();

            // select * from Orders where CustomName = 'Bor'
            cursor = db.query(dataBaseHelper.TABLE_NAME2,
                    ORDER_COLUMNS2,
                    "listName = ? ",
                    new String[] {listName},
                    null, null, null);

            if (cursor.getCount() > 0) {
                ArrayList<String> list = new ArrayList<String>(cursor.getCount());
                while (cursor.moveToNext()) {
                    String order = cursor.getString(cursor.getColumnIndex("listName"));
                    String order3 = cursor.getString(cursor.getColumnIndex("filePath"));
                    Log.d("ListDao","ListName"+order+"  filePath:"+order3);
                    list.add(order);
                }
                return list;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }


    /**
     * 新增一条数据
     */
    public boolean insertFile(String filePath,String listName){
        SQLiteDatabase db = null;

        try {
            db = dataBaseHelper.getWritableDatabase();
            db.beginTransaction();

            ContentValues contentValues = new ContentValues();
            contentValues.put("filePath", filePath);
            contentValues.put("listName", listName);
            db.insertOrThrow(DataBaseHelper.TABLE_NAME2, null, contentValues);

            db.setTransactionSuccessful();
            Log.d(TAG,"向列表"+listName+"中添加了"+filePath);
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 删除列表
     */
    public boolean deleteList(String listName) {
        SQLiteDatabase db = null;

        try {
            db = dataBaseHelper.getWritableDatabase();
            db.beginTransaction();

            // delete from Orders where Id = 7
            db.delete(dataBaseHelper.TABLE_NAME1, "Id = ?", new String[]{listName});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

}