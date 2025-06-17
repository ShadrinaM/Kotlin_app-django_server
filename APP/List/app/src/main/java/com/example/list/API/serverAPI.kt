package com.example.list.API

import com.example.list.Data.Faculty
import com.example.list.Data.Group
import com.example.list.Data.Student
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


interface serverAPI {
    @GET("faculties")
    fun getFaculties(): Call<List<Faculty>>

    @Headers("Content-Type: application/json")
    @POST("faculty")
    fun postFaculty(@Body faculty: Faculty): Call<Unit>

    @DELETE ("faculty/{id}")
    fun deleteFaculty(@Path("id") id : String): Call<Unit>

//    @GET("groups")
//    fun getGroups(): Call<List<Group>>
//
//    @Headers("Content-Type: application/json")
//    @POST("group")
//    fun postGroup(@Body group: Group): Call<Unit>
//
//    @DELETE ("group/{id}")
//    fun deleteGroup(@Path("id") id : String): Call<Unit>
//
//    @GET("students")
//    fun getStudents(): Call<List<Student>>
//
//    @Headers("Content-Type: application/json")
//    @POST("student")
//    fun postStudent(@Body student: Student): Call<Unit>
//
//    @DELETE ("student/{id}")
//    fun deleteStudent(@Path("id") id : String): Call<Unit>
}