// package com.likelion.web.custom;

// import java.util.concurrent.atomic.AtomicLong;

// import org.slf4j.Marker;

// import ch.qos.logback.classic.Level;
// import ch.qos.logback.classic.Logger;
// import ch.qos.logback.classic.spi.ILoggingEvent;
// import ch.qos.logback.classic.turbo.TurboFilter;
// import ch.qos.logback.core.spi.FilterReply;

// public class TimeBasedTurboFilter extends TurboFilter {
//     private static final long ONE_MINUTE_MILLIS = 60 * 1000;
//     private final AtomicLong lastTimestamp = new AtomicLong(0);

//     @Override
//     public void start() {
//         super.start();
//         lastTimestamp.set(System.currentTimeMillis());
//     }

//     @Override
//     public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
//         long currentTimestamp = System.currentTimeMillis();
//         long lastTimestampValue = lastTimestamp.get();

//         if (currentTimestamp - lastTimestampValue > ONE_MINUTE_MILLIS) {
//             lastTimestamp.set(currentTimestamp);
//             return FilterReply.NEUTRAL; // Allow the log message through
//         } else {
//             return FilterReply.DENY; // Skip the log message to avoid duplicate entries
//         }
//     }
// }

