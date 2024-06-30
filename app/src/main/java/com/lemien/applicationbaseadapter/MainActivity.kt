package com.lemien.applicationbaseadapter

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lemien.applicationbaseadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var list = arrayListOf("Physics", "Maths", "Java",)
    var studentList = arrayListOf<Student>()
    var baseAdapterClass = BaseAdapterClass(studentList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentList.add(Student(rollNo = 1, "Elphine", "Physics"))
        studentList.add(Student(rollNo = 2, "Clifford", "Maths"))
        studentList.add(Student(rollNo = 3, "Daniel", "Java"))
        binding?.listview?.adapter = baseAdapterClass


        binding?.listview?.adapter = baseAdapterClass
        binding?.fab?.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            dialog.show()

            var etName = dialog.findViewById<EditText>(R.id.etName)
            var addbtn = dialog.findViewById<Button>(R.id.addbtn)
            addbtn?.setOnClickListener {
                if (etName?.text?.toString().isNullOrEmpty()) {
                    etName?.error = "enter name"
                } else {
                    list.add(etName.text.toString())
                    dialog.dismiss()
                    baseAdapterClass.notifyDataSetChanged()
                }
            }
        }
        binding?.listview?.setOnItemClickListener { adapterView, view, i, l ->
            list.removeAt(i)
            baseAdapterClass.notifyDataSetChanged()
        }
        binding?.listview?.setOnItemLongClickListener { adapterView, view, i, l ->
            return@setOnItemLongClickListener true
        }
        binding?.fab?.setOnClickListener {
            studentList.add(Student(4, "Velma", "Python"))
            baseAdapterClass.notifyDataSetChanged()
        }
    }
}

