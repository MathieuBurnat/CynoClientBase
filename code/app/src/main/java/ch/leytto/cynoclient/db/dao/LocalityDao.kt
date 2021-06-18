package ch.leytto.cynoclient.db.dao

import androidx.room.*
import ch.leytto.cynoclient.db.entities.Locality
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalityDao {


    @Query("SELECT * FROM localities")
    fun getLocalities(): Flow<List<Locality>>

    @Query("SELECT * FROM localities WHERE id = :id")
    suspend fun getLocality(id: Int): Locality

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(localities: Locality)

    @Update
    suspend fun updateLocalities(vararg localities: Locality)

    @Delete
    suspend fun deleteLocalities(vararg localities: Locality)
}