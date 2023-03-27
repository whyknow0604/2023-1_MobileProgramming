package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val countries = mutableListOf<String>(
        "Afghanistan","Albania","Algeria","American Samoa", "Andorra",
        "Angola", "Anguilla","Antarctica","Argentina",
        "Armenia","Aruba","Australia","Austria","Azerbaijan",
        "Bahrain","Bangladesh","Barbaods","Belalus","Belgium")


    lateinit var countries2 : Array<String>

    lateinit var adapter1: ArrayAdapter<String>
    lateinit var adapter2: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        countries2 = resources.getStringArray(R.array.countries_array)
        adapter1 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)
        adapter2 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries2)

        binding.autoCompleteTextView.setAdapter(adapter1)
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l, ->
            val item = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(this, "선택된 나라 : $item", Toast.LENGTH_SHORT).show()
        }

        binding.multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        binding.multiAutoCompleteTextView.setAdapter(adapter2)

        binding.editText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                val str = s.toString()
                binding.button.isEnabled = str.isNotEmpty()
            }

        })

        binding.button.setOnClickListener{
            adapter1.add(binding.editText.text.toString())
            adapter1.notifyDataSetChanged()
            binding.editText.text.clear()
        }
    }



}