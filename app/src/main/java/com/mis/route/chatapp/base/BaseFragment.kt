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

abstract class BaseFragment<VM:BaseViewModel,DB:ViewDataBinding> : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initViewModel() : VM


     private fun observeLiveData(){
        viewModel.isLoading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                showLoading()
            }else{
                hideLoading()
            }
        }

        viewModel.viewMessage.observe(viewLifecycleOwner) { vMessage ->
            showDialog(
                vMessage.title, vMessage.message, vMessage.posBtnTitle, vMessage.onPosBtnClick,
                vMessage.onNegBtnClick,
                vMessage.negBtnTitle
            )
        }
    }

    fun showLoading(){
        val builder = AlertDialog.Builder(activity)
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
        val myDialog = AlertDialog.Builder(activity)
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