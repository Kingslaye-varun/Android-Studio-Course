package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lemonade.R.id.textView

class MainActivity : AppCompatActivity() {

    // Define constants for each step
    private val SELECT_LEMON = 1
    private val SQUEEZE_LEMON = 2
    private val DRINK_LEMONADE = 3
    private val START_AGAIN = 4

    private var currentStep = SELECT_LEMON
    private var squeezeCount = 0

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)

        // Initialize the UI for the first step
        setUI()

        // Set a click listener for the image
        imageView.setOnClickListener {
            handleTap()
        }
    }

    // Handle tap on the image
    private fun handleTap() {
        when (currentStep) {
            SELECT_LEMON -> {
                currentStep = SQUEEZE_LEMON
                squeezeCount = (2..4).random() // Randomize squeeze count
            }
            SQUEEZE_LEMON -> {
                squeezeCount--
                if (squeezeCount == 0) {
                    currentStep = DRINK_LEMONADE
                }
            }
            DRINK_LEMONADE -> {
                currentStep = START_AGAIN
            }
            START_AGAIN -> {
                currentStep = SELECT_LEMON
            }
        }
        setUI()
    }

    // Update the UI based on the current step
    private fun setUI() {
        when (currentStep) {
            SELECT_LEMON -> {
                textView.text = getString(R.string.tap_lemon_tree)
                imageView.setImageResource(R.drawable.lemon_tree)
                imageView.contentDescription = getString(R.string.lemon_tree_description)
            }
            SQUEEZE_LEMON -> {
                textView.text = getString(R.string.keep_tapping_lemon)
                imageView.setImageResource(R.drawable.lemon_squeeze)
                imageView.contentDescription = getString(R.string.lemon_description)
            }
            DRINK_LEMONADE -> {
                textView.text = getString(R.string.tap_lemonade_to_drink)
                imageView.setImageResource(R.drawable.lemon_drink)
                imageView.contentDescription = getString(R.string.lemonade_description)
            }
            START_AGAIN -> {
                textView.text = getString(R.string.tap_empty_glass)
                imageView.setImageResource(R.drawable.lemon_restart)
                imageView.contentDescription = getString(R.string.empty_glass_description)
            }
        }
    }
}
