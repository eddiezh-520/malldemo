package com.demo.mall_demo

import android.app.Application
import com.demo.mall_library.global.Mall

class MallApp: Application() {

    override fun onCreate() {
        super.onCreate()
        /**
         * withApiHost -> ApiHost配置，意味着一开始就已经帮你配置好了ApiHost
         * init -> applicationContext ，让app在一开始就设置好，然后在MainActivity或者其他的activity就可以用
         */
        Mall.init(this)
            .withApiHost("https://class.imooc.com/mock/api/")
                //https://class.imooc.com/mock/api/
            .configure()
    }
}