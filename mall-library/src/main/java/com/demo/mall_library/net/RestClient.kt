package com.demo.mall_library.net

import android.util.Log
import com.demo.mall_library.net.callback.*
import retrofit2.Call
import java.util.*

/**
 * 对外使用Rest请求的交接口
 */
class RestClient internal constructor(
   private val url: String?,
   private val params: WeakHashMap<String,Any>,
   private val request: IRequest?,
   private val success: ISuccess?,
   private val failure: IFailure?,
   private val error: IError?,
   private val complete: IComplete?
) {

    companion object {
        fun builder(): RestClientBuilder {
            return RestClientBuilder()
        }
    }

    private fun requestMethod(httpMethod: HttpMethod) {
        val restService = RestCreator.restService
        val call: Call<String>?
        request?.onRequestStart()

        call = when (httpMethod) {
            HttpMethod.GET -> {
                restService.get(url, params)
            }
            HttpMethod.POST -> {
                restService.post(url, params)
            }
            HttpMethod.PUT -> {
                restService.put(url, params)
            }
            HttpMethod.DELETE -> {
                restService.delete(url, params)
            }
            else -> {
                restService.get(url, params)
            }
        }

        call.enqueue(requestCallback)

    }

    private val requestCallback: RequestCallBacks = RequestCallBacks(url, request, success, failure, error, complete)

    fun get() {
        requestMethod(HttpMethod.GET)
    }

    fun post() {
        requestMethod(HttpMethod.POST)
    }

    fun put() {
        requestMethod(HttpMethod.PUT)
    }

    fun delete() {
        requestMethod(HttpMethod.DELETE)
    }
}