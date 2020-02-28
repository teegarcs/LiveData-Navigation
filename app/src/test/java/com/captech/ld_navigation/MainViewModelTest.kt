package com.captech.ld_navigation

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.captech.ld_navigation.demo_classes.SampleActivityOne
import com.captech.ld_navigation.demo_classes.SampleFragmentOne
import com.captech.ld_navigation.event.ActivityEvent
import com.captech.ld_navigation.event.FragmentEvent
import com.captech.ld_navigation.event.NavEvent
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

        val observer = Observer<NavEvent> {
            eventReceived = it as ActivityEvent
        }

        viewModel.navEventSample.observeForever(observer)

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

        viewModel.navEventSample.removeObserver(observer)
    }


    @Test
    fun testLaunchFragmentEventSample() {
        var eventReceived: FragmentEvent? = null

        val observer = Observer<NavEvent> {
            eventReceived = it as FragmentEvent
        }

        viewModel.navEventSample.observeForever(observer)

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

        viewModel.navEventSample.removeObserver(observer)
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