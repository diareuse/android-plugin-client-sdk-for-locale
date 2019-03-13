package com.twofortyfouram.locale.sdk.client.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.twofortyfouram.locale.sdk.client.internal.TaskerPluginActivityDelegate

abstract class TaskerAppCompatPluginActivity : AppCompatActivity(), IPluginActivity {

    protected var isCancelled = false
    private val delegate = TaskerPluginActivityDelegate<TaskerAppCompatPluginActivity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.onCreate(this, savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delegate.onPostCreate(this, savedInstanceState)
    }

    override fun finish() {
        delegate.finish(this, isCancelled)
        super.finish()
    }

    override fun getPreviousBundle() = delegate.getPreviousBundle(this)

    override fun getPreviousBlurb() = delegate.getPreviousBlurb(this)
}