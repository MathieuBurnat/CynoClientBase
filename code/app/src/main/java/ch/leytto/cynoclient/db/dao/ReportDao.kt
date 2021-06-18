package ch.leytto.cynoclient.db.dao

import androidx.room.*
import ch.leytto.cynoclient.db.entities.Consultation
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {


    @Query("SELECT * FROM consultations")
    fun getReports(): Flow<List<Consultation>>

    @Query("SELECT * FROM consultations WHERE id = :id")
    suspend fun getReport(id: Int): Consultation

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reports: Consultation)

    @Update
    suspend fun updateReports(vararg reports: Consultation)

    @Delete
    suspend fun deleteReports(vararg reports: Consultation)
}