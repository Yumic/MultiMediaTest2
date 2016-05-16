package yumic.diverbob.love.multimediatest2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oathkeeper on 2016/5/5.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "multi_media.db"; //数据库名称
    private static final int version = 1; //数据库版本

    public static final String TABLE_NAME1 = "category_list";
    public static final String TABLE_NAME2 = "list_name";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }

    /**
     * 创建数据库时调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建两个数据库表
        String sql = "create table if not exists "+TABLE_NAME1+"(category varchar(20) not null , listName varchar(60) not null," +
                "id int identity(1,1),constraint pkid primary key (id));";
        db.execSQL(sql);
       // String sql2 = "drop table "+TABLE_NAME2;
      String sql2 = "create table if not exists "+TABLE_NAME2+"(filePath varchar(255) not null , listName varchar(60) not null,"+
               "id int identity(1,1),constraint pkid primary key (id));";
        db.execSQL(sql2);
    }

    /**
     * 版本更新时调用
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}