package com.captech.ld_navigation.event

import android.os.Bundle
import androidx.fragment.app.Fragment

class FragmentEvent(clazz: Class<*>, bundle: Bundle?, val tag: String = clazz.simpleName) :
    NavEvent(clazz, bundle) {

    fun buildFragment(): Fragment {
        return (clazz.getConstructor().newInstance() as Fragment).apply {
            bundle?.let {
                this.arguments = bundle
            }
        }
    }
}