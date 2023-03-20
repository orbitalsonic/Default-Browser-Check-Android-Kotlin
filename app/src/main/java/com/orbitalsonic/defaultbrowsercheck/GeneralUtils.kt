package com.orbitalsonic.defaultbrowsercheck

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.orbitalsonic.defaultbrowsercheck.FirebaseUtils.recordException


object GeneralUtils {

    private val generalTag = "generalTag"
    fun Context?.isBrowserAvailable(): Boolean {
        this?.let {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
                val resolveInfo =
                    it.packageManager.resolveActivity(browserIntent, PackageManager.MATCH_DEFAULT_ONLY)
                val defaultBrowserPkg: String
                if (resolveInfo != null) {
                    if (resolveInfo.activityInfo != null) {
                        defaultBrowserPkg = resolveInfo.activityInfo.packageName
                        defaultBrowserPkg?.let { dbp ->
                            Log.d(generalTag, dbp)
                            return !(dbp.contains("player") ||
                                    dbp.contains("video") ||
                                    dbp.contains("mxtech") ||
                                    dbp.contains("music") ||
                                    dbp.contains("mp3") ||
                                    dbp.contains("audio") ||
                                    dbp.contains("downloader") ||
                                    dbp.contains("videodownloader") ||
                                    dbp.contains("casttv") ||
                                    dbp.contains("castforchromecast") ||
                                    dbp.contains("screencast") ||
                                    dbp.contains("doubleclick") ||
                                    dbp.contains("videoplayer"))
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.recordException("isBrowserAvailableTAG")
            }
        }

        return true
    }

    fun Context?.defaultBrowser(): String {
        this?.let {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
                val resolveInfo =
                    it.packageManager.resolveActivity(browserIntent, PackageManager.MATCH_DEFAULT_ONLY)
                val defaultBrowserPkg: String
                if (resolveInfo != null) {
                    if (resolveInfo.activityInfo != null) {
                        defaultBrowserPkg = resolveInfo.activityInfo.packageName
                        defaultBrowserPkg?.let { dbp ->
                            Log.d(generalTag, dbp)
                            return dbp
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.recordException("isBrowserAvailableTAG")
            }
        }
        return "Unknown Browser"
    }

}