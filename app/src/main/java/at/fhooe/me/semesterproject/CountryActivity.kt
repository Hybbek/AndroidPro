package at.fhooe.me.semesterproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


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

            //Summary
            val title = "Covid history summary"
            findViewById<TextView>(R.id.title_summary).text = title

            val summary = covidRestriction.data.summary
            val htmlSummary = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT) // Bei paar Felder bekommen wir <p> zurück (HTML code) -> unschön


            findViewById<TextView>(R.id.result_summary).text = htmlSummary  //result = id von TextView


            //Links
            val webLinks = covidRestriction.data.dataSources?.covidDashboardLink
            val webLinks2 = covidRestriction.data.dataSources?.governmentSiteLink
            val weblinks_concat = webLinks + "\n" + webLinks2
            findViewById<TextView>(R.id.web_link).text = weblinks_concat
        }
    }
}