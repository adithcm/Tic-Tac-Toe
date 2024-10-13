package com.example.newproject

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.newproject.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("game_settings", MODE_PRIVATE)

        // set default difficlty to hard, if preference not set
        val savedDifficulty = preferences.getString("difficulty", "Hard")

        // set difficulty
        when (savedDifficulty) {
            "Easy" -> binding.radioEasy.isChecked = true
            "Medium" -> binding.radioMedium.isChecked = true
            else -> binding.radioHard.isChecked = true
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        // save difficulty in preferences
        binding.radioGroupDifficulty.setOnCheckedChangeListener { group, checkedId ->
            val selectedDifficulty = findViewById<RadioButton>(checkedId).text.toString()
            preferences.edit().putString("difficulty", selectedDifficulty).apply()
        }
    }
}
