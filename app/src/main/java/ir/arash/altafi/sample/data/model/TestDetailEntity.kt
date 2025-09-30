package ir.arash.altafi.sample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class TestDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val family: String,
    val avatar: String
)