package com.example.aditroomdb1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TbSiswa (
    @PrimaryKey(autoGenerate = true)
    val nis: Int,
    val nama:String,
    val kelas:String,
    val alamat:String,
)