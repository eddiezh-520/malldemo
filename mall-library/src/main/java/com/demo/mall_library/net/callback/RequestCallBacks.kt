package com.demo.mall_library.net.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RequestCallBacks(
    private val url: String?,
    private val request: IRequest?,
    private val success: ISuccess?,
    private val failure: IFailure?,
    private val error: IError?,
    private val complete: IComplete?
): Callback<String> {
    override fun onResponse(call: Call<String>, response: Response<String>) {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                if (success != null) {
                    if (response.body() != null) {
                        success.onSuccess(response.body()!!)
                    }
                }
            }
        } else {
            if (error != null) {
                error.onError(response.code(),response.message())
            }
        }
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        failure?.onFailure()
        request?.onRequestEnd()
    }
}