package osp.leobert.android.desensitization.core

import cn.hutool.core.util.ReflectUtil
import java.lang.reflect.Field

/**
 * Classname: DesensitizeField </p>
 * Description: a struct to declare information of the filed to be desensitized </p>
 * Created by Leobert on 2023/7/17.
 */
class DesensitizeField(private val obj: Any, private val field: Field) {
    fun desensitize(strategy: DesensitizeStrategy) {
        strategy.getHandler(field).let {
            val value = ReflectUtil.getFieldValue(obj, field)
            if (it.support(value)) {
                field.set(obj, it.desensitize(value))
            }
        }
    }
}