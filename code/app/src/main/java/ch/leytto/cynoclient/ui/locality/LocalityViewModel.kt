package ch.leytto.cynoclient.ui.locality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocalityViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is locality Fragment"
    }
    val text: LiveData<String> = _text
}