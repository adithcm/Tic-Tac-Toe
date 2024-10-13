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

        // initialize sqlitehelper to access db
        dbHelper = SQLiteHelper(this)

        val pastGames = dbHelper.getAllGames().map {
            "${it.date}: ${it.winner} (Difficulty: ${it.difficulty})"
        }

        // create adapter to display list of past games in listview
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pastGames)

        // set adapter to list view to see all past games
        binding.listViewGames.adapter = adapter
    }
}
