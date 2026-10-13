package edu.temple.dicethrow

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fragment attached as a background worker gets a tag instead of a location id, findFragmentByTag
        //can replace "== null" with "!is DieFragment"
        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null) //if no fragment exists yet ->
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment())
                .commit()
    }
}