package at.fhooe.me.semesterproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class CountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        val result = (application as AccessAPI).get()
        setData(result)
    }

    /*
    ###########################################################
    ############# Alle Daten hier ausgeben ####################
    ###########################################################
     */
    fun setData(covidRestriction: CovidRestrictions) { //hier die ganzen daten den TextViews/Labes etc. zuweisen.
        this.runOnUiThread {

            val country_code = covidRestriction.data.area?.iataCode
            val flag = findViewById<ImageView>(R.id.flag)

            if (country_code != null) {

                if(country_code.uppercase() == "AT") {
                    flag.setImageResource(R.drawable.ic_at) //ic_at ist die Flagge von AT im drawable ordner
                }

            }


            val summary = covidRestriction.data.summary
            val htmlSummary = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT) // Bei paar Felder bekommen wir <p> zurück (HTML code) -> unschön

            findViewById<TextView>(R.id.result).text = htmlSummary  //result = id von TextView
        }
    }
}