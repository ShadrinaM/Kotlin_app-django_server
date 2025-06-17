package com.example.list.Interfaces

import com.example.list.Data.NamesOfFragment
import com.example.list.Data.Student

interface MainActivityCallbacks {
    fun newTitle(_title : String)
    fun showFragment(fragmentType: NamesOfFragment, student: Student? = null)
}