package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Consultation
import ch.leytto.cynoclient.db.entities.Service
import ch.leytto.cynoclient.model.ReportRepository
import ch.leytto.cynoclient.model.ServiceRepository
import kotlinx.coroutines.launch

class ServiceViewModel(private val repository: ServiceRepository) : ViewModel() {

    val AllServices: LiveData<List<Service>> = repository.allServices.asLiveData()

    fun insert(service: Service) = viewModelScope.launch {
        repository.insert(service)
    }

}