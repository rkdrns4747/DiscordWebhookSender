package org.dr_romantic.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class LogHandler extends StreamHandler {
    Logger textArea = null;

    public LogHandler(Formatter format){
        setFormatter(format);
    }

    public void setTarget(Logger textArea) {
        this.textArea = textArea;
    }

    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();

        if (textArea != null) {
            textArea.appendText(getFormatter().format(record));
        }
    }
}
