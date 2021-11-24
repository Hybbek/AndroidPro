package at.fhooe.me.semesterproject

import android.app.Application
import android.util.Log
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*

class AccessAPI: Application() {
    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)

    private lateinit var amadeus: Amadeus

    var country_code = ""


    override fun onCreate() {
        super.onCreate()
        amadeus = Amadeus.Builder(context = this)
            .setCustomAppIdAndVersion("com.amadeus.android.demo", "1.3.0")
            .setClientId("lrlm8XAjaYdJHlNnaRTu2sgpnbjBxxY3")
            .setClientSecret("AxCzH8vHVbZRsOOL")
            .build()

        //get()
    }


    fun get(){

         scope.launch {
             try {

                 val res = amadeus.get("v1/duty-of-care/diseases/covid19-area-report?countryCode=${country_code.uppercase()}")
                 Log.d(this.javaClass.simpleName, "requestApi = ${res.toString()}")
                val covidRestrictions = Gson().fromJson(res.toString(), CovidRestrictions::class.java)
                 Log.d(javaClass.simpleName, "Converted Object ${covidRestrictions}")
             } catch (e: Exception) {
                 Log.e(javaClass.simpleName, "Request failed", e)
             }
         }
    }


}