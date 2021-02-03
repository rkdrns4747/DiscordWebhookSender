package org.dr_romantic.gui;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.DocumentFilter;

import org.dr_romantic.Utils.ResourceUtils;
import org.dr_romantic.gui.Lang.Messages;
import org.dr_romantic.Utils.WebUtils;
import org.dr_romantic.main.Main;
import org.dr_romantic.gui.StructureComponents.*;
import org.json.simple.parser.ParseException;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.Buffer;
import java.util.Arrays;

public class CustomGui extends JFrame{
    final static Font DEFAULT_FONT = new Font("Ariel", Font.PLAIN, 13);
    private String id;
    private String token;
    private String channelId;
    private String avatar;
    private String username;



    public CustomGui(String webhookContents) throws IOException, InterruptedException {
        final Lang LANG_PACK = Main.getLangPack();

        String[] webhookInfo = webhookContents.split("/");
        id = webhookInfo[0];
        token = webhookInfo[1];
        channelId = webhookInfo[2];
        avatar = webhookInfo[3];
        username = webhookInfo[4];
        setSize( 640,560);
        setTitle(LANG_PACK.get(Messages.TITLE));
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(ResourceUtils.getImageFromPath(ResourceUtils.RESOURCE_PATH+"icon.png"));


        JLabel idLabel = new JLabel(LANG_PACK.get(Messages.WEBHOOK_ID_LABEL));
        idLabel.setFont(DEFAULT_FONT);
        idLabel.setHorizontalAlignment(JLabel.RIGHT);
        idLabel.setBounds(23, 27, 120, 15);

        JTextField idField = new DefaultTextField("webhookId-field", id, 155, 20, 445, 30);
        idField.setEnabled(false);
        idField.setDisabledTextColor(Color.GRAY);


        JLabel tokenLabel = new JLabel(LANG_PACK.get(Messages.WEBHOOK_TOKEN_LABEL));
        tokenLabel.setFont(DEFAULT_FONT);
        tokenLabel.setHorizontalAlignment(JLabel.RIGHT);
        tokenLabel.setBounds(19, 67, 124, 15);

        JTextField tokenField = new DefaultTextField("tokenField", token, 155, 60, 445, 30);
        tokenField.setEnabled(false);
        tokenField.setDisabledTextColor(Color.GRAY);


        JLabel botTokenLabel = new JLabel(LANG_PACK.get(Messages.BOT_TOKEN_LABEL));
        botTokenLabel.setFont(DEFAULT_FONT);
        botTokenLabel.setHorizontalAlignment(JLabel.RIGHT);
        botTokenLabel.setBounds(23, 127, 120, 15);

        JTextField botTokenField = new DefaultTextField("botTokenField", null,155, 120, 350, 30);
        botTokenField.setDisabledTextColor(Color.BLACK);


        JLabel channelIdLabel = new JLabel(LANG_PACK.get(Messages.CHANNEL_ID_LABEL));
        channelIdLabel.setFont(DEFAULT_FONT);
        channelIdLabel.setHorizontalAlignment(JLabel.RIGHT);
        channelIdLabel.setBounds(23, 167, 120, 15);


        JTextField channelIdField = new HintTextField("channelIdField", channelId, 155, 160, 445, 30);
        channelIdField.setDisabledTextColor(Color.GRAY);
        channelIdField.setEnabled(false);


        JLabel avatarLabel = new JLabel(LANG_PACK.get(Messages.AVATAR_LABEL));
        avatarLabel.setFont(DEFAULT_FONT);
        avatarLabel.setHorizontalAlignment(JLabel.RIGHT);
        avatarLabel.setBounds(9, 207, 134, 15);

        //since discord does not support cdn.discord.com, we have to use old cdn.discordapp.com data.
        JTextField avatarField = new HintTextField("avatarField",  avatar != null ? "https://cdn.discordapp.com/avatars/"+id+"/"+avatar+".png" : "", 155, 200, 340, 30);

        JLabel usernameLabel = new JLabel(LANG_PACK.get(Messages.USERNAME_LABEL));
        usernameLabel.setFont(DEFAULT_FONT);
        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        usernameLabel.setBounds(23, 247, 120, 15);

        JTextField usernameField = new HintTextField("username", username, 155, 240, 445, 30);

        JLabel contentLabel = new JLabel(LANG_PACK.get(Messages.CONTENT_LABEL));
        contentLabel.setFont(DEFAULT_FONT);
        contentLabel.setHorizontalAlignment(JLabel.RIGHT);
        contentLabel.setBounds(23, 287, 120, 15);

        JTextArea contentArea = new JTextArea(1, 1);
        JScrollPane contentWrapper = new JScrollPane(contentArea);
        contentWrapper.setBounds(155, 280, 445, 180);

        //buttons

        DefaultButton changeWebhookButton = new DefaultButton("change_webhook", Messages.CHANGE_WEBHOOK, 454, 95);
        changeWebhookButton.setSize(changeWebhookButton.getSize().width + 55, changeWebhookButton.getSize().height);
        changeWebhookButton.addActionListener(e -> {
            String[] newInfo;
            try {
                newInfo = WebUtils.requestWebhookInfo().split("/");
                if(!newInfo[0].equals("CANCELED") && !id.equals(newInfo[0])) {//check if id changed -> token should also be changed if idが違う
                        changeWebhookInfo(newInfo);
                        id = newInfo[0];
                        token = newInfo[1];
                        channelId = newInfo[2];
                        avatar = newInfo[3];
                        username = newInfo[4];
                        idField.setText(newInfo[0]);
                        tokenField.setText(newInfo[1]);
                        channelIdField.setText(newInfo[2]);
                        channelIdField.setEnabled(false);
                        DefaultInfoMessagePanePopup successPopup = new DefaultInfoMessagePanePopup(Messages.WEBHOOK_CHANGE_SUCCESS, Messages.WEBHOOK_CHANGE_SUCCESS_TITLE, null);
                        successPopup.open();

                }
            } catch (IOException | ParseException ex) {
                ex.getStackTrace();
            }
        });


        DefaultButton checkButton = new DefaultButton("check_button", Messages.CHECK_CHANNEL_AVAILABILITY, 509, 120);
        checkButton.setSize(checkButton.getSize().width, 29);
        checkButton.addActionListener(e -> {
            try {
                if(!webhookContents.equals("CANCELED")) {
                    boolean check = WebUtils.checkWebhookPermission(id, botTokenField.getText());
                    if(check) {
                        channelIdField.setEnabled(true);
                        botTokenField.setEnabled(false);
                    }else{
                        channelIdField.setText(channelId);
                        channelIdField.setEnabled(false);
                        botTokenField.setEnabled(true);
                    }
                }
            } catch (IOException | InterruptedException ex) {
                ex.getStackTrace();
            }
        });

        DefaultButton previewButton = new DefaultButton("show_preview", Messages.SHOW_AVATAR_PREVIEW, 499,200);
        previewButton.setSize(previewButton.getSize().width + 10, 29);
        previewButton.addActionListener(e -> {
            try {
                JFrame imagePreviewFrame = new JFrame("image-preview");
                Image pureImage = WebUtils.getImageFromUrl(avatarField.getText());
                Image previewCover = ResourceUtils.getImageFromPath(ResourceUtils.RESOURCE_PATH+"cover.png");
                BufferedImage preview = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
                Graphics previewGraphic = preview.createGraphics();
                previewGraphic.drawImage(pureImage, 0, 0, 512, 512, null);
                previewGraphic.drawImage(previewCover, 0, 0, 512, 512, null);
                previewGraphic.dispose();
                JLabel previewLabel = new JLabel(new ImageIcon(preview));
                imagePreviewFrame.add(previewLabel);
                imagePreviewFrame.pack();
                imagePreviewFrame.setLocationRelativeTo(null);
                imagePreviewFrame.setVisible(true);
            } catch (IOException ex) {
                DefaultErrorMessagePanePopup errorPopup = new DefaultErrorMessagePanePopup(Lang.Messages.ERROR_IMAGE_NOT_FOUND, Lang.Messages.ERROR_GENERAL_TITLE, null);
                errorPopup.open();
                ex.printStackTrace();
            }
        });

        DefaultButton sendButton = new DefaultButton("submit", Messages.SEND, 500, 480);
        sendButton.addActionListener(e ->{
            //check if username, content has appropriate length
            int unCharLength = usernameField.getText().replaceAll("[\\n\\t]", "").length();
            int caCharLength = contentArea.getText().replaceAll("[\\n\\t]", "").length();
            if(unCharLength > 80 || caCharLength < 1
                    || contentArea.getText().length() > 1999){
                DefaultErrorMessagePanePopup errorPopup = new DefaultErrorMessagePanePopup(Messages.NULL, Messages.ERROR_GENERAL_TITLE, null);
                errorPopup.setText(LANG_PACK.get(Messages.V_ERROR_LENGTH_INVALID)
                        .replace("{$USERNAME_LENGTH}", String.valueOf(unCharLength))
                        .replace("{$CONTENT_LENGTH}", String.valueOf(caCharLength == 0 ? caCharLength : contentArea.getText().length())));
                errorPopup.open();
                return;
            }

            //check if webhook still available
            /**
            if(!WebUtils.checkWebhookAvailability(id, token)){
                DefaultInfoMessagePanePopup errorPopup = new DefaultInfoMessagePanePopup(Messages.ERROR_WEBHOOK_NOT_FOUND, Messages.ERROR_GENERAL_TITLE, null);
                errorPopup.open();
                return;
            }

            //check if webhook can change channel
            if(!WebUtils.checkWebhookPermission(id, botTokenField.getText())){
                int result = WebUtils.executeWebhook(usernameField.getText(), contentArea.getText(), avatarField.getText(), id, token);
                //do default send
            }else {

                //change channel, send, and also turn it back.
            }
             **/

            //try {
               // WebUtils.executeWebhook(username.getText(), contentArea.getText(), avatarUrl.getText(), id, token);
            //} catch (IOException ioException) {
               // ioException.printStackTrace();
            //}

        });
        //sendButton.setSize(100, 300);


        /* code for supporting local image. might be used

        DefaultButton chooseAvatarButton = new DefaultButton("select_avatar", Messages.CHOOSE_AVATAR, 130, 130);

        chooseAvatarButton.setSize(chooseAvatarButton.getSize().width + 20, chooseAvatarButton.getSize().height);
        chooseAvatarButton.addActionListener(e -> {
            JFrame fileChooseFrame = new JFrame();
            JFileChooser chooser = new JFileChooser();
            Action details = chooser.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);
            chooser.setPreferredSize(new Dimension(945, 535));
            setFrameFont(chooser.getComponents());
            fileChooseFrame.add(chooser);
            int dialog = chooser.showOpenDialog(null);
            String path = chooser.getSelectedFile() != null ? chooser.getSelectedFile().getPath() : "";
            System.out.println(path);
            //fileChooser.add(new DefaultButton("test", Messages.TEST, 30, 30));
            //fileChooseFrame.setVisible(true);
        });
         */

        DefaultButton testButton = new DefaultButton("submit", Messages.TEST, 300, 480);
        testButton.addActionListener(e -> {
            try {
                System.out.println(WebUtils.checkChannelExist(channelId, botTokenField.getText()));
            } catch (IOException | ParseException ioException) {
                ioException.printStackTrace();
            }
        });

        add(idLabel);
        add(idField);
        add(tokenLabel);
        add(tokenField);
        add(botTokenLabel);
        add(botTokenField);
        add(channelIdLabel);
        add(channelIdField);
        add(checkButton);
        add(changeWebhookButton);
        add(avatarLabel);
        add(avatarField);
        add(previewButton);
        add(usernameLabel);
        add(usernameField);
        add(contentLabel);
        add(contentWrapper);
        //add(chooseAvatarButton);
        add(testButton);
        add(sendButton);
        setVisible(true);
    }

    private static void setFrameFont(Component[] components){
        Arrays.stream(components).forEach(action ->{
            if(action instanceof Container){
                setFrameFont(((Container) action).getComponents());
            }
            action.setFont(DEFAULT_FONT);
        });
    }

    private void changeWebhookInfo(String[] newInfo){

    }
}
