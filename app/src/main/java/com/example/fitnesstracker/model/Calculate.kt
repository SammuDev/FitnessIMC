package com.example.fitnesstracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Calculate(
    @PrimaryKey(true) val id: Int = 0,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("res") val res: Double,
    @ColumnInfo("created_date") val createdDate: Date
)
