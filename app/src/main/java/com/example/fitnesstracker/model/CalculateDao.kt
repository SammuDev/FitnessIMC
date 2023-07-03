package com.example.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculateDao {
    @Insert
    fun insert(calculate: Calculate)

    @Query("SELECT * FROM Calculate WHERE type = :type")
    fun getRegisterByType(type: String): List<Calculate>
}
