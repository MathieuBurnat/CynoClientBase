package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.leytto.cynoclient.DogListAdapter
import ch.leytto.cynoclient.viewmodels.DogViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogDetailsActivity : AppCompatActivity() {
    private val dogViewModel: DogViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).dogRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dog_details)

        val ref = this
        lifecycleScope.launch(Dispatchers.IO){
            val dogData = dogViewModel.getById(intent.getStringExtra( "dog_id"))

            ref.runOnUiThread{
                findViewById<EditText>(R.id.dog_detail_name).text = dogData.dog.noun

                val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
                if(dogData.dog.female === true){
                    radioGroup.check(R.id.dog_detail_radio_female)
                }
                else{
                    radioGroup.check(R.id.dog_detail_radio_male)
                }



                findViewById<EditText>(R.id.dog_detail_date_plaint_text).text = dogData.dog.birthdate
                findViewById<Switch>(R.id.dog_detail_sterile_switch_button).text = dogData.dog.sterilized
                findViewById<Switch>(R.id.dog_detail_chimic_switch_button).text = dogData.dog.chemical

            }
        }
    }
}