package com.demo.mall_library.net.callback

interface IError {
    fun onError(code: Int, msg: String)
}