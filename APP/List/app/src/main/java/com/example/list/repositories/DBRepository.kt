package com.example.list.repositories

import com.example.list.Data.Faculty
import com.example.list.Data.Group
import com.example.list.Data.Student
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface DBRepository {
//    Для Faculty
    fun getFaculty(): Flow<List<Faculty>>
    suspend fun insertFaculty(faculty: Faculty)
    suspend fun updateFaculty(faculty: Faculty)
    suspend fun insertAllFaculty(facultyList: List<Faculty>)
    suspend fun deleteFaculty(faculty: Faculty)
    suspend fun deleteAllFaculty()
//    Для Student
    fun getAllStudents(): Flow<List<Student>>
    fun getGroupStudents(groupID : UUID): Flow<List<Student>>
    suspend fun insertStudent(student: Student)
    suspend fun deleteStudent(student: Student)
    suspend fun deleteAllStudents()
//    Для Group
    fun getAllGroups(): Flow<List<Group>>
    fun getFacultyGroups(facultyID: UUID): List<Group>
    suspend fun insertGroup(group: Group)
    suspend fun deleteGroup(group: Group)
    suspend fun deleteAllGroups()
}