package net.rpgtoolkit.editor.ui;

import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 * A field that consists of a JFormattedTextField with a default formatter that
 * ensures it contains (long) integers. (-2, -1, 0, 1, 2, etc.) Use any time you
 * want a text field in which the user should enter only integers.
 *
 * @author Joel Moore
 */
public class IntegerField extends JFormattedTextField {

    /**
     * Creates a IntegerField with the specified value. This will create and
     * use a Formatter that requires values that are (long) integers.
     *
     * @param value Initial value for the IntegerField
     */
    public IntegerField(Object value) {
        super(getIntegerFormatter());
        this.setValue(value);
    }

    public static NumberFormatter getIntegerFormatter() {
        NumberFormatter intFormatter = new NumberFormatter(
                NumberFormat.getIntegerInstance());
        intFormatter.setValueClass(Long.class);
        return intFormatter;
    }
    
    @Override
    public Long getValue() {
        return (Long)super.getValue();
    }
}
