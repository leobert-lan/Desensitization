package osp.leobert.android.desensitization.core.notation

import osp.leobert.android.desensitization.core.consts.DesensitizeType
import osp.leobert.android.desensitization.core.handle.DesensitizeHandler
import kotlin.reflect.KClass

/**
 * Classname: Desensitize </p>
 * Description: notate for filed to config Desensitize Type and Handle </p>
 * Created by Leobert on 2023/7/17.
 *
 * [type] : the target DesensitizeType,if do not config handle, will use global handle in config
 *
 * [handle] : the specified handle Type,
 */
annotation class Desensitize(

    val type:DesensitizeType = DesensitizeType.OTHER,

    val handle: KClass<out DesensitizeHandler> = DesensitizeHandler.Stub::class

)
