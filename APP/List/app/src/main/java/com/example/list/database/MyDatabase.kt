package com.example.list.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.list.Data.Faculty
import com.example.list.Data.Group
import com.example.list.Data.Student

@Database(
    entities = [Faculty::class, Student::class, Group::class,],
    version = 6,
    exportSchema = false
)
@TypeConverters(DBConverters::class)

abstract class MyDatabase: RoomDatabase() {
    abstract fun myDAO(): MyDAO

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "list_database")
            .fallbackToDestructiveMigration(false)
            .build()

    }
}