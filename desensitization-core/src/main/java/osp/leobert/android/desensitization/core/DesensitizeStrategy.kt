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
    private val handlers: Map<DesensitizeType, DesensitizeHandler> =
        EnumMap(DesensitizeType::class.java),
    private val customHandlerProvider: (Class<out DesensitizeHandler>) -> DesensitizeHandler?
) {

    private val nullSafe: DesensitizeHandler = DesensitizeHandler.Stub()

    open fun getHandler(desensitizeType: DesensitizeType): DesensitizeHandler {
        return handlers[desensitizeType] ?: nullSafe
    }

    open fun getHandler(desensitize: Desensitize): DesensitizeHandler {
        return if (desensitize.handle == DesensitizeHandler.Stub::class.java) {
            getHandler(desensitize.type)
        } else {
            customHandlerProvider(desensitize.handle.java) ?: nullSafe
        }
    }

    open fun getHandler(field: Field): DesensitizeHandler {
        return if (AnnotationUtil.hasAnnotation(field, Desensitize::class.java)) {
            getHandler(field.getAnnotation(Desensitize::class.java))
        } else {
            nullSafe
        }
    }
}