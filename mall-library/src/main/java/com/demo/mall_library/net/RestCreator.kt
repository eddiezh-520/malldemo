package com.demo.mall_library.net

import com.demo.mall_library.global.Globalkeys
import com.demo.mall_library.global.Mall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

/**
 * 创建Retrofit的各个实例
 */
object RestCreator {

    private object OkHttpHolder {
        private val TIME_OUT = 60
        private val BUILDER = OkHttpClient.Builder()

        internal val OKHTTP_CLIENT = BUILDER
            .connectTimeout(TIME_OUT.toLong(),TimeUnit.SECONDS)
            .build()
    }

    private object RetrofitHolder {
        /**
         * API_HOST 在MallAppp里面配置，意味着一开始就已经帮你配置好了API_HOST
         */
        private val BASE_URL =
            Mall.getConfiguration<String>(Globalkeys.API_HOST)

        val RETROFIT_BUILDER = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpHolder.OKHTTP_CLIENT)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    }

    private object RestHolder {
        internal val REST_SERVICE =
            RetrofitHolder.RETROFIT_BUILDER.create(RestService::class.java)
    }

    val restService: RestService get() = RestHolder.REST_SERVICE
}