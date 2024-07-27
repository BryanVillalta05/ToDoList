package com.example.aaaa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val tasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addTaskbtn: Button = findViewById(R.id.newbtn)
        listView = findViewById(R.id.list)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, tasks)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        addTaskbtn.setOnClickListener {
            val intent = Intent(this, newTask::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        val removeButton: Button = findViewById(R.id.removebtn)
        removeButton.setOnClickListener {
            val checkedItems = listView.checkedItemPositions
            for (i in (listView.count - 1) downTo 0) {
                if (checkedItems.get(i)) {
                    tasks.removeAt(i)
                }
            }
            adapter.notifyDataSetChanged()
            listView.clearChoices()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val taskName = data?.getStringExtra("TASK_NAME")
            val taskDesc = data?.getStringExtra("TASK_DESC")
            if (!taskName.isNullOrEmpty() && !taskDesc.isNullOrEmpty()) {
                tasks.add("$taskName: $taskDesc")
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val REQUEST_CODE = 1
    }
}
