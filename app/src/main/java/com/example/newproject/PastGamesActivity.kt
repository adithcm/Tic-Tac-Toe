package com.example.newproject

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.newproject.database.SQLiteHelper
import com.example.newproject.databinding.ActivityPastGamesBinding

class PastGamesActivity : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var binding: ActivityPastGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPastGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = SQLiteHelper(this)

        val pastGames = dbHelper.getAllGames().map {
            "${it.date}: ${it.winner} (Difficulty: ${it.difficulty})"
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pastGames)
        binding.listViewGames.adapter = adapter
    }
}
