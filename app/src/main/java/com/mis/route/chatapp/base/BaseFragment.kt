package com.mis.route.chatapp.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.mis.route.chatapp.R

abstract class BaseFragment<VM:ViewModel,DB:ViewDataBinding> : Fragment() {

    lateinit var viewModel : VM
    lateinit var dataBinding : DB
    private var dialog : AlertDialog?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel()
        dataBinding = DataBindingUtil.inflate(layoutInflater,getLayoutId(),container,false)
        dataBinding.lifecycleOwner=this
        return dataBinding.root
    }

    abstract fun getLayoutId(): Int

    abstract fun initViewModel() : VM

    fun showLoading(){
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.loading)
        dialog = builder.create()
    }
    fun hideLoading(){
        dialog?.dismiss()
    }

    fun showDialog(
        title: String? = null,
        message: String? = null,
        posBtnTitle: String? = null,
        onPosBtnClick: (() -> Unit)? = null,
        onNegBtnClick: (() -> Unit)? = null,
        negBtnTitle: String? = null,


        ) {
        val myDialog = AlertDialog.Builder(activity)
        myDialog.setTitle(title)
        myDialog.setMessage(message)
        posBtnTitle.let {
            myDialog.setPositiveButton(
                posBtnTitle
            ) { dialog, which ->
                dialog?.dismiss()
                onPosBtnClick?.invoke()
            }
        }
        negBtnTitle.let {
            myDialog.setNegativeButton(negBtnTitle,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                        onNegBtnClick?.invoke()
                    }

                })
        }

    }

}