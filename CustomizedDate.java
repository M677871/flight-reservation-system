package oopproject;

public class CustomizedDate {
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minute;

    // Default constructor using a try-catch to guard initialization
    public CustomizedDate() {
        try {
            // Default to 1st January 2024, 00:00
            setCustomizedDate(2024, 1, 1, 0, 0);
        } catch (Exception e) {
            System.out.println("Error initializing default CustomizedDate: " + e.getMessage());
        }
    }

    // Parameterized constructor that uses the setCustomizedDate method
    public CustomizedDate(int year, int month, int day, int hours, int minute) {
        try {
            setCustomizedDate(year, month, day, hours, minute);
        } catch (Exception e) {
            System.out.println("Error initializing CustomizedDate: " + e.getMessage());
        }
    }

    // Sets all date and time values together
    public void setCustomizedDate(int year, int month, int day, int hours, int minute) {
        try {
            setYear(year);
            setMonth(month);
            setDay(day);
            setHours(hours);
            setMinute(minute);
        } catch (Exception e) {
            System.out.println("Error setting CustomizedDate: " + e.getMessage());
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        // Validate that the year is within the accepted range.
        if (year >= 2024 && year <= 2026) {
            this.year = year;
        } else {
            this.year = 2024;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            this.month = 1;
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (isValidDay(day)) {
            this.day = day;
        } else {
            this.day = 1;
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours >= 0 && hours <= 23) {
            this.hours = hours;
        } else {
            this.hours = 0;
        }
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if (minute >= 0 && minute <= 59) {
            this.minute = minute;
        } else {
            this.minute = 0;
        }
    }

    // Compares both date and time values for equality
    public boolean equalsDateAndTime(CustomizedDate otherDate) {
        return year == otherDate.year
                && month == otherDate.month
                && day == otherDate.day
                && hours == otherDate.hours
                && minute == otherDate.minute;
    }

    // Compares only the date values for equality
    public boolean equalsDate(CustomizedDate date) {
        return year == date.year
                && month == date.month
                && day == date.day;
    }

    // Compares this date/time with another to see if this date/time is earlier.
    public boolean isBefore(CustomizedDate otherDate) {
        if (year < otherDate.year) {
            return true;
        } else if (year > otherDate.year) {
            return false;
        }

        if (month < otherDate.month) {
            return true;
        } else if (month > otherDate.month) {
            return false;
        }

        if (day < otherDate.day) {
            return true;
        } else if (day > otherDate.day) {
            return false;
        }

        if (hours < otherDate.hours) {
            return true;
        } else if (hours > otherDate.hours) {
            return false;
        }

        if (minute < otherDate.minute) {
            return true;
        } else if (minute > otherDate.minute) {
            return false;
        }
        return false;
    }

    // Returns the date as a formatted string.
    public String toString() {
        try {
            return day + "/" + month + "/" + year + " - " +
                   ((hours < 10) ? "0" + hours : hours) + ":" +
                   ((minute < 10) ? "0" + minute : minute);
        } catch (Exception e) {
            return "Error displaying date: " + e.getMessage();
        }
    }

    // Checks if the current year is a leap year.
    private boolean isLeapYear() {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Validates if the provided day is valid for the current month and year.
    public boolean isValidDay(int day) {
        boolean result = false;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                result = (day >= 1 && day <= 31);
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                result = (day >= 1 && day <= 30);
                break;

            case 2:
                if (isLeapYear() && day >= 1 && day <= 29) {
                    result = true;
                } else if (day >= 1 && day <= 28) {
                    result = true;
                } else {
                    result = false;
                }
                break;
        }
        return result;
    }
}
