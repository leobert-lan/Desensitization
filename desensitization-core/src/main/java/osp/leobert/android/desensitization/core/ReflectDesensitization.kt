package osp.leobert.android.desensitization.core

import cn.hutool.core.annotation.AnnotationUtil
import cn.hutool.core.util.ReflectUtil
import osp.leobert.android.desensitization.core.notation.Desensitize

/**
 * Classname: ReflectDesensitization </p>
 * Description: a build-in entrance of desensitization, use reflect to access desensitize handler </p>
 * Created by Leobert on 2023/7/17.
 */
class ReflectDesensitization(strategy: DesensitizeStrategy) : Desensitization(strategy) {

    override fun <T : Any> desensitize(clz: Class<in T>, t: T): T {

        ReflectUtil.getFields(clz).filter {
            AnnotationUtil.hasAnnotation(it, Desensitize::class.java)
        }.map {
            DesensitizeField(t, it)
        }.forEach {
            it.desensitize(strategy)
        }
        return t

    }
}