package ch.leytto.cynoclient.model

import androidx.annotation.WorkerThread
import ch.leytto.cynoclient.db.dao.ReportDao
import ch.leytto.cynoclient.db.entities.Consultation
import kotlinx.coroutines.flow.Flow

class ReportRepository(private val reportDao: ReportDao) : AbstractRepository() {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allReports: Flow<List<Consultation>> = reportDao.getReports()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(report: Consultation) {
        reportDao.insert(report)
    }
}