package com.captech.ld_navigation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.captech.ld_navigation.databinding.ActivityMainBinding
import com.captech.ld_navigation.event.ActivityEvent
import com.captech.ld_navigation.event.FragmentEvent
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel

        /*
        Navigation Event Samples
         */

        viewModel.navEventSample.observe(this) {
            if (it is ActivityEvent) {
                startActivity(it.buildIntent(this))
            } else if (it is FragmentEvent) {
                supportFragmentManager.beginTransaction().apply {
                    addToBackStack(null)
                    replace(R.id.fragmentContainer, it.buildFragment(), it.tag)
                    commit()
                }
            }
        }

        /*
        Generic Event Samples
         */

        viewModel.genericEventSample.observe(this) {
            Snackbar
                .make(binding.root, "Generic One Time Event", Snackbar.LENGTH_LONG)
                .show()
        }

        viewModel.timeEventSample.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        }


    }
}
