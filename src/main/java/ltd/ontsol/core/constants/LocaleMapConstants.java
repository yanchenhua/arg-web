package ltd.ontsol.core.constants;

import java.util.Locale;

/**
 * Created by cn40580 at 2018-07-03 12:04 AM.
 */
public enum LocaleMapConstants {
       cn("中文"), en("English"), ru("Русский язык"),ru_RU("Русский язык"), zh_cn("中文"), en_us("English"), CN("中文"), EN("English"), RU("Русский язык"), zh_CN("中文"), en_US("English"), fr_FR("Français"), jp_JP("日本語") ,kr_KR("한국어") ,de_DE("Deutsch") ,es_ES("Español") ,por_POR("Português");


    private String desc;

    LocaleMapConstants(String desc) {
        this.desc = desc;
    }

    public static String langDesc(Locale localMap) {

        return valueOf(localMap.toString()).desc;
    }
}
