package com.turgunboyevjurabek.chempionat3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.turgunboyevjurabek.chempionat3.models.Valyuta

@Database(entities = [Valyuta::class], version = 1)
abstract class DataBaseRomm: RoomDatabase() {

    abstract fun abstrakDao():Interfase

    companion object{
        fun newInstens(context: Context):DataBaseRomm{
            return Room.databaseBuilder(context,DataBaseRomm::class.java,"allambalo")
                .allowMainThreadQueries()
                .build()
        }

    }

}