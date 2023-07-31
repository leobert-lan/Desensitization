package osp.leobert.android.desensitization.core

/**
 * abstract layer, entrance of functions
 * */
abstract class Desensitization(val strategy: DesensitizeStrategy) {

    abstract fun <T : Any> desensitize(clz: Class<in T>, t: T): T

}