package com.captech.ld_navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.captech.ld_navigation.demo_classes.SampleActivityOne
import com.captech.ld_navigation.demo_classes.SampleFragmentOne
import com.captech.ld_navigation.event.ActivityEvent
import com.captech.ld_navigation.event.FragmentEvent
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = MainViewModel()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testLaunchActivityEventSample() {
        var eventReceived: ActivityEvent? = null

        val observer = Observer<ActivityEvent> {
            eventReceived = it
        }

        viewModel.activityEventSample.observeForever(observer)

        mockkConstructor(Bundle::class)
        every {
            anyConstructed<Bundle>()
                .putString(any(), any())
        } returns Unit

        viewModel.launchActivityEventSample()

        verify {
            anyConstructed<Bundle>()
                .putString("arg1", "value1")
        }

        assert(eventReceived?.clazz == SampleActivityOne::class.java)

        viewModel.activityEventSample.removeObserver(observer)
    }

    @Test
    fun testLaunchFunctionActivitySample() {
        val mainActivity = mockk<MainActivity>()
        val observer = Observer<(arg: Activity) -> Unit> { it(mainActivity) }

        viewModel.functionActivitySample.observeForever(observer)

        mockkConstructor(Intent::class)

        every {
            mainActivity.startActivity(any())
        } returns Unit

        viewModel.launchFunctionActivitySample()

        verify {
            mainActivity.startActivity(any())
        }


        viewModel.functionActivitySample.removeObserver(observer)
    }

    @Test
    fun testLaunchFragmentEventSample() {
        var eventReceived: FragmentEvent? = null

        val observer = Observer<FragmentEvent> {
            eventReceived = it
        }

        viewModel.fragmentEventSample.observeForever(observer)

        mockkConstructor(Bundle::class)
        every {
            anyConstructed<Bundle>()
                .putString(any(), any())
        } returns Unit

        viewModel.launchFragmentEventSample()

        verify {
            anyConstructed<Bundle>()
                .putString("arg1", "value1")
        }

        assert(eventReceived?.clazz == SampleFragmentOne::class.java)

        viewModel.fragmentEventSample.removeObserver(observer)
    }


    @Test
    fun testFireGenericEvent() {
        val observer = mockk<Observer<Void>>(relaxed = true)
        viewModel.genericEventSample.observeForever(observer)

        viewModel.fireGenericEvent()

        verify {
            observer.onChanged(any())
        }

        viewModel.genericEventSample.removeObserver(observer)
    }

    @Test
    fun testShowTimeStamp() {
        val observer = mockk<Observer<Long>>(relaxed = true)
        viewModel.timeEventSample.observeForever(observer)

        viewModel.showTimeStamp()

        verify {
            observer.onChanged(any())
        }

        viewModel.timeEventSample.removeObserver(observer)
    }

}