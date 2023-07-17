package osp.leobert.android.desensitization.core

import cn.hutool.core.annotation.AnnotationUtil
import osp.leobert.android.desensitization.core.consts.DesensitizeType
import osp.leobert.android.desensitization.core.handle.DesensitizeHandler
import osp.leobert.android.desensitization.core.notation.Desensitize
import java.lang.reflect.Field
import java.util.EnumMap


/**
 * Classname: DesensitizeStrategy </p>
 * Description: TODO </p>
 * Created by Leobert on 2023/7/17.
 */
open class DesensitizeStrategy(
    private val handlers: Map<DesensitizeType, DesensitizeHandler<Any>> =
        EnumMap(DesensitizeType::class.java),
    private val customHandlerProvider: (Class<out DesensitizeHandler<Any>>) -> DesensitizeHandler<Any>?
) {

    private val nullSafe: DesensitizeHandler<Any> = DesensitizeHandler.Stub()

    fun getHandler(desensitizeType: DesensitizeType): DesensitizeHandler<Any> {
        return handlers[desensitizeType] ?: nullSafe
    }

    fun getHandler(desensitize: Desensitize): DesensitizeHandler<Any> {
        return if (desensitize.handle == DesensitizeHandler.Stub::class.java) {
            getHandler(desensitize.type)
        } else {
            customHandlerProvider(desensitize.handle.java) ?: nullSafe
        }
    }

    fun getHandler(field: Field): DesensitizeHandler<Any> {
        return if (AnnotationUtil.hasAnnotation(field, Desensitize::class.java)) {
            getHandler(field.getAnnotation(Desensitize::class.java))
        } else {
            nullSafe
        }
    }
}