package com.example.list.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID
import java.text.SimpleDateFormat
import java.util.*

//@Entity(tableName = "student",
//    indices = [Index("id"), Index("group_id", "id")],
//    foreignKeys = [
//        ForeignKey(
//            entity = Group::class,
//            parentColumns = ["id"],
//            childColumns = ["group_id"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//
//class Student (
//    @PrimaryKey val id: Long = -1L,
//    var lastName: String="",
//    var firstName: String="",
//    var middleName: String="",
//    @ColumnInfo(name = "birth_date")var birthDate: Date = Date(),
//    @ColumnInfo(name = "group_id")var groupID: Long= -1L,
//    var phone: String="",
//    var sex : Int=0
//){
//    val shortName
//        get()=lastName+
//                (if(firstName.length>0) {" ${firstName.subSequence(0, 1)}. "} else "") +
//                (if(middleName.length>0) {" ${middleName.subSequence(0, 1)}. "} else "")
//}


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

data class Student (
    @PrimaryKey val id: Long = -1L,
    @SerializedName("last_name") var lastName: String = "",
    @SerializedName("first_name") var firstName: String = "",
    @SerializedName("middle_name") var middleName: String = "",
    @ColumnInfo(name = "birth_date")
    @SerializedName("birth_date") var birthDate: Date = Date(),
    @ColumnInfo(name = "group_id")
    @SerializedName("group") var groupID: Long = -1L,
    @SerializedName("phone") var phone: String = "",
    @SerializedName("sex") var sex: Int = 0
) {
    val shortName
        get() = lastName +
                (if(firstName.isNotEmpty()) " ${firstName.substring(0, 1)}." else "") +
                (if(middleName.isNotEmpty()) " ${middleName.substring(0, 1)}." else "")
}















//@Entity(tableName = "student",
//    indices = [Index("id"), Index("group_id", "id")],
//    foreignKeys = [
//        ForeignKey(
//            entity = Group::class,
//            parentColumns = ["id"],
//            childColumns = ["group_id"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//data class Student (
//    @PrimaryKey val id: Long = -1L,
//    @SerializedName("last_name") var lastName: String = "",
//    @SerializedName("first_name") var firstName: String = "",
//    @SerializedName("middle_name") var middleName: String = "",
//    @ColumnInfo(name = "birth_date")
//    @SerializedName("birth_date") var birthDate: Date = Date(),
//    @ColumnInfo(name = "group_id")
//    @SerializedName("group") var groupID: Long = -1L,
//    @SerializedName("phone") var phone: String = "",
//    @SerializedName("sex") var sex: Int = 0
//) {
//    val shortName
//        get() = lastName +
//                (if(firstName.isNotEmpty()) " ${firstName.substring(0, 1)}." else "") +
//                (if(middleName.isNotEmpty()) " ${middleName.substring(0, 1)}." else "")
//}

//data class Student (
//    @PrimaryKey val id: Long = -1L,
//    @SerializedName("last_name") var lastName: String = "",
//    @SerializedName("first_name") var firstName: String = "",
//    @SerializedName("middle_name") var middleName: String = "",
//
//    // Исправленное поле birthDate
//    @ColumnInfo(name = "birth_date")
//    @SerializedName("birth_date")
//    var birthDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
//
//    @ColumnInfo(name = "group_id")
//    @SerializedName("group") var groupID: Long = -1L,
//    @SerializedName("phone") var phone: String = "",
//    @SerializedName("sex") var sex: Int = 0
//) {
//    // Дополнительные методы для работы с датой
//    fun getBirthDateAsDate(): Date {
//        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(birthDate) ?: Date()
//    }
//
//    fun setBirthDate(date: Date) {
//        birthDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
//    }
//
//    val shortName
//        get() = lastName +
//                (if(firstName.isNotEmpty()) " ${firstName.substring(0, 1)}." else "") +
//                (if(middleName.isNotEmpty()) " ${middleName.substring(0, 1)}." else "")
//}


//@Entity(tableName = "student",
//    indices = [Index("id"), Index("group_id", "id")],
//    foreignKeys = [
//        ForeignKey(
//            entity = Group::class,
//            parentColumns = ["id"],
//            childColumns = ["group_id"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//data class Student(
//    @PrimaryKey val id: Long = -1L,
//    @SerializedName("last_name") var lastName: String = "",
//    @SerializedName("first_name") var firstName: String = "",
//    @SerializedName("middle_name") var middleName: String = "",
//    @ColumnInfo(name = "birth_date")
//    @SerializedName("birth_date") var birthDate: Date = Date(),
//    @ColumnInfo(name = "group_id")
//    @SerializedName("group") var groupID: Long = -1L,
//    @SerializedName("phone") var phone: String = "",
//    @SerializedName("sex") var sex: Int = 0
//) {
//    val shortName
//        get() = lastName +
//                (if(firstName.isNotEmpty()) " ${firstName.substring(0, 1)}." else "") +
//                (if(middleName.isNotEmpty()) " ${middleName.substring(0, 1)}." else "")
//}