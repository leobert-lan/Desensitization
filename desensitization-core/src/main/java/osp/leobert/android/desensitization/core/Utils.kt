package osp.leobert.android.desensitization.core

import java.lang.reflect.AccessibleObject
import java.lang.reflect.Field

/**
 * Classname: Utils </p>
 * Created by Leobert on 2023/8/7.
 */
object Utils {
    private val FIELDS_CACHE = hashMapOf<Class<*>, Array<Field>>()

    @Throws(Exception::class)
    fun getFieldValue(obj: Any?, field: Field?): Any? {
        field ?: return null
        setAccessible(field)

        return if (obj is Class<*>) {
            // 静态字段获取时对象为null
            field.get(null)
        } else {
            field.get(obj)
        }
    }

    fun <T : AccessibleObject?> setAccessible(accessibleObject: T?): T? {
        if (null != accessibleObject && !accessibleObject.isAccessible) {
            accessibleObject.isAccessible = true
        }
        return accessibleObject
    }

    @Throws(SecurityException::class)
    fun getFields(beanClass: Class<*>?): Array<Field> {
        beanClass ?: return arrayOf()
        synchronized(this) {

            return FIELDS_CACHE[beanClass] ?: getFieldsDirectly(beanClass, true).apply {
                FIELDS_CACHE[beanClass] = this
            }
        }
    }

    /**
     * 获得一个类中所有字段列表，直接反射获取，无缓存<br></br>
     * 如果子类与父类中存在同名字段，则这两个字段同时存在，子类字段在前，父类字段在后。
     *
     * @param beanClass            类
     * @param withSuperClassFields 是否包括父类的字段列表
     * @return 字段列表
     * @throws SecurityException 安全检查异常
     */
    @Throws(SecurityException::class)
    fun getFieldsDirectly(beanClass: Class<*>?, withSuperClassFields: Boolean): Array<Field> {
        beanClass ?: return arrayOf()

        var searchType = beanClass
        val ret = arrayListOf<Field>()

        while (searchType != null) {
            ret.addAll(searchType.declaredFields)
            searchType = if (withSuperClassFields) searchType.superclass else null
        }
        return ret.toArray(arrayOf())
    }

}