package ch.leytto.cynoclient.db.dao

import androidx.room.*
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocality
import ch.leytto.cynoclient.db.entities.relations.DogWithBreed
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {


    @Query("SELECT * FROM dogs")
    fun getDogs(): Flow<List<Dog>>

    @Query("SELECT * FROM dogs WHERE id = :id")
    suspend fun getDog(id: Int): Dog

    @Query("SELECT * FROM dogs WHERE id = :id")
    suspend fun getById(id: String?): DogWithBreed

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dog: Dog)

    @Update
    suspend fun updateDogs(vararg dogs: Dog)

    @Delete
    suspend fun deleteDogs(vararg dogs: Dog)
}