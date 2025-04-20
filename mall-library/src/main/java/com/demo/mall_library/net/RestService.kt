package com.demo.mall_library.net

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

/**
 * 返回String可以适用很多不同类型的类
 */
interface RestService {

    @GET
    fun get(
        @Url url: String?,
        @QueryMap params: WeakHashMap<String, Any>?
    ): Call<String>

    @FormUrlEncoded
    @POST
    fun post(
        @Url url: String?,
        @QueryMap params: WeakHashMap<String, Any>?
    ): Call<String>

    @PUT
    fun put(
        @Url url: String?,
        @QueryMap params: WeakHashMap<String, Any>?
    ): Call<String>

    @DELETE
    fun delete(
        @Url url: String?,
        @QueryMap params: WeakHashMap<String, Any>?
    ): Call<String>

    @Streaming
    @GET
    fun download(
        @Url url: String,
        @QueryMap params: WeakHashMap<String, Any>?
    ): Call<ResponseBody>


    fun upload(
        @Url url: String,
        @Part file: MultipartBody.Part?
    ): Call<String>
}