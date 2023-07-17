package osp.leobert.android.desensitization.core.consts

/**
 * Classname: DesensitizeType </p>
 * Description: the field types for department of different handle of desensitization </p>
 * Created by Leobert on 2023/7/17.
 */
enum class DesensitizeType {
    /**
     * email address
     * */
    EMAIL,

    /**
     * mobile phone number
     * */
    MOBILE,

    /**
     * Ip address
     * */
    IP,

    /**
     * strict、block、city、 etc.
     * */
    ADDRESS,


    BANK_CARD,

    CAR_LICENSE,

    GENDER,
    BIRTHDAY,
    NAME,
    ID_CARD,

    OTHER;
}