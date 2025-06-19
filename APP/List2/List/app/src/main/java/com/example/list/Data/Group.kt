package com.example.list.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.UUID

@Entity(tableName = "groups",
    indices = [Index("id"), Index("faculty_id")],
    foreignKeys = [
        ForeignKey(
            entity = Faculty::class,
            parentColumns = ["id"],
            childColumns = ["faculty_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Group (
    @PrimaryKey val id: Long = -1L,
    @ColumnInfo(name = "group_name") var name: String = "",
    @ColumnInfo(name = "faculty_id")
    @SerializedName("faculty")  // Это ключевое изменение!
    var facultyID: Long = -1L
)