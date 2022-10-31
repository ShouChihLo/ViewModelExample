package com.example.viewmodelexample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private var counter = 0   //without ViewModel (version 1)
   private var lastValue: Int = 0
    private lateinit var viewModel: MainViewModel
    //private val viewModel: MainViewModel by viewModels { MainViewModelFactory(lastValue) }
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //using shared preferences
        sp = getPreferences(Context.MODE_PRIVATE)
        lastValue = sp.getInt("counter", 0)
        viewModel = ViewModelProvider(this, MainViewModelFactory(lastValue)).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this  //for activity
        //without viewmodel (version 1)
//        binding.addButton.setOnClickListener {
//            counter = counter + 1
//            binding.textView.text = counter.toString()
//        }

        //with viewmodel  (version 2)
//        binding.addButton.setOnClickListener {
//            viewModel.plusOne()
//        binding.textView.text = viewModel.counter.toString()
//        }

        //with viewmodel and livedata (version 3)
        //binding.addButton.setOnClickListener { viewModel.plusOne() }
        //binding.resetButton.setOnClickListener { viewModel.reset() }
        //add LiveData observer (without combining the data binding)
//       viewModel.counter.observe(this) {count ->
//           binding.textView.text = count.toString()
//       }
    }

    override fun onStop() {
        super.onStop()
        sp.edit()
            .putInt("counter",viewModel.counter.value!!)  //forced writing
            .apply()
    }

}