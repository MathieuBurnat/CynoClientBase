package ch.leytto.cynoclient.model

import androidx.annotation.WorkerThread
import ch.leytto.cynoclient.db.dao.DogDao
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.relations.DogWithBreed
import kotlinx.coroutines.flow.Flow

class DogRepository(private val dogDao: DogDao) : AbstractRepository() {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allDogs: Flow<List<Dog>> = dogDao.getDogs()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(dog: Dog) {
        dogDao.insert(dog)
    }
    /**
     * Get a dog by it's id with his breed
     */
    suspend fun getById(id: String): DogWithBreed {
        return dogDao.getById(id)
    }
}