package com.turgunboyevjurabek.chempionat3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.turgunboyevjurabek.chempionat3.models.Valyuta

@Dao
interface Interfase {
    @Insert
    fun insert(valyuta: ArrayList<Valyuta>)

    @Query("select *from Valyuta")
    fun getAll():List<Valyuta>

    @Query("select *from Valyuta where title like '%' || :searchQuery || '%' or userId like '%'||:searchQuery ")
    fun searchQuery(searchQuery :String):List<Valyuta>

}