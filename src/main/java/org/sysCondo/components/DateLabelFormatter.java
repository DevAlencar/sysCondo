package org.sysCondo.components;

import javax.swing.JFormattedTextField.AbstractFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateLabelFormatter extends AbstractFormatter {

    private final String datePattern = "EEE, dd MMM yyyy"; // Formato para exibir como "Sat, 30 Nov 2024"
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        try {
            return dateFormatter.parse(text);
        } catch (ParseException e) {
            throw new ParseException(e.getMessage(), 1);
        }
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime()); // Exibe a data no formato "Sat, 30 Nov 2024"
        }
        return "";
    }
}
