package org.dr_romantic.main;

import org.dr_romantic.gui.CustomGui;
import org.dr_romantic.gui.Lang;
import org.dr_romantic.Utils.WebUtils;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

public class DiscordWebhookSender {
    private static Lang locale;
    private static final Logger LOG = Logger.getGlobal();
    public static void main(String[] args) throws IOException, ParseException{

        locale = Locale.getDefault().getCountry().matches("KR|US|JP") ? Lang.valueOf(Locale.getDefault().getCountry()) : Lang.KR;
        String webhookContents = WebUtils.requestWebhookInfo();
        if(webhookContents.equals("CANCELED")) { return;}
        System.out.println(Locale.getDefault().getCountry());
        new CustomGui(webhookContents);

    }

    public static Lang getLangPack(){
        if(locale == null){
            locale = Locale.getDefault().getCountry().matches("KR|US|JP") ? Lang.valueOf(Locale.getDefault().getCountry()) : Lang.KR;
        }
        return locale;
    }

    public static Logger getLOG(){
        return LOG;
    }
}
