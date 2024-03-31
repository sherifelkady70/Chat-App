package com.mis.route.chatapp.models

data class ViewMessage(
    var title: String? = null,
    var message: String? = null,
    var posBtnTitle: String? = null,
    var onPosBtnClick: (() -> Unit)? = null,
    var onNegBtnClick: (() -> Unit)? = null,
    var negBtnTitle: String? = null,
)
