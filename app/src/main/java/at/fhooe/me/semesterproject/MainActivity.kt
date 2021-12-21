package at.fhooe.me.semesterproject

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.amadeus.android.Amadeus
import com.google.gson.Gson
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import android.content.SharedPreferences




class MainActivity : AppCompatActivity() {

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)
    var string_countries_viewed = ""
    var countries_viewed_counter = 0

    val my_pref_name = "at.fhooe.me.semesterproject"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreference =  getSharedPreferences(my_pref_name, Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()

        //editor.clear()
        //editor.commit()

        string_countries_viewed = sharedPreference.getString("myKey",string_countries_viewed).toString()
        countries_viewed_counter = sharedPreference.getInt("myKey2", countries_viewed_counter).toInt()

        setContentView(R.layout.activity_main)
        findViewById<com.hbb20.CountryCodePicker>(R.id.countryCode_picker)?.setCountryPreference(string_countries_viewed)


        findViewById<Button>(R.id.search_btn).setOnClickListener {
            //get the Country code from chosen country
            val pickerText =
                findViewById<com.hbb20.CountryCodePicker>(R.id.countryCode_picker).selectedCountryNameCode


            if(string_countries_viewed.equals("")) string_countries_viewed =  pickerText + string_countries_viewed
            else string_countries_viewed = pickerText  + "," + string_countries_viewed

            if(countries_viewed_counter > 4) string_countries_viewed = string_countries_viewed.dropLast(3)
            else countries_viewed_counter++

            editor.putString("myKey", string_countries_viewed)
            editor.putInt("myKey2", countries_viewed_counter)
            editor.apply()

            findViewById<com.hbb20.CountryCodePicker>(R.id.countryCode_picker).setCountryPreference(string_countries_viewed)

            loadCountry(pickerText)
        }



    }


    fun loadCountry(country_code: String) {
        scope.launch {
            try {

                /*
                (application as AccessAPI).load(country_code)
                Handler().postDelayed({
                    startCountryActivity()
                    findViewById<SubmitButton>(R.id.search_btn).reset()
                }, 1000)
                */

                (application as AccessAPI).load(country_code)
                startCountryActivity()
                findViewById<Button>(R.id.search_btn)
            } catch (e: HttpException) {

                sendToast("Invalid Country Code")
            }

        }
    }

    fun startCountryActivity() {
        val intent = Intent(this, CountryActivity::class.java)
        val img =
            findViewById<com.hbb20.CountryCodePicker>(R.id.countryCode_picker).imageViewFlag.drawable.toBitmap()
        val baos = ByteArrayOutputStream()
        img.compress(Bitmap.CompressFormat.PNG, 100, baos)
        intent.putExtra("flag", baos.toByteArray())
        startActivity(intent)
    }

    fun sendToast(msg: String) {
        this.runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

}