package net.ijiangtao.tech.framework.spring.ispringboot.demo.start.i18n.util;

/**
 * @author ijiangtao.net
 */
public class LanguageUtil {

    public static final String ZH_CN = "zh_CN";

    public static final String EN_US = "en_US";

    public static final String DEFAULT_LANGUAGE = ZH_CN;

    private String lang;

    private static final ThreadLocal<LanguageUtil> context = new ThreadLocal<LanguageUtil>() {
        @Override
        protected LanguageUtil initialValue() {
            return new LanguageUtil();
        }
    };

    public LanguageUtil() {
        lang = DEFAULT_LANGUAGE;
    }

    public static LanguageUtil getCurrentContext() {
        return (LanguageUtil) context.get();
    }

    public static String getCurrentLang() {
        return getCurrentContext().lang;
    }

    public static void setCurrentLang(String lang) {
        getCurrentContext().lang = lang;
    }

    public static void remove() {
        context.remove();
    }
}
