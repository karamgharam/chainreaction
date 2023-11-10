package com.chainreaction.karam.utils.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.tentimelimited.ios.R


abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel: VM

    lateinit var binding: VB


    protected abstract fun getViewModelClass(): Class<VM>

    protected abstract fun getLayoutId(): Int

    protected open fun setupViewModel() {
        //override optional
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindingAndViewModel()
        setupViewModel()
        observeLiveData()

    }

    private fun setupBindingAndViewModel() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProvider(this)[getViewModelClass()]
    }


    private fun observeLiveData() {
        viewModel.getShowProgressDialogLiveData().observe(this) { booleanEvent ->
            if (booleanEvent.contentIfNotHandled != null) showProgressDialog(booleanEvent.peekContent())
        }
    }

    private var progressDialog: Dialog? = null
    private fun showProgressDialog(show: Boolean) {
        if (progressDialog == null) progressDialog = getProgressDialog(this)
        if (show) {
            progressDialog?.show()
        } else progressDialog?.dismiss()
    }

    private fun getProgressDialog(context: Context?): Dialog {
        val builder = AlertDialog.Builder(
            context!!
        )
        builder.setView(R.layout.dialog_loading)
        builder.setCancelable(false)
        return builder.create()
    }


}