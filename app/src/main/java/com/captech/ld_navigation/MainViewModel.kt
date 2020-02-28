package com.captech.ld_navigation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.captech.ld_navigation.demo_classes.SampleActivityOne
import com.captech.ld_navigation.demo_classes.SampleFragmentOne
import com.captech.ld_navigation.event.ActivityEvent
import com.captech.ld_navigation.event.FragmentEvent
import com.captech.ld_navigation.ld.NavLiveEvent
import com.captech.ld_navigation.ld.SingleLiveEvent

class MainViewModel : ViewModel() {

    val navEventSample = NavLiveEvent()

    val genericEventSample = SingleLiveEvent<Void>()
    val timeEventSample = SingleLiveEvent<Long>()

    /*
    Navigation Samples
     */

    /**
     * Launch an Activity via the NavLiveEvent. This will leverage the ActivityEvent.
     * This event takes in the Activity class and data so that the Observer
     * can simply launch the Activity without having to contain logic of its own.
     */
    fun launchActivityEventSample() {
        val bundle = Bundle()
        bundle.putString("arg1", "value1")
        navEventSample.value =
            ActivityEvent(
                SampleActivityOne::class.java,
                bundle
            )
    }


    /**
     * Launch a fragment via the NavLiveEvent. This will leverage the FragmentEvent.
     * This event takes in the Fragment class, data, and tags so that the Observer
     * can simply show the Fragment without having to contain logic of its own.
     */
    fun launchFragmentEventSample() {
        val bundle = Bundle()
        bundle.putString("arg1", "value1")
        navEventSample.value =
            FragmentEvent(
                SampleFragmentOne::class.java,
                bundle,
                "tag"
            )
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