package com.example.aaaa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class newTask : AppCompatActivity() {
    var nomTask: TextInputEditText?= null
    var descTask: TextInputEditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.newtaskview)
        val addTaskbtn: Button = findViewById(R.id.addbtn)
        nomTask = findViewById(R.id.nombre)
        descTask =  findViewById(R.id.desc)
        addTaskbtn.setOnClickListener {
            var nom:String = nomTask?.text.toString()
            var desc:String = descTask?.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("TASK_NAME", nom)
            resultIntent.putExtra("TASK_DESC", desc)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }
}