package com.materialdoc.utils;

import android.util.Log;

import com.materialdoc.BuildConfig;

public final class Logger {

    private static final String LOG_PREFIX = "materialdoc_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;
    private static boolean loggingEnabled = false;

    static {
        if (BuildConfig.DEBUG) {
            loggingEnabled = true;
        }
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void e(final String tag, String message, Throwable cause) {
        if (loggingEnabled) {
            if (Log.isLoggable(tag, Log.ERROR)) {
                Log.e(tag, "[" + message + "]", cause);
            }
        }
    }

    public static void e(final String tag, String message) {
        if (loggingEnabled) {
            if (Log.isLoggable(tag, Log.ERROR)) {
                Log.e(tag, message);
            }
        }
    }


    public static void w(final String tag, String message, Throwable cause) {
        if (loggingEnabled) {
            if (Log.isLoggable(tag, Log.WARN)) {
                Log.w(tag, "[" + message + "]", cause);
            }
        }
    }

    public static void w(final String tag, String message) {
        if (loggingEnabled) {
            if (Log.isLoggable(tag, Log.WARN)) {
                Log.w(tag, message);
            }
        }
    }

    public static void i(final String tag, String message, Throwable cause) {
        if (loggingEnabled) {
            if (Log.isLoggable(tag, Log.INFO)) {
                Log.i(tag, "[" + message + "]", cause);
            }
        }
    }

    public static void i(final String tag, String msg) {
        if (loggingEnabled) {
            if(Log.isLoggable(tag, Log.INFO)) {
                Log.i(tag, msg);
            }
        }
    }

    public static void d(final String tag, String message, Throwable cause) {
        if (loggingEnabled) {
            if(Log.isLoggable(tag, Log.DEBUG)) {
                Log.d(tag, message, cause);
            }
        }
    }

    public static void d(final String tag, String message) {
        if (loggingEnabled) {
            if(Log.isLoggable(tag, Log.DEBUG)) {
                Log.d(tag, message);
            }
        }
    }

    public static void v(final String tag, String message, Throwable cause) {
        if (loggingEnabled) {
            if(Log.isLoggable(tag, Log.VERBOSE)) {
                Log.v(tag, message, cause);
            }
        }
    }

    public static void v(final String tag, String message) {
        if (loggingEnabled) {
            if(Log.isLoggable(tag, Log.VERBOSE)) {
                Log.v(tag, message);
            }
        }
    }
}
