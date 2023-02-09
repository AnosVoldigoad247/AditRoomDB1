package com.example.aditroomdb1.Room

import androidx.room.*

@Dao
interface SiswaDao {

    @Insert
    fun addData(siswa: TbSiswa)

    @Update
    fun UpdateData(siswa: TbSiswa)

    @Delete
    fun DeleteData(siswa: TbSiswa)

    @Query("SELECT * FROM TbSiswa")
    fun getDatas(): List<TbSiswa>

    @Query("SELECT * FROM TbSiswa WHERE nis=:siswa_id")
    fun getData(siswa_id: Int): List<TbSiswa>
}