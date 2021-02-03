package ltd.ontsol.core.config;


import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import ltd.ontsol.core.constants.LocaleMapConstants;
import java.util.Arrays;
/**
 * Created by cn40580 at 2018-07-05 6:09 PM.
 */
@ConfigurationProperties("argweb")
public class LocaleResolver extends AcceptHeaderLocaleResolver {
    private static final String I18N_LANGUAGE = "lang";
    private static final String I18N_LANGUAGE_SESSION = "lang_session";
    private static final String I18N_LANGUAGE_SESSION_1 = "lang_session_1";
    private static final String I18N_LANGUAGE_LOCALE_MAP = "localeMap";
    private static final String I18N_LANGUAGE_SUPPORT = "supportLocals";
    private static final String I18N_LANGUAGE_SUPPORT_1 = "supportLocals_1";

    private List<Locale> argSupportLocales;
    String[] array = {"en"};
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String i18n_language = request.getParameter(I18N_LANGUAGE);
        Locale locale = request.getLocale() == null ? getDefaultLocale() : request.getLocale();

        if (!StringUtils.isEmpty(i18n_language)) {
            String[] lang = i18n_language.split("_");
            if (lang.length == 2) locale = new Locale(lang[0], lang[1]);
            else if (lang.length == 1) locale = new Locale(lang[0]);

            //将国际化语言保存到session
            HttpSession session = request.getSession();
            //session.setAttribute(I18N_LANGUAGE_SESSION, locale);
            System.out.println(locale.getLanguage());
            if(Arrays.binarySearch(array,locale.getLanguage())>-1){
                //session.setAttribute(I18N_LANGUAGE_SESSION, "en_US");
                session.setAttribute(I18N_LANGUAGE_SESSION, new Locale("en","US"));
            }else{
                session.setAttribute(I18N_LANGUAGE_SESSION, locale);
            }
           // session.setAttribute(I18N_LANGUAGE_SESSION, locale);
            if(lang.length == 2){
                session.setAttribute(I18N_LANGUAGE_SESSION, new Locale(lang[0], lang[1]));
            }else{
                session.setAttribute(I18N_LANGUAGE_SESSION, new Locale(lang[0]));
            }

//            if(locale.getLanguage()!="zh"){
//                //session.setAttribute(I18N_LANGUAGE_SESSION, "en_US");
//                session.setAttribute(I18N_LANGUAGE_SESSION, new Locale("en","US"));
//            	}else{
//            		session.setAttribute(I18N_LANGUAGE_SESSION_1, "zh_CN");
//            		}
        }
        HttpSession session = request.getSession();
        Object supportLang = session.getAttribute(I18N_LANGUAGE_SUPPORT);
        if (supportLang == null) {
            session.setAttribute(I18N_LANGUAGE_SUPPORT, getArgSupportLocales().stream().limit(9).collect(Collectors.toList()));
            session.setAttribute(I18N_LANGUAGE_SUPPORT_1, getArgSupportLocales().stream().limit(2).collect(Collectors.toList()));
        }

        Object localeMap = session.getAttribute(I18N_LANGUAGE_LOCALE_MAP);
        if (localeMap == null) {
            session.setAttribute(I18N_LANGUAGE_LOCALE_MAP, LocaleMapConstants.class);
        }

        Object currentLocale = session.getAttribute(I18N_LANGUAGE_SESSION);
        if (currentLocale == null) {
            //session.setAttribute(I18N_LANGUAGE_SESSION, locale);
            System.out.println(locale.getLanguage());
            if(Arrays.binarySearch(array,locale.getLanguage())>-1){
                session.setAttribute(I18N_LANGUAGE_SESSION,  new Locale("en","US"));
            }else{
                session.setAttribute(I18N_LANGUAGE_SESSION, locale);
            }
            //session.setAttribute(I18N_LANGUAGE_SESSION, locale);
            if(locale.getLanguage()!="zh"){
            	session.setAttribute(I18N_LANGUAGE_SESSION_1,  "en_US");
            	}else{
            		session.setAttribute(I18N_LANGUAGE_SESSION_1, "zh_CN");
            		}
        } else {
          // Locale currentLocale_1 = new Locale(currentLocale.toString());
            if(Arrays.binarySearch(array,currentLocale.toString())>-1){
                currentLocale = new Locale("en","US");
            }
            //currentLocale = new Locale(currentLocale.toString());
           if(currentLocale=="zh_CN"){
               currentLocale = new Locale("zh","CN");
           }else if(currentLocale=="en_US"){
               currentLocale = new Locale("en","US");
           }else if(currentLocale=="jp_JP"){
               currentLocale = new Locale("jp","JP");
           }else if(currentLocale=="ru_RU"){
               currentLocale = new Locale("ru","RU");
           }
            return (Locale)currentLocale;
        }
        if(Arrays.binarySearch(array,locale.getLanguage())>-1){
            locale = new Locale("en","US");
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }

    public List<Locale> getArgSupportLocales() {
        return argSupportLocales;
    }

    public void setArgSupportLocales(List<Locale> argSupportLocales) {
        this.argSupportLocales = argSupportLocales;
    }

}
