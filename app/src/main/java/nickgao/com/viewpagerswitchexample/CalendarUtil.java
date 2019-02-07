package nickgao.com.viewpagerswitchexample;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日历日期转换类
 *
 * @author zhengxiaobin@babytree-inc.com
 * @since 2018/9/27
 */
public class CalendarUtil {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static final long MINUTE_AGO = 60 * 1000; // 1分钟以前

    private static final long HOUR_AGO = 60 * MINUTE_AGO; // 1小时以前

    public static final long DAY_AGO = 24 * HOUR_AGO; // 24小时以前
    public static final long DAYS_14 = 14 * DAY_AGO;
    public final static SimpleDateFormat dateFormater4 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    public static final String TITLE_PATTERN_M_D = "M.d";
    public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();


    public static long getZeroTagTime(long time) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(time);
        return getMidnightMilliseconds(calendar);
    }

    /**
     * 获取当日零点的时间戳
     *
     * @param calendar
     * @return
     */
    public static long getMidnightMilliseconds(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 格式化输入的millis
     *
     * @param millis
     * @param pattern yyyy-MM-dd HH:mm:ss E
     * @return
     */
    public static String dateFormat(long millis, String pattern) {
        SIMPLE_DATE_FORMAT.applyPattern(pattern);
        Date date = new Date(millis);
        String dateString = SIMPLE_DATE_FORMAT.format(date);
        return dateString;
    }





    /**
     * 日历日期转换：
     * 数据库里面索引字段是：yyyyMMdd
     *
     * @param day_num yyyyMMdd
     * @return 当前时间的秒
     */
    public static long dateNum(String day_num) {
        long dayTime = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
            dayTime = sdf.parse(day_num).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayTime;

    }






    /**
     * 毫秒转为日期字符串
     *
     * @param timeMills
     * @return MM/dd
     */
    public static String formatMMdd(long timeMills) {
        SimpleDateFormat dateFormater = new SimpleDateFormat("MM/dd", Locale.getDefault());
        return dateFormater.format(new Date(timeMills));
    }

    /**
     * 日期 字符转换
     *
     * @param date yyyy-MM-dd
     * @return yyyy.MM.dd
     */
    public static String format(String date) {
        return date.replace("-", ".");
    }


}
