package com.example.unichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast

class StarRating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_rating)


        val simpleRatingBar = findViewById<RatingBar>(R.id.simpleRatingBar)

        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {

            val rating =simpleRatingBar.rating
            Toast.makeText(this, """Thanks for rating us $rating Stars""".trimIndent(),
                Toast.LENGTH_LONG).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}