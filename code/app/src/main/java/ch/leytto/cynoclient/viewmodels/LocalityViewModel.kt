package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Locality
import ch.leytto.cynoclient.model.LocalityRepository
import kotlinx.coroutines.launch

class LocalityViewModel(private val repository: LocalityRepository) : ViewModel() {

    val AllLocalities: LiveData<List<Locality>> = repository.allLocalities.asLiveData()

    fun insert(locality: Locality) = viewModelScope.launch {
        repository.insert(locality)
    }

}