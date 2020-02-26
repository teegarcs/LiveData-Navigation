package com.captech.ld_navigation.ld

import com.captech.ld_navigation.event.NavEvent


class NavLiveEvent<T> : SingleLiveEvent<T>() where T : NavEvent
