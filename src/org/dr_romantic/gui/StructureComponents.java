package org.dr_romantic.gui;

import org.dr_romantic.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class StructureComponents {
    private static final Font DEFAULT_FONT = new Font("Ariel", Font.PLAIN, 13);
    private static final Lang LANG_PACK = Main.getLangPack();



    public static class DefaultButton extends JButton {
        public DefaultButton(String name, Lang.Messages content, int x, int y){
            setBounds(x, y, 90, 20);
            setText(LANG_PACK.get(content));
            setFont(DEFAULT_FONT);
            setName(name);
            setFocusPainted(false);
        }
    }

    public static class DefaultTextField extends JTextField{
        public DefaultTextField(String name, String initialMsg, int x, int y, int w, int h) {
            setName(name);
            setBounds(x, y, w, h);
            setText(initialMsg);
            setFont(DEFAULT_FONT);
            setForeground(Color.BLACK);
        }
    }

    public static class HintTextField extends JTextField {

        private static final Font activeFont = new Font("Ariel", Font.PLAIN, 13);
        private static final Font nonactiveFont = new Font("Ariel", Font.ITALIC, 13);

        public HintTextField(String name, String hint, int x, int y, int w, int h) {
            setName(name);
            setBounds(x, y, w, h);
            setText(hint);
            setFont(nonactiveFont);
            setForeground(Color.LIGHT_GRAY);

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if(!isEnabled()) return;
                    if (getText().equals(hint)) {
                        setText("");

                    } else {
                        setText(getText());
                    }
                    setFont(activeFont);
                    setForeground(Color.BLACK);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if(!isEnabled()) return;
                    if (getText().equals(hint)|| getText().length()==0) {
                        setText(hint);
                        setFont(nonactiveFont);
                        setForeground(Color.LIGHT_GRAY);
                    } else {
                        setText(getText());
                        setFont(activeFont);
                        setForeground(Color.BLACK);
                    }
                }
            });
        }
    }

    public static class DefaultInputPanePopup extends JOptionPane {
        JLabel label;
        Lang.Messages title;
        int type;
        public DefaultInputPanePopup(Lang.Messages msg, Lang.Messages title, int type) {
            setText(LANG_PACK.get(msg));
            this.type = type;
            this.title = title;
        }

        public void setText(String msg){
            this.label = new JLabel(msg == null ? "" : msg);
            label.setFont(new Font("Ariel", Font.PLAIN, 13));
        }

        public String open() {
            return showInputDialog(null, label, LANG_PACK.get(title), type);
        }
    }

    public static class DefaultInfoMessagePanePopup extends JOptionPane {
        JLabel label;
        Icon icon;
        Lang.Messages title;
        public DefaultInfoMessagePanePopup(Lang.Messages msg, Lang.Messages title, Icon icon) {
            this.title = title;
            this.icon = icon;
            setText(LANG_PACK.get(msg));
        }

        public void setText(String msg){
            this.label = new JLabel(msg == null ? "" : msg);
            label.setFont(new Font("Ariel", Font.PLAIN, 13));
        }

        public void open() {
            showMessageDialog(null, label, LANG_PACK.get(title), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static class DefaultErrorMessagePanePopup extends JOptionPane {
        JLabel label;
        Icon icon;
        Lang.Messages title;
        public DefaultErrorMessagePanePopup(Lang.Messages msg, Lang.Messages title, Icon icon) {
            this.title = title;
            this.icon = icon;
            setText(LANG_PACK.get(msg));
        }

        public void setText(String msg){
            this.label = new JLabel(msg == null ? "" : msg);
            label.setFont(new Font("Ariel", Font.PLAIN, 13));
        }

        public void open() {
            showMessageDialog(null, label, LANG_PACK.get(title), JOptionPane.ERROR_MESSAGE);
        }
    }
}
