package osp.leobert.android.desensitization.core.handle

import osp.leobert.android.desensitization.core.consts.DesensitizeType

/**
 * Classname: DesensitizeHandler </p>
 * Description: TODO </p>
 * Created by Leobert on 2023/7/17.
 */
interface DesensitizeHandler<T> {
    val responseType: DesensitizeType
    val supportType: Class<T>

    fun support(target: Any?): Boolean {
        target ?: return false
        return supportType.isAssignableFrom(target.javaClass)
    }

    fun desensitize(target: T): T

    class Stub() :DesensitizeHandler<Any> {
        override fun desensitize(target: Any): Any {
            return target
        }

        override val responseType: DesensitizeType
            get() = DesensitizeType.OTHER

        override val supportType: Class<Any>
            get() = Any::class.java
    }
}