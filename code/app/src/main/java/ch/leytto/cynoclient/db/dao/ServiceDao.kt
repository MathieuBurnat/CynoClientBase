package ch.leytto.cynoclient.db.dao

import androidx.room.*
import ch.leytto.cynoclient.db.entities.Service
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {


    @Query("SELECT * FROM services")
    fun getServices(): Flow<List<Service>>

    @Query("SELECT * FROM services WHERE id = :id")
    suspend fun getService(id: Int): Service

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(services: Service)

    @Update
    suspend fun updateServices(vararg services: Service)

    @Delete
    suspend fun deleteServices(vararg services: Service)
}