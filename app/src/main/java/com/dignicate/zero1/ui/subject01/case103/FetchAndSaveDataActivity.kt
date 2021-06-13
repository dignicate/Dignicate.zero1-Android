package com.dignicate.zero1.ui.subject01.case103

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dignicate.zero1.R

class FetchAndSaveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetch_and_save_data_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FetchAndSaveDataFragment.newInstance())
                .commitNow()
        }
    }
}