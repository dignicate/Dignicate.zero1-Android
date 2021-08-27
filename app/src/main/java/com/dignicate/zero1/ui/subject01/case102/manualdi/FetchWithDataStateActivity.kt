package com.dignicate.zero1.ui.subject01.case102.manualdi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dignicate.zero1.R

class FetchWithDataStateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetch_with_data_state_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FetchWithDataStateFragment.newInstance())
                .commitNow()
        }
    }
}