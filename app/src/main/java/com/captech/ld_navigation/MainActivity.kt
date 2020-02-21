package com.captech.ld_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.captech.ld_navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel


        //Activity Samples

        viewModel.activityEventSample.observe(this) {
            startActivity(it.buildIntent(this))
        }

        viewModel.functionActivitySample.observe(this) {
            it(this)
        }


        //Fragment Samples

        viewModel.fragmentEventSample.observe(this) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
                .replace(R.id.fragmentContainer, it.buildFragment(), it.tag)
                .commit()
        }

        viewModel.functionFMSample.observe(this) {
            it(supportFragmentManager)
        }
    }
}
