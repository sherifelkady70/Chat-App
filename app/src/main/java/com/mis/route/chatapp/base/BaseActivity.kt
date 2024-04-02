package com.mis.route.chatapp.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mis.route.chatapp.R

abstract class BaseActivity<VM:BaseViewModel,DB:ViewDataBinding> :AppCompatActivity() {

    lateinit var viewModel : VM
    lateinit var dataBinding: DB
    private var dialog : AlertDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=initViewModel()
        dataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        dataBinding.lifecycleOwner = this
        observeLiveData()
    }

    abstract fun initViewModel() : VM
    abstract fun getLayoutId() : Int
    open fun observeLiveData(){
        viewModel.isLoading.observe(this){ loading ->
            if(loading){
                showLoading()
            }else{
                hideLoading()
            }
        }

        viewModel.viewMessage.observe(this) { vMessage ->
            showDialog(
                vMessage.title, vMessage.message, vMessage.posBtnTitle, vMessage.onPosBtnClick,
                vMessage.onNegBtnClick,
                vMessage.negBtnTitle
            )
        }
    }
    fun showLoading(){
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.loading)
        dialog = builder.create()
        dialog?.show()
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
        val myDialog = AlertDialog.Builder(this)
        myDialog.setTitle(title)
        myDialog.setMessage(message)
        posBtnTitle.let {
            myDialog.setPositiveButton(posBtnTitle,
                object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                        onPosBtnClick?.invoke()
                    }

                })
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

        myDialog.create().show()

    }
}