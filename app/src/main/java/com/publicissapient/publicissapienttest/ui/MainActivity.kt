package com.publicissapient.publicissapienttest.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.publicissapient.publicissapienttest.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar);
      //  supportFragmentManager.beginTransaction().replace(R.id.fragmentLayout, listBooksFragment, "listbooks").commit()
    }

}