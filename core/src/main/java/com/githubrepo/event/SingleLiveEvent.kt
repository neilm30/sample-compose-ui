package com.iiab.mbanking.core.architecture

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.Objects
import java.util.concurrent.atomic.AtomicBoolean

/****
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and SnackBar messages.
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * Note that only one observer is going to be notified of changes.
 * Author: Lajesh Dineshkumar
 * Company:
 * Created on: 2020-03-03
 * Modified on: 2020-03-03
 *****/
open class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Timber.log(Log.ASSERT, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner) { value ->
            if (mPending.compareAndSet(true, false)) {
                /***
                 * handle if value not null because now the observe should not accept null values
                 * ***/
                if (value != null){
                    observer.onChanged(value)
                } else {
                    // Check the class type of T
                    try {
                        observer.onChanged(Unit as T)
                    } catch (e : Exception) {
                        try {
                            observer.onChanged(String() as T)
                        } catch (e : Exception ) {
                            e.cause
                        }
                    }
                }
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {

        private const val TAG = "SingleLiveEvent"
    }
}
