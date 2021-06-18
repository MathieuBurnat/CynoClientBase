package ch.leytto.cynoclient.ui.dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

    suspend fun insert(dog: Dog){
        viewModelScope.launch {
            repository.insert(dog)
        }
    }

    /**
     * Get a dog by it's id with his breed
     */
    suspend fun getById(id: String) : DogWithBreed {
        return repository.getById(id)
    }
}