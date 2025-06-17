package com.example.list.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "student",
    indices = [Index("id"), Index("group_id", "id")],
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = ["id"],
            childColumns = ["group_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

class Student (
    @PrimaryKey val id: Long = -1L,
    var lastName: String="",
    var firstName: String="",
    var middleName: String="",
    @ColumnInfo(name = "birth_date")var birthDate: Date = Date(),
    @ColumnInfo(name = "group_id")var groupID: Long= -1L,
    var phone: String="",
    var sex : Int=0
){
    val shortName
        get()=lastName+
                (if(firstName.length>0) {" ${firstName.subSequence(0, 1)}. "} else "") +
                (if(middleName.length>0) {" ${middleName.subSequence(0, 1)}. "} else "")
}