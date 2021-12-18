package at.fhooe.me.semesterproject

import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.math.round
import kotlin.math.roundToInt


class CountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_country)


        val flagArr = intent.extras?.getByteArray("flag")
        flagArr?.let {
            val img = BitmapFactory.decodeByteArray(flagArr, 0, flagArr.size)
            findViewById<ImageView>(R.id.flag).setImageBitmap(img)
        }


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


            //Summary
            val titleSummary = "COVID COUNTRY SUMMARY"
            findViewById<TextView>(R.id.title_summary).text = titleSummary

            val summary = covidRestriction.data.summary
            val htmlSummary = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT) // Bei paar Felder bekommen wir <p> zurück (HTML code) -> unschön


            findViewById<TextView>(R.id.result_summary).text = htmlSummary  //result = id von TextView

            val titleStats = "STATISTIC"
            findViewById<TextView>(R.id.title_statistics).text = titleStats


            // Table init
            val riskLevel = covidRestriction.data?.diseaseRiskLevel
            findViewById<TextView>(R.id.result_risklevel).text = riskLevel

            val confirmed_cases = covidRestriction.data.diseaseCases?.confirmed
            findViewById<TextView>(R.id.result_confirmed_cases).text = confirmed_cases.toString()
            val confirmed_deaths = covidRestriction.data.diseaseCases?.deaths
            findViewById<TextView>(R.id.result_confirmed_deaths).text = confirmed_deaths.toString()

            val vaccinated_one_dose = covidRestriction.data.areaVaccinated?.get(0)?.percentage

            if (vaccinated_one_dose != null) {
                findViewById<ProgressBar>(R.id.progressBar_oneDose).progress = vaccinated_one_dose.toInt()
            }

            val vaccinated_oneDose_round = vaccinated_one_dose?.let { Math.round(it) }
            val percentage = vaccinated_oneDose_round.toString() + " %"
            val pBar = findViewById<ProgressBar>(R.id.progressBar_oneDose)

            vaccinated_one_dose?.let {
                val color = when {
                    it < 80.0 -> Color.RED
                    it >= 80.0 -> resources.getColor(R.color.android_green)
                    else -> 0xfff
                }
                pBar.progressTintList = ColorStateList.valueOf(color)
                pBar.progressBackgroundTintList = ColorStateList.valueOf(color)
            }

            findViewById<TextView>(R.id.result_vaccination_one_dose).text = percentage
            var vaccinated_fully = covidRestriction.data.areaVaccinated?.get(1)?.percentage

            if (vaccinated_fully != null) {
                findViewById<ProgressBar>(R.id.progressBar_fully).progress = vaccinated_fully.toInt()
            }

            val pBar2 = findViewById<ProgressBar>(R.id.progressBar_fully)

            vaccinated_fully?.let {
                val color = when {
                    it < 80.0 -> Color.RED
                    it >= 80.0 -> resources.getColor(R.color.android_green)
                    else -> 0xfff
                }
                pBar2.progressTintList = ColorStateList.valueOf(color)
                pBar2.progressBackgroundTintList = ColorStateList.valueOf(color)
            }

            val vaccinated_fully_round = vaccinated_fully?.let { Math.round(it) }
            val percentage2 = vaccinated_fully_round.toString() + " %"
            findViewById<TextView>(R.id.result_vaccinated_fully).text = percentage2


            // Area Policy
            val areaPolicyTitel = "COUNTRY POLICY"
            findViewById<TextView>(R.id.title_area_policy).text = areaPolicyTitel



            var areaPolicyText = covidRestriction.data.areaPolicy?.text
            if(areaPolicyText == null) areaPolicyText = "NO INFORMATION"
            areaPolicyText = "Area Policy: $areaPolicyText"

            val htmlAreaPolicyText = Html.fromHtml(areaPolicyText, Html.FROM_HTML_MODE_COMPACT)
            findViewById<TextView>(R.id.result_area_policy_text).text = htmlAreaPolicyText

            var maskRequired = covidRestriction.data.areaAccessRestriction?.mask?.text
            if(maskRequired == null) maskRequired = "NO INFORMATION"
            maskRequired = "Mask regulation: $maskRequired"
            val htmlMaskRequired = Html.fromHtml(maskRequired, Html.FROM_HTML_MODE_COMPACT)
            findViewById<TextView>(R.id.result_mask_text).text = htmlMaskRequired


            // Entry Policy
            val titleEntryPolicy = "ENTRY RESTRICTION"
            findViewById<TextView>(R.id.title_entry_policy).text = titleEntryPolicy

            val entryPolicy = covidRestriction.data.areaAccessRestriction?.entry?.text
            val htmlEntryPolicy = Html.fromHtml(entryPolicy, Html.FROM_HTML_MODE_COMPACT)
            findViewById<TextView>(R.id.result_entry_policy).text = htmlEntryPolicy

            //Links
            var webLinks = covidRestriction.data.dataSources?.covidDashboardLink
            var webLinks2 = covidRestriction.data.dataSources?.governmentSiteLink

            if(webLinks == null) webLinks = "";
            if(webLinks2 == null) webLinks2 = "";

            val weblinks_concat = webLinks + "\n" + webLinks2
            findViewById<TextView>(R.id.web_link).text = weblinks_concat
        }
    }
}