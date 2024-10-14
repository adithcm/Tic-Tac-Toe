package com.example.newproject.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DATABASE_NAME = "tictactoe.db"
private const val DATABASE_VERSION = 1
private const val TABLE_NAME = "past_games"
private const val COLUMN_ID = "id"
private const val COLUMN_DATE = "date"
private const val COLUMN_WINNER = "winner"
private const val COLUMN_DIFFICULTY = "difficulty"

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_DATE TEXT, "
                + "$COLUMN_WINNER TEXT, "
                + "$COLUMN_DIFFICULTY TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertGameResult(date: String, winner: String, difficulty: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DATE, date)
            put(COLUMN_WINNER, winner)
            put(COLUMN_DIFFICULTY, difficulty)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllGames(): List<GameResult> {
        val pastGames = mutableListOf<GameResult>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
                val winner = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WINNER))
                val difficulty = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULTY))
                pastGames.add(GameResult(id, date, winner, difficulty))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return pastGames
    }
}

data class GameResult(val id: Int, val date: String, val winner: String, val difficulty: String)