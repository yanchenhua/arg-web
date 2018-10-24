package ltd.ontsol.core.util;

/**
 * Created by cn40580 at 2018-07-02 1:56 PM.
 */
public class HtmlTagUtil {

    /**
     * The Constant HTML_FONT_Head.
     */
    private static final String HTML_FONT_Head = "<font";

    /**
     * The Constant HTML_DIV_Head.
     */
    private static final String HTML_DIV_Head = "<div";

    /**
     * The Constant HTML_SPAN_Head.
     */
    private static final String HTML_SPAN_Head = "<span";


    public static String removeHtmlTags(final String longtext) {
        String result = longtext;
        result = removeFontDivSpan(result, HTML_FONT_Head);
        result = removeFontDivSpan(result, HTML_DIV_Head);
        result = removeFontDivSpan(result, HTML_SPAN_Head);
        result = replaceString(result, "&lt;", "<", false);
        result = replaceString(result, "&gt;", ">", false);
        result = replaceString(result, "<(>", "", false);
        result = replaceString(result, "<<)>", "<", false);
        result = replaceString(result, "<br>", "\n", false);
        result = replaceString(result, "<br/>", "\n", false);
        result = replaceString(result, "\r", "", false);
        result = replaceString(result, "\t", "", false);
        result = replaceString(result, "<UL>", "", false);
        result = replaceString(result, "</UL>", "", false);
        result = replaceString(result, "<OL>", "", false);
        result = replaceString(result, "</OL>", "", false);
        result = replaceString(result, "</LI>", "\n", false);
        result = replaceString(result, "<LI>", "", false);
        result = replaceString(result, "<P>", "", false);
        result = replaceString(result, "</P>", "\n", false);
        result = replaceString(result, "&NBSP;", " ", false);
        result = replaceString(result, "<UL type=disc>", "", false);
        result = replaceString(result, "</FONT>", "", false);
        result = replaceString(result, "<FONT>", "", false);
        result = replaceString(result, "</DIV>", "", false);
        result = replaceString(result, "<DIV>", "", false);
        result = replaceString(result, "</SPAN>", "", false);
        result = replaceString(result, "<SPAN>", "", false);
        result = replaceString(result, "<br />", "\n", false);
        result = replaceString(result, "\n\n", "\n", false);
        result = replaceString(result, "§", "\n", false);
        result = replaceString(result, "&middot;", "", false);

        result = prepareReplaceHtmlToLatin(result);

        return result;

    }

    /**
     * Prepare replace html to latin.
     *
     * @param longtext the longtext
     * @return the string
     */
    private static String prepareReplaceHtmlToLatin(final String longtext) {
        String result = longtext;

        result = replaceString(result, "&auml;", "ä", true);
        result = replaceString(result, "&ouml;", "ö", true);
        result = replaceString(result, "&uuml;", "ü", true);
        result = replaceString(result, "&Auml;", "Ä", true);
        result = replaceString(result, "&Ouml;", "Ö", true);
        result = replaceString(result, "&Uuml;", "Ü", true);

        result = replaceString(result, "&Agrave;", "À", true);
        result = replaceString(result, "&agrave;", "à", true);
        result = replaceString(result, "&Egrave;", "È", true);
        result = replaceString(result, "&egrave;", "è", true);
        result = replaceString(result, "&Igrave;", "Ì", true);
        result = replaceString(result, "&igrave;", "ì", true);
        result = replaceString(result, "&Ograve;", "Ò", true);
        result = replaceString(result, "&ograve;", "ò", true);
        result = replaceString(result, "&Ugrave;", "Ù", true);
        result = replaceString(result, "&ugrave;", "ù", true);

        result = replaceString(result, "&Aacute;", "Á", true);
        result = replaceString(result, "&aacute;", "á", true);
        result = replaceString(result, "&Eacute;", "É", true);
        result = replaceString(result, "&eacute;", "é", true);
        result = replaceString(result, "&Iacute;", "Í", true);
        result = replaceString(result, "&iacute;", "í", true);
        result = replaceString(result, "&Oacute;", "Ó", true);
        result = replaceString(result, "&oacute;", "ó", true);
        result = replaceString(result, "&Uacute;", "Ú", true);
        result = replaceString(result, "&uacute;", "ú", true);
        result = replaceString(result, "&Yacute;", "Ý", true);
        result = replaceString(result, "&yacute;", "ý", true);

        result = replaceString(result, "&Acirc;", "Â", true);
        result = replaceString(result, "&acirc;", "â", true);
        result = replaceString(result, "&Ecirc;", "Ê", true);
        result = replaceString(result, "&ecirc;", "ê", true);
        result = replaceString(result, "&Icirc;", "Î", true);
        result = replaceString(result, "&icirc;", "î", true);
        result = replaceString(result, "&Ocirc;", "Ô", true);
        result = replaceString(result, "&ocirc;", "ô", true);
        result = replaceString(result, "&Ucirc;", "Û", true);
        result = replaceString(result, "&ucirc;", "û", true);

        result = replaceString(result, "&szlig;", "ß", false);
        result = replaceString(result, "&deg;", "°", false);
        result = replaceString(result, "&reg;", "®", false);
        return result;

    }

    private static String removeFontDivSpan(final String _string, final String htmlTag) {
        StringBuffer sb = new StringBuffer(_string);
        String _lowerString = _string.toLowerCase();

        int sbDisp = 0;
        int lpv = 0;
        int lp = _lowerString.indexOf(htmlTag, lpv);
        int lpend = _lowerString.indexOf(">", lp) + 1;
        while (lp > -1) {
            sb.delete(lp + sbDisp, lpend + sbDisp);
            sbDisp -= (lpend - lp);
            lpv = lpend;
            lp = _lowerString.indexOf(htmlTag, lpv);
            lpend = _lowerString.indexOf(">", lp) + 1;
        }
        return sb.toString();
    }

    private static String replaceString(final String _string, final String _srStr, final String _rpStr, final boolean caseSensitive) {
        StringBuffer sb = new StringBuffer(_string);
        String _innerString = _string;
        String _innerSrStr = _srStr;

        if (!caseSensitive) {
            _innerString = _innerString.toLowerCase();
            _innerSrStr = _innerSrStr.toLowerCase();
        }
        int sbDisp = 0;

        int lpv = 0;
        int lp = _innerString.indexOf(_innerSrStr, lpv);
        while (lp > -1) {
            sb.delete(lp + sbDisp, lp + sbDisp + _innerSrStr.length());
            sb.insert(lp + sbDisp, _rpStr);
            sbDisp -= _innerSrStr.length();
            sbDisp += _rpStr.length();
            lpv = lp + _innerSrStr.length();
            lp = _innerString.indexOf(_innerSrStr, lpv);
        }
        return sb.toString();
    }
}
