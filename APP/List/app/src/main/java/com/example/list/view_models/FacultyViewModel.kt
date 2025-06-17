package com.example.list.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.list.Data.Faculty
import com.example.list.Data.ListOfFaculty
import com.example.list.repositories.MainRepository

class FacultyViewModel : ViewModel() {
    var facultyList = MainRepository.getInstance().listOfFaculty
    private var _faculty : Faculty? = null
    val faculty
        get() = _faculty

//    private val facultyListObserver = Observer<ListOfFaculty?> {
//            list ->
//        facultyList.postValue(list)
//    }
    init {
//        MainRepository.getInstance().listOfFaculty.observeForever(facultyListObserver)
        MainRepository.getInstance().faculty.observeForever {
            _faculty=it
        }
    }

    fun deleteFaculty() {
        if (faculty!=null)
            MainRepository.getInstance().deleteFaculty(faculty!!)
    }

    fun appendFaculty(facultyName : String) {
        val faculty = Faculty()
        faculty.name = facultyName
        MainRepository.getInstance().addFaculty(faculty)
    }
    fun updateFaculty(facultyName : String) {
        if (_faculty != null) {
            _faculty!!.name=facultyName
            MainRepository.getInstance().updateFaculty(_faculty!!)
        }
    }

    fun setCurrentFaculty(faculty: Faculty) {
        MainRepository.getInstance().setCurrentFaculty(faculty)
    }
}
