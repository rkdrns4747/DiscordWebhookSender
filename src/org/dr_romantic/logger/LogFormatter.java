package org.dr_romantic.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);
        StringBuilder messageBuilder = new StringBuilder(record.getMessage());
        builder.append(getAsDate(record.getMillis()))
            .append(" [")
            .append(record.getLevel())
            .append("] ");


        for(int i = 33; i < messageBuilder.length(); i = i+33){
            messageBuilder.insert(i, "<br>");
            i = i + 4;
        }
        builder.append(messageBuilder.toString().replace("\n", "<br>"));
        switch (record.getLevel().getName()){
            case "WARNING":
                builder.insert(0, "<font face='Courier New' color='#eb0000'>");
                builder.append("</font>");
            case "INFO":
                builder.insert(0, "<font face='Courier New' color='c0c0c0'>");
                builder.append("</font>");
        }
        builder.append("<br>");
        return builder.toString();
    }

    private String getAsDate(long milli){
        SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
        Date result = new Date(milli);
        return format.format(result);

    }
}
