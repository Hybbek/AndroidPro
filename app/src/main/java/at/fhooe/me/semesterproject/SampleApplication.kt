package at.fhooe.me.semesterproject

import android.app.Application
import android.util.Log
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import kotlinx.coroutines.*

class SampleApplication: Application() {
    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)

    private lateinit var amadeus: Amadeus



    override fun onCreate() {
        super.onCreate()
        amadeus = Amadeus.Builder(context = this)
            .setCustomAppIdAndVersion("com.amadeus.android.demo", "1.3.0")
            .setClientId("lrlm8XAjaYdJHlNnaRTu2sgpnbjBxxY3")
            .setClientSecret("AxCzH8vHVbZRsOOL")
            .build()
        get()

    }

    /*companion object {
        lateinit var amadeus: Amadeus
    }*/

    fun get(){
         scope.launch {
             val covidRes = amadeus.get("https://test.api.amadeus.com/v1/duty-of-care/diseases/covid19-area-report?countryCode=US")
             when (val checkinLinks = amadeus.referenceData.urls.checkinLinks.get(airlineCode = "BA")) {
                 is ApiResult.Success -> {
                     Log.i("Result", "${checkinLinks.data}")
                 }
                 is ApiResult.Error -> {
                     Log.i("Result", "${checkinLinks.errors}")
                 }
             }
         }
    }


}