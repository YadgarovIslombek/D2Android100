package com.example.d2android100.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.d2android100.data.ShopItemDbModel
import com.example.d2android100.domain.ShopItem
import com.example.d2android100.domain.ShopItemDAO


@Database(entities = [ShopItem::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun shopItemDao():ShopItemDAO

    companion object{
        private var myDb:AppDatabase? = null
        @Synchronized
        fun getInstance(context:Context): AppDatabase {
            if (myDb == null) {
                myDb = Room.databaseBuilder(
                    context, AppDatabase::class.java,
                    "main_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return myDb!!
        }

        }
    }
