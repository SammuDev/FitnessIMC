package com.example.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert
import java.util.Date

@Dao
interface CalculateDao {
    @Insert
    fun insert(calculate: Calculate)
}
