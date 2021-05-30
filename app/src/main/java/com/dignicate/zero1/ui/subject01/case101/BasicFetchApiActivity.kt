package com.dignicate.zero1.ui.subject01.case101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dignicate.zero1.R

class BasicFetchApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_fetch_api_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BasicFetchApiFragment.newInstance())
                .commitNow()
        }
    }
}