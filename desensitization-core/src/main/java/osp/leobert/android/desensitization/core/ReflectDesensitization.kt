package osp.leobert.android.desensitization.core

import osp.leobert.android.desensitization.core.notation.Desensitize

/**
 * Classname: ReflectDesensitization </p>
 * Description: a build-in entrance of desensitization, use reflect to access desensitize handler </p>
 * Created by Leobert on 2023/7/17.
 */
class ReflectDesensitization(strategy: DesensitizeStrategy) : Desensitization(strategy) {

    override fun <T : Any> desensitize(clz: Class<in T>, t: T): T {

        Utils.getFields(clz).filter {
            it.getAnnotation(Desensitize::class.java) != null
        }.map {
            DesensitizeField(t, it)
        }.forEach {
            it.desensitize(strategy)
        }
        return t

    }
}