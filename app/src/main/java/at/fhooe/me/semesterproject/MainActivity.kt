package at.fhooe.me.semesterproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.amadeus.android.Amadeus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.search_btn).setOnClickListener {
            var app = application as AccessAPI;

            val getText = findViewById<EditText>(R.id.inputText)
            app.country_code = getText.text.toString()

            app.get()

            val intent = Intent(this, CountryActivity::class.java)
            startActivity(intent)

        }
    }

}