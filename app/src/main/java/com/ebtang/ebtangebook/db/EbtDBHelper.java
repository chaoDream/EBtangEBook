package com.ebtang.ebtangebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yunqing.core.db.database.DBHelper;

/**
 * 实现DBHelper 继承SQLiteOpenHelper<br/>
 * 子类可以通过继承该类进行监听数据库版本的变化，和数据库创建,用法参照(
 * {@link SQLiteOpenHelper})
 * @author Administrator
 * 
 */
public class EbtDBHelper extends SQLiteOpenHelper implements DBHelper {

	public static final String DEFAULT_DBNAME = "ebtang.db";
	public static final int DEFAULT_DBVERION = 1; //初始版本为1

	private String path;
	private static EbtDBHelper examDBHelper;

	public static EbtDBHelper getInstance(Context context) {
		if(examDBHelper == null){
			examDBHelper = new EbtDBHelper(context);
		}
		return examDBHelper;
	}

	public EbtDBHelper(Context context) {
		super(context, DEFAULT_DBNAME, null, DEFAULT_DBVERION);
		path = context.getDatabasePath(DEFAULT_DBNAME).getPath();
	}

	public EbtDBHelper(Context context, String name, int version) {
		super(context, name, null, version);
		path = context.getDatabasePath(name).getPath();
	}



	@Override
	public SQLiteDatabase getDatabase() {
		SQLiteDatabase database = null;
		try {
			database = getWritableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return database;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//更新操作 例如course 需要更新 新加的实体无需在此写入，会自动添加一个表的
//		String course_table_name = TableFactory.getTableName(Course.class);
//		String sql = "alter table "+ course_table_name + " add column testName varchar(30);";
//		db.execSQL(sql);
	}

	@Override
	public String getDatabasePath() {
		return path;
	}
}
