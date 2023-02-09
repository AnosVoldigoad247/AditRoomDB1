package com.example.aditroomdb1.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aditroomdb1.Room.SiswaDao

@Database(
    entities = [TbSiswa::class],
    version = 1
)
abstract class DbSmksa : RoomDatabase(){

    abstract fun siswa() : SiswaDao

    companion object {

        @Volatile private var instance : DbSmksa? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DbSmksa::class.java,
            "siswa12345.db"
        ).build()

    }
}