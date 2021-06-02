package ch.leytto.cynoclient.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is customer Fragment"
    }
    val text: LiveData<String> = _text
}