package ir.arash.altafi.sample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.arash.altafi.sample.data.model.TestDetailEntity

@Database(entities = [TestDetailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testDetailDao(): TestDetailDao
}