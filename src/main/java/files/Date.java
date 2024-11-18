package files;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * The Date class represents a calendar date and provides functionality to validate and manipulate dates.
 * It includes methods for validating date ranges, leap year checks, and comparison.
 *
 * @author Ayaz Naeem
 */
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    // Constant values for date validation and months
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int MINYEAR = 1900;
    public static final int SIX_MONTHS = 6;

    // Month constants
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    /**
     * Constructs a Date object from a string in mm/dd/yyyy format.
     *
     * @param date The date in mm/dd/yyyy format.
     */
    public Date(String date) {
        StringTokenizer dateToken = new StringTokenizer(date, "/");
        month = Integer.parseInt(dateToken.nextToken());
        day = Integer.parseInt(dateToken.nextToken());
        year = Integer.parseInt(dateToken.nextToken());
    }

    /**
     * Constructs a Date object using the current system date.
     */
    public Date() {
        Calendar currentDate = Calendar.getInstance();
        year = currentDate.get(Calendar.YEAR);
        month = currentDate.get(Calendar.MONTH) + 1;
        day = currentDate.get(Calendar.DATE);
    }

    /**
     * Validates if the date of birth is valid.
     * Checks if the year is valid, if the date is not in the future, and if the day is valid for the given month.
     *
     * @return true if the date of birth is valid; false otherwise.
     */
    public boolean validDob() {
        Calendar today = Calendar.getInstance();

        // Check for minimum year and future dates
        if (year < MINYEAR || year > today.get(Calendar.YEAR)
                || (year == today.get(Calendar.YEAR) && month > (today.get(Calendar.MONTH) + 1))
                || (year == today.get(Calendar.YEAR) && month == (today.get(Calendar.MONTH) + 1) && day > today.get(Calendar.DAY_OF_MONTH))) {
            return false;
        }

        // Check if the day is valid for the given month and year
        int validDaysInMonth = daysInMonth(year, month);
        if (day < 1 || day > validDaysInMonth) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the date is valid based on certain conditions:
     * 1. The date is not in the future.
     * 2. The date is not today.
     * 3. The date is within the next 6 months.
     * 4. The date is not on a weekend.
     * 5. The date is valid for the given month.
     *
     * @return true if valid, false otherwise.
     */
    public boolean isValid() {
        Calendar today = Calendar.getInstance();

        // Check if the date is in the past or today
        if (year < today.get(Calendar.YEAR)
                || (year == today.get(Calendar.YEAR) && (month < (today.get(Calendar.MONTH) + 1)
                || (month == (today.get(Calendar.MONTH) + 1) && day < today.get(Calendar.DAY_OF_MONTH))))) {
            return false;
        }

        // Check if it's within the next 6 months
        Calendar withinSixMonth = Calendar.getInstance();
        withinSixMonth.add(Calendar.MONTH, SIX_MONTHS);
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(year, month - 1, day);
        if (appointmentDate.after(withinSixMonth)) {
            return false;
        }

        // Check if the date is on a weekend
        int dayOfWeek = appointmentDate.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return false;
        }

        // Check if the date is valid for the given month
        int validMonthDate = daysInMonth(year, month);
        if (day < 1 || day > validMonthDate) {
            return false;
        }

        return true;
    }

    /**
     * Determines the number of days in the specified month and year.
     *
     * @param year The year to check.
     * @param month The month to check.
     * @return The number of days in the specified month and year.
     */
    public int daysInMonth(int year, int month) {
        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return 31;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    /**
     * Determines if the given year is a leap year.
     *
     * @param year The year to check.
     * @return true if the year is a leap year; false otherwise.
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }

    /**
     * Compares this date to another date for ordering.
     *
     * @param other The other date to compare to.
     * @return A negative integer, zero, or a positive integer as this date is earlier, the same, or later than the other date.
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    /**
     * Checks if this date is equal to another date.
     *
     * @param obj The object to compare.
     * @return true if the dates are equal; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date other = (Date) obj;
        return year == other.year && month == other.month && day == other.day;
    }

    /**
     * Returns a string representation of the date in mm/dd/yyyy format.
     *
     * @return A string representation of the date.
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}
