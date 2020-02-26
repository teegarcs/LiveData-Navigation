package com.captech.ld_navigation.ld

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


class FunctionLiveEvent<T> : MutableLiveData<(arg: T) -> Unit>() {
    private val mPending = AtomicBoolean(false)


    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in (arg: T) -> Unit>) {
        // Observe the internal MutableLiveData
        super.observe(owner, Observer {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }


    @MainThread
    override fun setValue(t: (arg: T) -> Unit) {
        mPending.set(true)
        super.setValue(t)
    }
}