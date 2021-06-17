package ch.leytto.cynoclient.viewmodels

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Consultation
import ch.leytto.cynoclient.model.ReportRepository
import kotlinx.coroutines.launch

class ReportViewModel(private val repository: ReportRepository) : ViewModel() {

    val AllReports: LiveData<List<Consultation>> = repository.allReports.asLiveData()

    fun insert(report: Consultation) = viewModelScope.launch {
        repository.insert(report)
    }

}