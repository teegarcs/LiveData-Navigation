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
import com.captech.ld_navigation.ld.FunctionLiveEvent
import com.captech.ld_navigation.ld.NavLiveEvent
import com.captech.ld_navigation.ld.SingleLiveEvent

class MainViewModel : ViewModel() {

    val activityEventSample = NavLiveEvent<ActivityEvent>()
    val functionActivitySample = FunctionLiveEvent<Activity>()

    val fragmentEventSample = NavLiveEvent<FragmentEvent>()
    val functionFMSample = FunctionLiveEvent<FragmentManager>()

    val genericEventSample = SingleLiveEvent<Void>()
    val timeEventSample = SingleLiveEvent<Long>()
    /*
    Activity Samples
     */

    /**
     * Launch an Activity via the NavLiveEvent. This will leverage the ActivityEvent.
     * This event takes in the Activity class and data so that the Observer
     * can simply launch the Activity without having to contain logic of its own.
     */
    fun launchActivityEventSample() {
        val bundle = Bundle()
        bundle.putString("arg1", "value1")
        activityEventSample.value =
            ActivityEvent(
                SampleActivityOne::class.java,
                bundle
            )
    }

    /**
     * Launch an Activity via the FunctionLiveEvent.
     *
     * We will provide a function here that we want our observer to call when hit.
     * This will be specifically useful when we need a specific argument such as the
     * Activity instance, like used in this case. Any argument passed into the VM
     * via the function should not be held onto beyond the function's scope
     * to avoid leaks.
     */
    fun launchFunctionActivitySample() {
        functionActivitySample.setValue { mainActivity ->
            mainActivity.startActivity(Intent(mainActivity, SampleActivityTwo::class.java))
        }
    }


    /*
    Fragment Samples
     */


    /**
     * Launch a fragment via the NavLiveEvent. This will leverage the FragmentEvent.
     * This event takes in the Fragment class, data, and tags so that the Observer
     * can simply show the Fragment without having to contain logic of its own.
     */
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


    /**
     * Launch a Fragment via the FunctionLiveEvent.
     *
     * We will provide a function here that we want our observer to call when hit.
     * This will be specifically useful when we need a specific argument such as the
     * FragmentManager, like used in this case. Any argument passed into the VM
     * via the function should not be held onto beyond the function's scope
     * to avoid leaks
     */
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


    /*
    Generic Event Samples
     */


    /**
     * Example of firing off generic notification to something on the UI.
     * The Observer won't receive anything, this would be similar to a
     * button press being called directly
     */
    fun fireGenericEvent() {
        genericEventSample.call()
    }

    /**
     * Example of us firing an event with a message for one time consumption.
     * This will deliver a Long, but could be set to deliver any Object.
     */
    fun showTimeStamp() {
        timeEventSample.value = System.currentTimeMillis()
    }


}