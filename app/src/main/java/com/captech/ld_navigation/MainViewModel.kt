package com.captech.ld_navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.captech.ld_navigation.demo_classes.SampleActivityOne
import com.captech.ld_navigation.demo_classes.SampleActivityTwo
import com.captech.ld_navigation.demo_classes.SampleFragmentOne
import com.captech.ld_navigation.demo_classes.SampleFragmentTwo
import com.captech.ld_navigation.event.ActivityEvent
import com.captech.ld_navigation.event.FragmentEvent
import com.captech.ld_navigation.ld.SingleLiveEvent
import com.captech.ld_navigation.ld.SingleLiveFunction

class MainViewModel : ViewModel() {

    val activityEventSample = SingleLiveEvent<ActivityEvent>()
    val functionActivitySample = SingleLiveFunction<Activity>()

    val fragmentEventSample =
        SingleLiveEvent<FragmentEvent>()
    val functionFMSample =
        SingleLiveFunction<FragmentManager>()


    fun launchActivityEventSample() {
        val bundle = Bundle()
        bundle.putString("arg1", "value1")
        activityEventSample.value =
            ActivityEvent(
                SampleActivityOne::class.java,
                bundle
            )
    }

    fun launchFunctionActivitySample() {
        functionActivitySample.setValue { mainActivity ->
            mainActivity.startActivity(Intent(mainActivity, SampleActivityTwo::class.java))
        }
    }


    fun launchFragmentEventSample() {
        val bundle = Bundle()
        bundle.putString("arg1", "value1")
        fragmentEventSample.value =
            FragmentEvent(
                SampleFragmentOne::class.java,
                bundle,
                "tag"
            )
    }


    fun launchFunctionFMSample() {
        functionFMSample.setValue {
            val frag = SampleFragmentTwo()
            val args = Bundle()
            args.putString("arg1", "value1")
            frag.arguments = args

            val transaction = it.beginTransaction()
            transaction.addToBackStack(null)
                .replace(R.id.fragmentContainer, frag)
                .commit()
        }

    }


}