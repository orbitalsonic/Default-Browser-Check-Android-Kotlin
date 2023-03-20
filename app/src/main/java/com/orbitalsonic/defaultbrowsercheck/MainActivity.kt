package com.orbitalsonic.defaultbrowsercheck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orbitalsonic.defaultbrowsercheck.FirebaseUtils.recordException
import com.orbitalsonic.defaultbrowsercheck.GeneralUtils.defaultBrowser
import com.orbitalsonic.defaultbrowsercheck.GeneralUtils.isBrowserAvailable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isBrowserAvailable()) {
            try {
                SecurityException("BROWSER_NOT_AVAILABLE and default browser is = ${defaultBrowser()}").recordException("BROWSER_NOT_AVAILABLE")
            } catch (ex: Exception) {
                ex.recordException("isBrowserAvailableTAG")
            }
        }

    }
}