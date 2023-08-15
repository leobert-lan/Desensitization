package osp.leobert.android.desensitization.core

import osp.leobert.android.desensitization.core.consts.DesensitizeType
import osp.leobert.android.desensitization.core.handle.DesensitizeHandler
import osp.leobert.android.desensitization.core.notation.Desensitize
import java.lang.reflect.Field
import java.util.EnumMap


/**
 * Classname: DesensitizeStrategy </p>
 * Created by Leobert on 2023/7/17.
 */
open class DesensitizeStrategy(
    private val handlers: Map<DesensitizeType, DesensitizeHandler> =
        EnumMap(DesensitizeType::class.java),
    private val customHandlerProvider: (Class<out DesensitizeHandler>) -> DesensitizeHandler?
) {

    private val nullSafe: DesensitizeHandler = DesensitizeHandler.Stub()

    fun getHandler(desensitizeType: DesensitizeType): DesensitizeHandler {
        return handlers[desensitizeType] ?: nullSafe
    }

    fun getHandler(desensitize: Desensitize): DesensitizeHandler {
        return if (desensitize.handle == DesensitizeHandler.Stub::class.java) {
            getHandler(desensitize.type)
        } else {
            customHandlerProvider(desensitize.handle.java) ?: nullSafe
        }
    }

    fun getHandler(field: Field): DesensitizeHandler {
        return field.getAnnotation(Desensitize::class.java)?.let {
            getHandler(it)
        } ?: nullSafe


    }
}