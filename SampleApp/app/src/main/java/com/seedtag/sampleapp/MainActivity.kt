package com.seedtag.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seedtag.sampleapp.ui_main.MainFragment
import com.seedtag.seedtagads.api.SeedtagAdsManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()

        SeedtagAdsManager.initSeedtagAds(
            baseContext,
            "advertisingIdSample",
            false,
            "iabConsentString",
            "seedtagTokenSample")
        SeedtagAdsManager.enableTestMode()

    }
}