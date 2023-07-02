package com.example.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CalculateDao {
    @Insert
    fun insert(calculate: Calculate)
}
