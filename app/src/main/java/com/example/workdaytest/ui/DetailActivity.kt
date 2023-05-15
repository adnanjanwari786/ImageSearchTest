package com.example.workdaytest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.workdaytest.R
import com.example.workdaytest.data.ItemData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    lateinit var item:ItemData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
    }
    private fun init() {
        try {
            item = intent.extras?.getParcelable("item")!!
            val titleTV = findViewById<TextView>(R.id.titleTextView)
            val descTv = findViewById<TextView>(R.id.descriptionTextView)
            val image = findViewById<ImageView>(R.id.imageView)
            val date = findViewById<TextView>(R.id.dateTextView)
            titleTV.text = item.title
            descTv.text = item.description

            val inputDateString = item.date_created
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

            val formatedDate: Date = inputFormat.parse(inputDateString)
            val shortDateString: String = outputFormat.format(formatedDate)

            date.text =shortDateString
            Glide.with(applicationContext)
                .load(item.imageUrl)
                .into(image)
        }
        catch (e:Exception)
        {e.printStackTrace()}

    }
}