package com.example.serenity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvJournal: RecyclerView
    private var list = ArrayList<Journal>()
    lateinit var imgList: Array<Int>
    lateinit var nameList: Array<String>
    lateinit var descList: Array<String>
    private var toolbar: androidx.appcompat.widget.Toolbar? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        imgList = arrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10
        )

        nameList = resources.getStringArray(R.array.data_name)
        descList = resources.getStringArray(R.array.data_description)

        rvJournal = findViewById(R.id.rv_journal)
        rvJournal.layoutManager = LinearLayoutManager(this)
        rvJournal.setHasFixedSize(true)

        getData()

        rvJournal.adapter = ListJournalAdapter(list) { selectedJournal ->
            val intent = Intent(this, DetailJournalActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, selectedJournal)
            startActivity(intent)
        }
    }

    private fun getData() {
        for (i in imgList.indices) {
            val journal = Journal(nameList[i], descList[i], imgList[i])
            list.add(journal)
        }

    }

    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            R.id.action_share -> {
                shareAppInfo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareAppInfo() {
        val shareText = "Check out the Serenity app!"
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(intent, "Share via"))
    }

}
