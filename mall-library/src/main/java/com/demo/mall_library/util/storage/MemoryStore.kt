package com.demo.mall_library.util.storage

/**
 * 全局类：单例，线程安全
 */
class MemoryStore private constructor(){


    /**
     * (静态的) 线程安全的单例模式
     * 仿照java写法
     */
    private object Holder {
        internal val INSTANCE = MemoryStore()
    }

    companion object {
        val instance : MemoryStore
            get() = Holder.INSTANCE
    }

    private val mDataMap = HashMap<String,Any>()

    fun <T>getData(key: String) : T {
        @Suppress("UNCHECKED_CAST")
        return mDataMap[key] as T
    }

    fun addData(key: String, value: Any) : MemoryStore {
        mDataMap[key] = value
        return this
    }

    fun addData(key: Enum<*>, value: Any) : MemoryStore {
        addData(key.name,value)
        return this
    }

    fun <T>getData(key: Enum<*>) : T {
        return getData(key.name)
    }
}
