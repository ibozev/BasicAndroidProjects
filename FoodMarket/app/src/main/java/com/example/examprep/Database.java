package com.example.examprep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "menuManager";
    private static final String TABLE_ARTICLES = "articles";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE_NAME = "imageName";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ARTICLE_TABLE = "CREATE TABLE " + TABLE_ARTICLES + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PRICE + " DOUBLE," +
                KEY_IMAGE_NAME + " TEXT" + ")";
        db.execSQL(CREATE_ARTICLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES);
        onCreate(db);
    }

    public void onDeleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTICLES, null, null);
    }

    void addArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, article.getName());
        values.put(KEY_PRICE, article.getPrice());
        values.put(KEY_IMAGE_NAME, article.getImageName());
        db.insert(TABLE_ARTICLES, null, values);
        db.close();
    }

    Article getArticle(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ARTICLES,
                new String[]{KEY_ID, KEY_NAME, KEY_PRICE, KEY_IMAGE_NAME},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null,
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Article article = new Article(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Double.parseDouble(cursor.getString(2)),
                cursor.getString(3));
        return article;
    }

    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<Article>();
        String selectQuery = "SELECT  * FROM " + TABLE_ARTICLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Article article = new Article();
                article.setId(Integer.parseInt(cursor.getString(0)));
                article.setName(cursor.getString(1));
                article.setPrice(Double.parseDouble(cursor.getString(2)));
                article.setImageName(cursor.getString(3));
                articleList.add(article);
            } while (cursor.moveToNext());
        }
        return articleList;
    }

    public int updateArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, article.getName());
        values.put(KEY_PRICE, article.getPrice());

        return db.update(TABLE_ARTICLES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(article.getId())});
    }

    public void deleteArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTICLES, KEY_ID + " = ?", new String[]{String.valueOf(article.getId())});
        db.close();
    }

    public int getArticlesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ARTICLES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public void clearArticles() {
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + TABLE_ARTICLES;
        db.execSQL(deleteQuery);
    }
}
