package com.twofortyfouram.locale.sdk.client.internal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.twofortyfouram.assertion.BundleAssertions
import com.twofortyfouram.locale.sdk.client.ui.activity.IPluginActivity
import com.twofortyfouram.log.Lumberjack
import com.twofortyfouram.spackle.bundle.BundleComparer
import com.twofortyfouram.spackle.bundle.BundleScrubber
import com.twofortyfouram.locale.api.Intent as ApiIntent

abstract class AbstractPluginActivityDelegate<T> where T : Activity, T : IPluginActivity {

    abstract fun isLocalePluginIntent(intent: Intent): Boolean

    fun onCreate(activity: T, savedInstanceState: Bundle?) {
        val intent = activity.intent
        if (!isLocalePluginIntent(intent)) return

        if (BundleScrubber.scrub(intent)) {
            return
        }

        val previousBundle = activity.previousBundle
        if (BundleScrubber.scrub(previousBundle)) {
            return
        }

        Lumberjack.v(
            "Creating Activity with Intent=%s, savedInstanceState=%s, EXTRA_BUNDLE=%s",
            intent, savedInstanceState, previousBundle
        )

    }

    fun onPostCreate(activity: T, savedInstanceState: Bundle?) {
        if (!isLocalePluginIntent(activity.intent)) return

        savedInstanceState ?: activity.apply {
            val previousBundle = previousBundle ?: return
            val previousBlurb = previousBlurb ?: return
            onPostCreateWithPreviousResult(previousBundle, previousBlurb)
            return
        }
    }

    fun finish(activity: T, isCancelled: Boolean) = activity.run {
        if (isCancelled) {
            return
        }

        val resultBundle = resultBundle ?: return

        BundleAssertions.assertSerializable(resultBundle)
        val blurb = getResultBlurb(resultBundle)

        val matchesPreviousBundle = BundleComparer.areBundlesEqual(resultBundle, previousBundle)
        val matchesPreviousBlurb = blurb == previousBlurb

        if (!matchesPreviousBundle || !matchesPreviousBlurb) {
            val resultIntent = Intent().apply {
                putExtra(ApiIntent.EXTRA_BUNDLE, resultBundle)
                putExtra(ApiIntent.EXTRA_STRING_BLURB, blurb)
            }

            setResult(Activity.RESULT_OK, resultIntent)
        }
    }

    fun getPreviousBlurb(activity: T): String? =
        activity.intent.getStringExtra(ApiIntent.EXTRA_STRING_BLURB)

    fun getPreviousBundle(activity: T): Bundle? {
        val bundle = activity.intent.getBundleExtra(ApiIntent.EXTRA_BUNDLE)
            ?: return null
        return if (activity.isBundleValid(bundle)) bundle else null
    }

}