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
    var array = arrayListOf("Finny", "Clifford", "Daniel",)
    var baseAdapterClass = BaseAdapterClass(array)
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

        binding?.listview?.adapter = baseAdapterClass
        binding?.fab?.setOnClickListener{
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            dialog.show()

            var etName = dialog.findViewById<EditText>(R.id.etName)
            var addbtn = dialog.findViewById<Button>(R.id.addbtn)
            addbtn?.setOnClickListener{
                if(etName?.text?.toString().isNullOrEmpty())
                {
                    etName?.error = "enter name"
                }
                else
                {
                    array.add(etName.text.toString())
                    dialog.dismiss()
                    baseAdapterClass.notifyDataSetChanged()
                }
            }
        }
        binding?.listview?.setOnItemClickListener { adapterView, view, i, l ->
            array.removeAt(i)
            baseAdapterClass.notifyDataSetChanged()
        }
        binding?.listview?.setOnItemLongClickListener { adapterView, view, i, l ->
            return@setOnItemLongClickListener true
        }
        }
    }

