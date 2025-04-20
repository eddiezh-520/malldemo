package com.demo.mall_library.global

import android.content.Context
import android.database.DatabaseUtils
import com.demo.mall_library.util.storage.MemoryStore

object Mall {

    val configurator: Configurator
        get() = Configurator.instance

     fun init(context: Context): Configurator {
         /**
          * 设置 applicationContext ，让app在一开始就设置好，然后在MainActivity或者其他的activity就可以用
          *
          */
         MemoryStore.instance.addData(Globalkeys.APPLICATION_CONTEXT,context.applicationContext)
         
         return Configurator.instance
     }

    private fun <T> getConfiguration(key: String) : T {
        return configurator.getConfiguration(key)
    }

    fun <T> getConfiguration(key: Enum<Globalkeys>) : T {
        return getConfiguration(key.name)
    }
}