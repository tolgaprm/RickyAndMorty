package comprmto.rickyandmorty.presentation.character

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import comprmto.rickyandmorty.R
import comprmto.rickyandmorty.databinding.ActivityCharacterBinding
import comprmto.rickyandmorty.presentation.episode.EpisodeActivity
import comprmto.rickyandmorty.util.BottomNavigationHelper

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_RickyAndMorty)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()


    }

    private fun setupNavigation() {
        val bottomNavBar = binding.bottomNavigationBar
        BottomNavigationHelper.setupNavigation(this, bottomNavBar)

        val menu = bottomNavBar.menu
        val menuItem = menu[0]
        menuItem.isChecked = true
        menuItem.setIcon(R.drawable.ic_character_selected)


    }
}