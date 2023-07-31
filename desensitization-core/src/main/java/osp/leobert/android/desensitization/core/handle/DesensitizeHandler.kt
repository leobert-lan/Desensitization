package osp.leobert.android.desensitization.core.handle

import osp.leobert.android.desensitization.core.consts.DesensitizeType

/**
 * Classname: DesensitizeHandler </p>
 * Description: interface for desensitize sth. override [support] method to deal with the target whether can be desensitized by this.
 *
 * override [desensitize] to fill custom desensitize logic
 * </p>
 * Created by Leobert on 2023/7/17.
 */
interface DesensitizeHandler {
    val responseType: DesensitizeType
    val supportType: Class<*>

    fun support(target: Any?): Boolean {
        target ?: return false
        return supportType.isAssignableFrom(target.javaClass)
    }

    fun desensitize(target: Any): Any

    class Stub() :DesensitizeHandler {
        override fun desensitize(target: Any): Any {
            return target
        }

        override val responseType: DesensitizeType
            get() = DesensitizeType.OTHER

        override val supportType: Class<Any>
            get() = Any::class.java
    }
}