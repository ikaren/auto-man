log4j.rootLogger=INFO,rolling,errlog,stdout

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss} [%-5p] %c{1}.%M:%L-[%X{traceId}]-%m%n

#common log
log4j.appender.rolling=org.apache.log4j.DailyRollingFileAppender
log4j.appender.rolling.File=${r'${application.base}'}/logs/common.log
log4j.appender.rolling.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.rolling.layout=org.apache.log4j.PatternLayout
log4j.appender.rolling.layout.ConversionPattern=%d{HH:mm:ss.SSS} [%-5p] %-20c{1} [%t]%x [%X{traceId}]-%m%n

#error log
log4j.appender.errlog=org.apache.log4j.DailyRollingFileAppender
log4j.appender.errlog.Threshold=ERROR
log4j.appender.errlog.File=${r'${application.base}'}/logs/error.log
log4j.appender.errlog.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.errlog.layout=org.apache.log4j.PatternLayout
log4j.appender.errlog.layout.ConversionPattern=%d{MM-dd HH:mm:ss.SSS} [%-5p] %-20c{1} [%.11t] [%X{traceId}]%x-%m%n




