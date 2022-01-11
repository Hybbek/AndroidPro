package at.fhooe.me.semesterproject

import android.app.Application
import android.util.Log
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlin.properties.Delegates

class AccessAPI: Application() {

    private lateinit var amadeus: Amadeus
    lateinit var covidRestrictions: CovidRestrictions

    override fun onCreate() {
        super.onCreate()
        amadeus = Amadeus.Builder(context = this)
            .setCustomAppIdAndVersion("com.amadeus.android.demo", "1.3.0")
            .setClientId("ozRvdBqRazWhWXH8c0aVNAJlKmyo0eCD")
            .setClientSecret("yb8cDE5aJHtoF0iM")
            .build()
    }

    suspend fun load(country_code: String) {
             val res = amadeus.get("v1/duty-of-care/diseases/covid19-area-report?countryCode=${country_code.uppercase()}")
             Log.d(this.javaClass.simpleName, "requestApi = ${res.toString()}")
             covidRestrictions = Gson().fromJson(res.toString(), CovidRestrictions::class.java)
             Log.d(javaClass.simpleName, "Converted Object ${covidRestrictions}")
    }

    fun get() : CovidRestrictions{
        return covidRestrictions
    }
}