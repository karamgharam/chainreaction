package com.chainreaction.karam.presentation.activity

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.chainreaction.karam.R
import com.chainreaction.karam.databinding.MainActivityBinding
import com.chainreaction.karam.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, MainActivityBinding>() {

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.main_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }


    override fun setupViewModel() {
        binding.viewModel = viewModel
    }


    private fun observeLiveData() {
        viewModel.getShareLiveData().observe(this) {
            if (it.contentIfNotHandled != null) {
                shareFacebook(it.peekContent())
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun shareFacebook(text: String) {
        var facebookAppFound = false
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(text))
        val pm = packageManager
        val activityList = pm.queryIntentActivities(shareIntent, 0)
        for (app in activityList) {
            if (app.activityInfo.packageName.contains("com.facebook.katana")) {
                val activityInfo = app.activityInfo
                val name =
                    ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name)
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                shareIntent.component = name
                facebookAppFound = true
                break
            }
        }
        if (!facebookAppFound) {
            val sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=$text"
            shareIntent = Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl))
        }
        startActivity(shareIntent)
    }


}