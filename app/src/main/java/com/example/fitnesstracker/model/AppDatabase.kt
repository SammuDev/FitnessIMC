package com.example.fitnesstracker.model

import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    abstract fun calculateDao(): CalculateDao
}
