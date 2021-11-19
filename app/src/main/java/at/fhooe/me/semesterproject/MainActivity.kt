package at.fhooe.me.semesterproject

import android.accounts.AccountManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var inputText : String = "";
    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)

    private lateinit var amadeus: Amadeus


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*amadeus = Amadeus.Builder(context = this)
            .setCustomAppIdAndVersion("com.amadeus.android.demo", "1.3.0")
            .setClientId("lrlm8XAjaYdJHlNnaRTu2sgpnbjBxxY3")
            .setClientSecret("AxCzH8vHVbZRsOOL")
            .build()
        scope.launch {
            when (val checkinLinks = amadeus.referenceData.urls.checkinLinks.get(airlineCode = "LH")) {
                is ApiResult.Success -> {
                    Log.i("Result", "Data ${checkinLinks.data}")
                }
                is ApiResult.Error -> {
                    Log.i("Result", "Error ${checkinLinks.errors}")
                }
            }
        }*/
        findViewById<Button>(R.id.button).setOnClickListener {
            var app = application as SampleApplication;
            app.get()
        }
    }

    fun editText(view: View){
        val getText = findViewById<EditText>(R.id.inputText)
        inputText = getText.text.toString()
    }




}