package com.turgunboyevjurabek.chempionat3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Valyuta(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val body: String,
    val title: String,
    val userId: Int
)