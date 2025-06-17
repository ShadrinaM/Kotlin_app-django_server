package com.example.list.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.list.Data.Faculty
import com.example.list.Data.Group
import com.example.list.Data.Student
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface MyDAO {
//    Для Faculty

    @Query("SELECT * FROM Faculty order by faculty_name")
    fun getFaculty(): Flow<List<Faculty>>

    @Insert(entity = Faculty::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFaculty(faculty: Faculty)

    @Update(entity = Faculty::class)
    suspend fun updateFaculty(faculty: Faculty)

    @Insert(entity = Faculty::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFaculty(facultyList: List<Faculty>)

    @Delete(entity = Faculty::class)
    suspend fun deleteFaculty(faculty: Faculty)

    @Query("DELETE FROM Faculty")
    suspend fun deleteAllFaculty()

//    Для Student

    @Query("SELECT * FROM student")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM student where group_id=:groupID")
    fun getGroupStudents(groupID : UUID): Flow<List<Student>>

    @Insert(entity = Student::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Delete(entity = Student::class)
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM student")
    suspend fun deleteAllStudents()

//    Для Group

    @Query("SELECT * FROM groups")
    fun getAllGroups(): Flow<List<Group>>

    @Query("SELECT * FROM groups where faculty_id=:facultyID")
    fun getFacultyGroups(facultyID : UUID): List<Group>

    @Insert(entity = Group::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: Group)

    @Delete(entity = Group::class)
    suspend fun deleteGroup(group: Group)

    @Query("DELETE FROM groups")
    suspend fun deleteAllGroups()




}