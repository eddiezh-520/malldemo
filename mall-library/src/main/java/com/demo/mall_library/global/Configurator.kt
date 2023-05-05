package com.demo.mall_library.global

import com.demo.mall_library.util.storage.MemoryStore
import java.lang.RuntimeException
import java.util.logging.Handler

/**
 * 全局配置控制类
 */
class Configurator {

    private object Holder {
        internal val INSTANCE = Configurator()
    }

    companion object {
        private val mStore = MemoryStore.instance

        private val mHandler = android.os.Handler()
        private val mConfigurator :Configurator
            get() = Holder.INSTANCE
    }

    init {
        //加一个标签，判断是否配置完成，现在还没开始配置
        mStore.addData(Globalkeys.IS_CONFIGURE_READY,false)
        mStore.addData(Globalkeys.HANDLER, mHandler)
    }

    /**
     * 访问服务器端配置
     */
    fun withApiHost(host: String) : Configurator{
        mStore.addData(Globalkeys.API_HOST,host)
        return this
    }

    /**
     * 结束配置
     */
    fun configure() {
        mStore.addData(Globalkeys.IS_CONFIGURE_READY,true)
        //回收动作
    }

    private fun checkConfiguration() {
        val isReady = mStore.getData<Boolean>(Globalkeys.IS_CONFIGURE_READY)
        if (!isReady) {
            throw RuntimeException("configuration is not ready!")
        }
    }

    fun <T> getConfiguration(key: String) : T {
        checkConfiguration()
        return mStore.getData(key)
    }

    fun <T> getConfiguration(key: Enum<*>) : T {
        checkConfiguration()
        return getConfiguration(key.name)
    }

}