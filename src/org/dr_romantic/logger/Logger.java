package org.dr_romantic.logger;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Logger extends JTextPane {

    public Logger(){
        setContentType("text/html");
        setText(null);
    }

    public void appendText(String text){
        String plainBody = getText().replaceAll("<[/]{0,1}(html|head|body)>", "");
        setText(plainBody +text);
    }
}
