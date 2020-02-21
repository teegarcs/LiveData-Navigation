package com.captech.ld_navigation.event

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class ActivityEvent(clazz: Class<*>, bundle: Bundle?) : NavEvent(clazz, bundle) {
    fun buildIntent(activity: Activity): Intent {
        return Intent(activity, clazz).apply {
            bundle?.let {
                putExtras(it)
            }
        }
    }
}