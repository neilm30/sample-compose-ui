package com.githubrepo.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.entity.ErrorEntity
import com.githubrepo.utils.NavigationCommand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel(), Observable {


    /**
     * Live data to handle loading
     */
 //   val loadingEvent = SingleLiveEvent<Boolean>()
     val loadingEvent = MutableStateFlow(Boolean)

    /**
     * Live data to handle error
     */
  //  val errorEvent = SingleLiveEvent<ErrorEntity.Error>()
    val errorEvent = MutableStateFlow(ErrorEntity.Error())


    val navigationCommands =
        MutableStateFlow<NavigationCommand>(NavigationCommand.Back)


    private val callbacks = PropertyChangeRegistry()

    /**
     * Extension function for calling api using flow.
     */
    protected fun <T : Any> Flow<T>.track(action: (value: T) -> Unit) =
        viewModelScope.launch {
            collect {
                action(it)
            }
        }

    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    internal fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    internal fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }


    /**
     * Method call to handle error
     */

    fun navigateToId(actionID: Int) {
       // navigationCommands.postValue(NavigationCommand.ToId(actionID))
    }



    fun <T : Any> NetworkResult<T>.response(
        onError: ((error: ErrorEntity.Error?) -> Unit)? = null,
        onSuccess: ((value: T) -> Unit)
    ) {
        when (this) {
            is NetworkResult.Success -> {
                onSuccess(data)
            }
            is NetworkResult.Error -> {
                onError?.let { onError(error) }
            }
            else -> Unit
        }
    }
}
