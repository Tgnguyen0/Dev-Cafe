package app.InitFont;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    Font font;

    // Constructor to set the font
    public CustomTableCellRenderer(Font font) {
        this.font = font;
    }

    // Override the getTableCellRendererComponent method to set the font
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setFont(font);
        return c;
    }
}
