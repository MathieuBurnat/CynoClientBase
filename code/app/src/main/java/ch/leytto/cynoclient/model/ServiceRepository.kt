package ch.leytto.cynoclient.model

import androidx.annotation.WorkerThread
import ch.leytto.cynoclient.db.dao.ServiceDao
import ch.leytto.cynoclient.db.entities.Service
import kotlinx.coroutines.flow.Flow

class ServiceRepository(private val serviceDao: ServiceDao) : AbstractRepository() {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allServices: Flow<List<Service>> = serviceDao.getServices()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(service: Service) {
        serviceDao.insert(service)
    }
}