package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.leytto.cynoclient.model.*
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: AbstractRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClientViewModel(repository as ClientRepository) as T
        }
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DogViewModel(repository as DogRepository) as T
        }
        if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReportViewModel(repository as ReportRepository) as T
        }
        if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServiceViewModel(repository as ServiceRepository) as T
        }
        if (modelClass.isAssignableFrom(LocalityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocalityViewModel(repository as LocalityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}