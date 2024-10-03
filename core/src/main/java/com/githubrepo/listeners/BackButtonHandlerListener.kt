package com.githubrepo.listeners

interface BackButtonHandlerListener {
    fun addBackpressListener(listener: BackPressListener)
    fun removeBackpressListener(listener: BackPressListener)
}