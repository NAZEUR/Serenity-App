package com.example.serenity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat


class DetailJournalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_journal)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val journal = intent.getParcelableExtra<Journal>(MainActivity.INTENT_PARCELABLE)


        val photo = findViewById<ImageView>(R.id.imageViewDetail)
        val name = findViewById<TextView>(R.id.TittleJournal)
        val description = findViewById<TextView>(R.id.textDescDetail)


        photo.setImageResource(journal?.photo ?: R.drawable.img4)
        name.text = journal?.name ?: "No Title"
        description.text = journal?.description ?: "No Description"

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_share -> {val journal = intent.getParcelableExtra<Journal>(MainActivity.INTENT_PARCELABLE)
                val shareIntent = ShareCompat.IntentBuilder(this)
                    .setType("text/plain")
                    .setText("Check out this journal: ${journal?.name}")
                    .intent
                startActivity(Intent.createChooser(shareIntent, "Share via"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


