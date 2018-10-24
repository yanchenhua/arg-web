package ltd.ontsol.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DateUtils.
 */
public final class DateUtils {

    /**
     * The Constant PAGE_DEFAULT_FORMATE.
     */
    public static final String PAGE_DEFAULT_FORMATE = "dd.MM.yyyy";

    /**
     * The Constant DEFAULT_FORMATE.
     */
    public static final String DEFAULT_FORMATE = "yyyy-MM-dd";

    /**
     * The Constant DEFAULT_FORMATE.
     */
    public static final String DEFAULT_FORMATE_CN = "yyyy年MM月dd日";

    /**
     * The Constant LOGGER.
     */
    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    /**
     * Instantiates a new date utils.
     */
    private DateUtils() {

    }

    /**
     * Parses the date.
     *
     * @param string the string
     * @return the date
     */
    public static Date parse(final String string) {
        return parse(string, DEFAULT_FORMATE);
    }

    /**
     * Parses the date.
     *
     * @param string       the string
     * @param formatString the format string
     * @return the date
     */
    public static Date parse(final String string, final String formatString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        try {
            return dateFormat.parse(string);
        } catch (ParseException e) {
            log.error("error parsing date", e);
        }
        return null;
    }

    /**
     * Formats the date.
     *
     * @param date the date
     * @return the string
     */
    public static String format(final Date date) {
        return format(date, DEFAULT_FORMATE);
    }

    /**
     * Format.
     *
     * @param date         the date
     * @param formatString the format string
     * @return the string
     */
    public static String format(final Date date, final String formatString) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            log.error("error formatting date", e);
        }
        return null;
    }

    /**
     * Gets the date today.
     *
     * @return the date today
     */
    public static Date getDateToday() {
        return toDate(today());
    }

    /**
     * Today.
     *
     * @return the local date
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /**
     * Format.
     *
     * @param date         the date to be formatted
     * @param formatString the format string
     * @return the string
     */
    public static String format(final ZonedDateTime date, final String formatString) {
        if (date == null) {
            return "";
        }

        return date.format(DateTimeFormatter.ofPattern(formatString));
    }

    /**
     * To local date.
     *
     * @param date the date
     * @return the local date
     */
    public static LocalDate toLocalDate(final Date date) {
        if (date == null) {
            return null;
        } else {
            Date d = date;
            if (date instanceof java.sql.Date) {
                d = new Date(date.getTime());
            }
            return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }


    /**
     * To date.
     *
     * @param date the date
     * @return the date
     */
    public static Date toDate(final LocalDate date) {
        if (date == null) {
            return null;
        } else {
            return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    /**
     * To local date time.
     *
     * @param date the date
     * @return the local date time
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        } else if (date instanceof java.sql.Date) {
            date = new Date(date.getTime());
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Immutably return the given date. If the original value is {@code null}, {@code null}
     * will be returned. Otherwise a new copy of the originally given object will be returned
     *
     * @param original the original
     * @return a new copy of the date
     */
    public static Date immutablyReturn(final Date original) {
        return original == null ? null : new Date(original.getTime());
    }

    /**
     * Adds the day.
     *
     * @param date  the date
     * @param value the value
     * @return the date
     */
    public static Date addDay(final Date date, final int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DAY_OF_YEAR, value);
        return now.getTime();
    }

    /**
     * Equals.
     *
     * @param d1 the d 1
     * @param d2 the d 2
     * @return true, if successful
     */
    public static boolean equals(final Date d1, final Date d2) {
        if (d1 == null && d2 == null) {
            return true;
        }

        if (d1 == null ^ d2 == null) {
            return false;
        }

        return d1.getTime() == d2.getTime();
    }

    /**
     * Gets the current quarter last date.
     *
     * @param date the date
     * @return the current quarter last date
     */
    public static Date getCurrentQuarterLastDate(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int nextQuarterMonth = ((cal.get(Calendar.MONTH)) / 3 + 1) * 3;
        cal.set(Calendar.MONTH, nextQuarterMonth);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);

        return cal.getTime();
    }

    /**
     * Gets the current month last date.
     *
     * @param date the date
     * @return the current month last date
     */
    public static Date getCurrentMonthLastDate(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);

        return cal.getTime();
    }

    /**
     * Gets the next quarter last date.
     *
     * @param date the date
     * @return the next quarter last date
     */
    public static Date getNextQuarterLastDate(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int nextQuarterMonth = ((cal.get(Calendar.MONTH)) / 3 + 1) * 3;
        cal.set(Calendar.MONTH, nextQuarterMonth);
        cal.add(Calendar.MONTH, 3);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * Gets the current quarter first date.
     *
     * @param date the date
     * @return the current quarter first date
     */
    public static Date getCurrentQuarterFirstDate(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentQuarterMonth = ((cal.get(Calendar.MONTH)) / 3) * 3;
        cal.set(Calendar.MONTH, currentQuarterMonth);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * Gets the current year last date.
     *
     * @return the current year last date
     */
    public static Date getCurrentYearLastDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);
        return cal.getTime();
    }
}
