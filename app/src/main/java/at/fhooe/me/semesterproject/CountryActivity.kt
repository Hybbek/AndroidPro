package at.fhooe.me.semesterproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView


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
            val titleSummary = "COVID COUNTRY SUMMARY"
            findViewById<TextView>(R.id.title_summary).text = titleSummary

            val summary = covidRestriction.data.summary
            val htmlSummary = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT) // Bei paar Felder bekommen wir <p> zurück (HTML code) -> unschön


            findViewById<TextView>(R.id.result_summary).text = htmlSummary  //result = id von TextView

            val titleStats = "STATISTIC"
            findViewById<TextView>(R.id.title_statistics).text = titleStats


            // Table init
            val riskLevel = covidRestriction.data.diseaseRiskLevel
            findViewById<TextView>(R.id.result_risklevel).text = riskLevel

            val confirmed_cases = covidRestriction.data.diseaseCases?.confirmed
            findViewById<TextView>(R.id.result_confirmed_cases).text = confirmed_cases.toString()
            val confirmed_deaths = covidRestriction.data.diseaseCases?.deaths
            findViewById<TextView>(R.id.result_confirmed_deaths).text = confirmed_deaths.toString()
            val vaccinated_one_dose = covidRestriction.data.areaVaccinated?.get(0)?.percentage
            val percentage = vaccinated_one_dose.toString() + " %"
            findViewById<TextView>(R.id.result_vaccination_one_dose).text = percentage
            val vaccinated_fully = covidRestriction.data.areaVaccinated?.get(1)?.percentage
            val percentage2 = vaccinated_fully.toString() + " %"
            findViewById<TextView>(R.id.result_vaccinated_fully).text = percentage2


            // Area Policy
            val areaPolicyTitel = "COUNTRY POLICY"
            findViewById<TextView>(R.id.title_area_policy).text = areaPolicyTitel



            val areaPolicyText = covidRestriction.data.areaPolicy?.text
            val htmlAreaPolicyText = Html.fromHtml(areaPolicyText, Html.FROM_HTML_MODE_COMPACT)
            findViewById<TextView>(R.id.result_area_policy_text).text = htmlAreaPolicyText

            val maskRequired = covidRestriction.data.areaAccessRestriction?.mask?.text
            val htmlMaskRequired = Html.fromHtml(maskRequired, Html.FROM_HTML_MODE_COMPACT)
            findViewById<TextView>(R.id.result_mask_text).text = htmlMaskRequired


            // Entry Policy
            val titleEntryPolicy = "ENTRY RESTRICTION"
            findViewById<TextView>(R.id.title_entry_policy).text = titleEntryPolicy

            val entryPolicy = covidRestriction.data.areaAccessRestriction?.entry?.text
            val htmlEntryPolicy = Html.fromHtml(entryPolicy, Html.FROM_HTML_MODE_COMPACT)
            findViewById<TextView>(R.id.result_entry_policy).text = htmlEntryPolicy

            //Links
            val webLinks = covidRestriction.data.dataSources?.covidDashboardLink
            val webLinks2 = covidRestriction.data.dataSources?.governmentSiteLink
            val weblinks_concat = webLinks + "\n" + webLinks2
            findViewById<TextView>(R.id.web_link).text = weblinks_concat
        }
    }
}