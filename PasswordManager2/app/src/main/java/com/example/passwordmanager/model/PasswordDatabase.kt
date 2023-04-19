package com.example.passwordmanager.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Password::class), version = 2)
abstract class PasswordDatabase : RoomDatabase() {

    abstract fun passwordDao(): PasswordDao

    private class PasswordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch { populateDatabase(database.passwordDao()) }
            }
        }

        suspend fun populateDatabase(passwordDao: PasswordDao) {
            passwordDao.deleteAll()

            var password = Password("VK", "login12", "pass12")
            passwordDao.insert(password)
            password = Password("Email", "login13", "pass13")
            passwordDao.insert(password)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PasswordDatabase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): PasswordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PasswordDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(PasswordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}