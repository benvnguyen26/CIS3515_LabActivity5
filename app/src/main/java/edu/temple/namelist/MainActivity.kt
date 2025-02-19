package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var names: MutableList<String> // changed to MutableList from List


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf(
            "Kevin Shaply",
            "Stacey Lou",
            "Gerard Clear",
            "Michael Studdard",
            "Michelle Studdard"
        )

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        with(spinner) {
            adapter = CustomAdapter(names, this@MainActivity)
            onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    p0?.run {
                        nameTextView.text = getItemAtPosition(p2).toString()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        findViewById<View>(R.id.deleteButton).setOnClickListener {

            //ensures that spinner updates selection after resetting adapter
            //1. no selection if empty
            //2. if remaining, set selection to index
            if (names.isNotEmpty()) {
                val selectedIndex = spinner.selectedItemPosition

                if (selectedIndex in names.indices) {
                    names.removeAt(selectedIndex)

                    spinner.adapter = CustomAdapter(names, this@MainActivity) // Reset adapter

                    if (names.isNotEmpty()) {
                        spinner.setSelection(0) // Ensure valid selection after deletion
                    }
                }
            }
        }
    }}