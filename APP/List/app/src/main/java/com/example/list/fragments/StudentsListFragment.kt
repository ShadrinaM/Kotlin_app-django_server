package com.example.list.fragments

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.list.Data.Group
import com.example.list.Data.NamesOfFragment
import com.example.list.Data.Student
import com.example.list.Interfaces.MainActivityCallbacks
import android.Manifest
import com.example.list.R
import com.example.list.databinding.FragmentStudentsListBinding
import com.example.list.view_models.StudentsListViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ARG_PARAM = "group_param"

class StudentsListFragment : Fragment() {

    // Сам добавил
    private lateinit var adapter: StudentAdapter

    companion object {
        @JvmStatic
        private lateinit var group: Group
        fun newInstance(group: Group) = StudentsListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM, Gson().toJson(group))
            }
        }
    }

    private lateinit var group: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val param1 = it.getString(ARG_PARAM)
            if (param1 == null)
                group=Group()
            else {
                val paramType = object : TypeToken<Group>() {}.type
                group = Gson().fromJson<Group>(param1, paramType)
            }
        }
    }

    private lateinit var viewModel: StudentsListViewModel

    private lateinit var _binding : FragmentStudentsListBinding
    val binding
        get()=_binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentsListBinding.inflate(inflater,container, false)
        binding.rvStudents.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentsListViewModel::class.java)
        viewModel.set_Group(group)

//        Сам поменял!!
//        viewModel.studentList.observe(viewLifecycleOwner) {
//            binding.rvStudents.adapter=
//                StudentAdapter(it.filter { it.groupID == group.id })
//        }
//        binding.fabAddStudent.setOnClickListener {
//            editStudent(Student().apply { groupID = group.id })
//        }

        // Создаём адаптер один раз
        val adapter = StudentAdapter(emptyList())
        binding.rvStudents.adapter = adapter

        // Подписываемся и обновляем только содержимое адаптера
        viewModel.studentList.observe(viewLifecycleOwner) { allStudents ->
            val filtered = allStudents.filter { it.groupID == group.id }
            adapter.updateItems(filtered)
        }

        // Обработчик кнопки «+»
        binding.fabAddStudent.setOnClickListener {
            editStudent(Student().apply { groupID = group.id })
        }
//        Вот до этих пор
    }

    private fun deleteDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Удаление!")
            .setMessage("ВЫ действительно хотите удалить студента ${viewModel.student?.shortName ?: ""}?")
            .setPositiveButton("ДА") {_, _ ->
                viewModel.deleteStudent()
            }
            .setNegativeButton("НЕТ", null)
            .setCancelable(true)
            .create()
            .show()
    }

    private fun editStudent(student: Student) {
        (requireActivity() as MainActivityCallbacks).showFragment(NamesOfFragment.STUDENT, student)
        (requireActivity() as MainActivityCallbacks).newTitle("Группа ${viewModel.group!!.name}")
    }
// Сам поменял!!!
//    private inner class StudentAdapter(private val items: List<Student>)
    private inner class StudentAdapter(private var items: List<Student>)
        : RecyclerView.Adapter<StudentAdapter.ItemHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): StudentAdapter.ItemHolder {
            val view = layoutInflater.inflate(R.layout.element_student_list, parent, false)
            return ItemHolder(view)
        }

        override fun getItemCount(): Int = items.size
//    Сам поменял вот эти две строки!!!
//        override fun onBindViewHolder(holder: StudentAdapter.ItemHolder, position: Int) {
        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
//            holder.bind(viewModel.studentList.value!![position])
            holder.bind(items[position])
        }
        private var lastView : View? = null
        private fun updateCurrentView(view: View) {
            val ll = lastView?.findViewById<LinearLayout>(R.id.llStudentButtons)
            ll?.visibility=View.INVISIBLE
            ll?.layoutParams= ll?.layoutParams.apply {this?.width=1}

            val ib = lastView?.findViewById<ImageButton>(R.id.ibCall)
            ib?.visibility=View.INVISIBLE
            ib?.layoutParams= ib?.layoutParams.apply {this?.width=1}

            lastView?.findViewById<ConstraintLayout>(R.id.clStudent)?.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.white))
            view.findViewById<ConstraintLayout>(R.id.clStudent).setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.selected_element))
            lastView=view
        }

//    Сам вставил updateItems!!!!
        fun updateItems(newItems: List<Student>) {
            items = newItems
            notifyDataSetChanged()
        }

        private inner class ItemHolder(view: View)
            : RecyclerView.ViewHolder(view) {
                private lateinit var student: Student
                @OptIn(DelicateCoroutinesApi::class)
                fun bind(student: Student) {
                    this.student=student
//                    Сам поменял!!!
//                    if (student == viewModel.student)
                    if (student.id == viewModel.student?.id)
                        updateCurrentView(itemView)
                    val tv = itemView.findViewById<TextView>(R.id.tvStudentName)
                    tv.text = student.shortName
                    val cl = itemView.findViewById<ConstraintLayout>(R.id.clStudent)
                    cl.setOnClickListener {
                        viewModel.setCurrentStudent(student)
                        updateCurrentView(itemView)
                    }
                    itemView.findViewById<ImageButton>(R.id.ibEditStudent).setOnClickListener {
                        editStudent(student)
                    }
                    itemView.findViewById<ImageButton>(R.id.ibDeleteStudent).setOnClickListener {
                        AlertDialog.Builder(requireContext())
                            .setTitle("Удаление!")
                            .setMessage("ВЫ действительно хотите удалить студента ${viewModel.student?.shortName ?: ""}?")
                            .setPositiveButton("ДА") {_, _ ->
                                viewModel.deleteStudent()
                            }
                            .setNegativeButton("НЕТ", null)
                            .setCancelable(true)
                            .create()
                            .show()
                    }
                    val llb = itemView.findViewById<LinearLayout>(R.id.llStudentButtons)
                    llb.visibility = View.INVISIBLE
                    llb?.layoutParams = llb.layoutParams.apply { this?.width=1 }
                    val ib = itemView.findViewById<ImageButton>(R.id.ibCall)
                    ib.visibility = View.INVISIBLE
                    cl.setOnLongClickListener {
                        cl.callOnClick()
                        llb.visibility=View.VISIBLE
                        if (student.phone.isNotBlank())
                            ib.visibility = View.VISIBLE
                        MainScope().launch {
//                            Здесь был знак вопроса после llb
                            val lp = llb.layoutParams
                            lp?.width = 1
                            val ip = ib.layoutParams
                            ip.width = 1
                            while (lp?.width!! < 350) {
                                lp.width = lp.width + 35
                                llb.layoutParams = lp
                                ip.width = ip.width + 10
                                if (ib.isVisible)
                                    ib.layoutParams=ip
                                delay(50)
                            }
                        }
                        true
                    }
                    itemView.findViewById<ImageButton>(R.id.ibCall).setOnClickListener {
                        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) ==
                            PackageManager.PERMISSION_GRANTED) {
                            val intent = Intent(Intent.ACTION_CALL, "tel:${student.phone}".toUri())
                            startActivity(intent)
                        } else {
                            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), 2)
                        }
                    }
                    
                }
            }
        }
}