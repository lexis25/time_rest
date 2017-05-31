package vk.com.super_cargo.checktime;

import java.util.Calendar;

public class TimeControler {

    private static final int ONE_HOUR_MINUTE = 60;

    public static boolean validateTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        if (hour > 23 | minute > 59) {
            return false;
        } else {
            return true;
        }
    }

    public static int[] checkSumTime(int hour, int minute) {
        int[] time = new int[2];
        Calendar now = Calendar.getInstance();
        int hourNow = now.get(now.HOUR_OF_DAY);
        int minuteNow = now.get(now.MINUTE);

        if (hour < hourNow) {
            time[0] = (24 - hourNow) + hour;

        } else if (hour > hourNow) {
            time[0] = hour - hourNow;
        }

        if (minute == 0) {
            time[0]--;
            time[1] = 60 - minuteNow;
        }

        if (minute < minuteNow & minute != 0) {
            time[0]--;
            time[1] = (60 - minuteNow) + minute;
        }

        if (minute > minuteNow) {
            time[1] = minute - minuteNow;
        }

        return time;
    }

    public static int[] divideTimeUser(int[] time, int users) {
        int[] divideTime = new int[2];
        int sumMinutes;
        if (users != 0) {
            sumMinutes = (((time[0] * ONE_HOUR_MINUTE) + time[1]) / users);
        } else {
            sumMinutes = (time[0] * ONE_HOUR_MINUTE) + time[1];
        }
        if (sumMinutes > 59) {
            divideTime[0] = sumMinutes / ONE_HOUR_MINUTE;
            divideTime[1] = sumMinutes % ONE_HOUR_MINUTE;
        } else {
            divideTime[1] = sumMinutes;
        }

        return divideTime;
    }

    public static int[] endTimeUser(int hour, int minute) {
        int[] endTime = new int[2];
        Calendar timeNow = Calendar.getInstance();
        timeNow.add(timeNow.HOUR_OF_DAY, hour);
        timeNow.add(timeNow.MINUTE, minute);
        endTime[0] = timeNow.get(timeNow.HOUR_OF_DAY);
        endTime[1] = timeNow.get(timeNow.MINUTE);
        return endTime;
    }

}