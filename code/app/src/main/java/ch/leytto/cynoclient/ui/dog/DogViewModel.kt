package ch.leytto.cynoclient.ui.dog

import androidx.lifecycle.*
import androidx.room.ColumnInfo
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocality
import ch.leytto.cynoclient.db.entities.relations.DogWithBreed
import ch.leytto.cynoclient.model.DogRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

class DogViewModel(private val repository: DogRepository) : ViewModel() {
    val allDogs: LiveData<List<Dog>> =repository.allDogs.asLiveData()

    private var _noun = MutableStateFlow("")
    private var _female = MutableStateFlow(false)
    private var _sterilized = MutableStateFlow(false)
    private var _chemical = MutableStateFlow(false)
    private var _color = MutableStateFlow("")
    private var _breed = MutableStateFlow("")

    fun setNoun(noun: String){
        this._noun.value = noun
    }
    fun setFemale(female: Boolean){
        this._female.value = female
    }
    fun setSterilized (sterilized : Boolean){
        this._sterilized.value = sterilized
    }
    fun setChemical (chemical : Boolean){
        this._chemical.value = chemical
    }
    fun setColor (color : String){
        this._color.value = color
    }
    fun setBreed (breed : String){
        this._breed.value = breed
    }

    suspend fun insert(dog: Dog){
        viewModelScope.launch {
            repository.insert(dog)
        }
    }

    /**
     * Get a dog by it's id with his breed
     */
    suspend fun getById(id: String) : DogWithBreed {
        return repository.getById(id)
    }
}