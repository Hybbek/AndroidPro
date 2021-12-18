package at.fhooe.me.semesterproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amadeus.android.Amadeus
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.search_btn).setOnClickListener {
            val pickerText = findViewById<com.hbb20.CountryCodePicker>(R.id.countryCode_picker).selectedCountryNameCode


            loadCountry(pickerText)
        }
    }



    fun loadCountry(country_code: String) {
        scope.launch {
            try {

                (application as AccessAPI).load(country_code)
                startCountryActivity()

            } catch (e: HttpException) {

                sendToast("Invalid Country Code")
            }

        }
    }

    fun startCountryActivity() {
        val intent = Intent(this, CountryActivity::class.java)
        startActivity(intent)
    }

    fun sendToast(msg: String) {
        this.runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

}