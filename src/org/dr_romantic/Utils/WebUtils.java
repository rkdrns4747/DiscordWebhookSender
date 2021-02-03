package org.dr_romantic.Utils;

import org.dr_romantic.gui.Lang;
import org.dr_romantic.gui.StructureComponents.*;
import org.dr_romantic.main.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebUtils {
    private static final String URL_FRONT_REGEX = "https://(discordapp|discord)\\.com/api/webhooks/";
    private static final Lang LANG_PACK = Main.getLangPack();

    public static boolean checkWebhookAvailability(String id, String token) throws IOException, ParseException {
        JSONObject responseJSON = getWebhook(id, token);

        return (int)responseJSON.get("response-state") == HttpsURLConnection.HTTP_OK;
    }

    public static boolean checkWebhookPermission(String id, String botToken) throws IOException, InterruptedException {
        JSONObject postJson = new JSONObject();
        HttpResponse response = sendWebhookPatch(id, postJson, botToken);

        return response.statusCode() == HttpsURLConnection.HTTP_OK;
    }

    public static Result changeChannel(String webhookID, String channelID, String botToken) throws IOException, InterruptedException {

        JSONObject patchParams = new JSONObject();
        patchParams.put("channel_id", channelID);

        HttpResponse response = sendWebhookPatch(webhookID, patchParams, botToken);;

        return response.statusCode() == HttpsURLConnection.HTTP_OK ? Result.ALLOW : Result.DENY;
    }

    /**
    public JSONObject createTempWebhook(String channelID) throws IOException, ParseException {
        URL url = new URL("https://discordapp.com/api/channels/"+channelID+"/webhooks");
        JSONObject postJson = new JSONObject();

        postJson.put("name", this.username);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Dr_Romantic_Discord_Webhook");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStream stream = connection.getOutputStream();
        stream.write(postJson.toString().getBytes());
        stream.flush();
        stream.close();

        BufferedReader bR = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String currentline;
        StringBuffer response = new StringBuffer();
        while ((currentline = bR.readLine()) != null) {
            response.append(currentline);
        }
        bR.close();
        connection.getInputStream().close();
        connection.disconnect();
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(String.valueOf(response));
    }
    **/

    public static JSONObject getWebhook(String id, String token) throws IOException, ParseException {
        URL getUrl = new URL("https://discord.com/api/webhooks/"+id+"/"+token);
        HttpsURLConnection connection = (HttpsURLConnection) getUrl.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Dr_Romantic_Discord_Webhook");
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
            JSONObject result = new JSONObject();
            result.put("response-state", connection.getResponseCode());
            return result;
        }

        BufferedReader bR = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String currentline;
        StringBuffer response = new StringBuffer();
        while ((currentline = bR.readLine()) != null) {
            response.append(currentline);
        }
        bR.close();

        connection.getInputStream().close();
        connection.disconnect();
        JSONObject result = (JSONObject) (new JSONParser()).parse(String.valueOf(response));
        result.put("response-state", connection.getResponseCode());
        return result;
    }
    public static String requestWebhookInfo() throws IOException, ParseException {
        String input;
        DefaultInputPanePopup webhookChooser;
        webhookChooser = new DefaultInputPanePopup(Lang.Messages.WEBHOOK_POPUP_MSG, Lang.Messages.WEBHOOK_SET_SCREEN_TITLE, JOptionPane.INFORMATION_MESSAGE);
        input = webhookChooser.open();
        if(input == null){
            return "CANCELED";
        }
        if(input.equals("")
                || !input.replaceAll(URL_FRONT_REGEX, "").matches("[0-9]{18}/[0-9a-zA-Z-_]+")){
            DefaultErrorMessagePanePopup errorPopup = new DefaultErrorMessagePanePopup(Lang.Messages.ERROR_INVALID_WEBHOOK_LINK, Lang.Messages.ERROR_GENERAL_TITLE, null);
            errorPopup.open();
            //JOptionPane.showMessageDialog(null, LANG_PACK.get(Lang.Messages.ERROR_INVALID_WEBHOOK_LINK), "ERROR", JOptionPane.ERROR_MESSAGE);
            input = requestWebhookInfo();
            return input.replaceAll(URL_FRONT_REGEX, "");
        }
        String _result = input.replaceAll(URL_FRONT_REGEX, "");
        JSONObject response = getWebhook(_result.split("/")[0], _result.split("/")[1]);
        if((int)response.get("response-state") != HttpsURLConnection.HTTP_OK){
            DefaultErrorMessagePanePopup errorPopup = new DefaultErrorMessagePanePopup(Lang.Messages.ERROR_WEBHOOK_NOT_FOUND, Lang.Messages.ERROR_GENERAL_TITLE, null);
            errorPopup.open();
            _result = null;
            input = requestWebhookInfo();
            return input.replaceAll(URL_FRONT_REGEX, "");
        }
        return _result + "/" + response.get("channel_id") + "/" + response.get("avatar") + "/" + response.get("name");
    }

    public static int executeWebhook(String username, String content, String avatarUrl, String id, String token) throws IOException {
        URL postUrl = new URL("https://discord.com/api/webhooks/"+id+"/"+token);
        JSONObject postJson = new JSONObject();

        postJson.put("content", content);
        postJson.put("username", username);
        postJson.put("avatar_url", avatarUrl);

        HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Dr_Romantic_Discord_Webhook");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStream stream = connection.getOutputStream();
        stream.write(postJson.toString().getBytes());
        stream.flush();
        stream.close();
        connection.getInputStream().close();
        connection.disconnect();

        return connection.getResponseCode();
    }

    public static Result executeWebhookWithAuth(String username, String content, String avatarUrl,String originChannel, String targetChannel, String id, String token, String botToken)
            throws IOException, ParseException, InterruptedException {
        Result targetPatchResult = changeChannel(id, targetChannel, botToken);
        if(targetPatchResult == Result.DENY){

        }
        URL postUrl = new URL("https://discord.com/api/webhooks/"+id+"/"+token);
        JSONObject postJson = new JSONObject();

        postJson.put("content", content);
        postJson.put("username", username);
        postJson.put("avatar_url", avatarUrl);

        HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Dr_Romantic_Discord_Webhook");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStream stream = connection.getOutputStream();
        stream.write(postJson.toString().getBytes());
        stream.flush();
        stream.close();
        connection.getInputStream().close();
        connection.disconnect();

        return connection.getResponseCode() == HttpsURLConnection.HTTP_OK ? Result.ALLOW : Result.DENY;
    }
    public static Image getImageFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        BufferedImage webImage = ImageIO.read(url);
        if(webImage == null)
            throw new IOException();

        return webImage;
    }
    public static boolean checkChannelExist(String id, String botToken) throws IOException, ParseException{
        URL getUrl = new URL("https://discord.com/api/channels/"+id);
        HttpsURLConnection connection = (HttpsURLConnection) getUrl.openConnection();
        connection.addRequestProperty("Authorization", "Bot "+botToken);  //security issue
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Dr_Romantic_Discord_Webhook");
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");

        return connection.getResponseCode() == HttpsURLConnection.HTTP_OK;


    }

    private static HttpResponse sendWebhookPatch(String webhookID, JSONObject patchParams, String botToken) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://discord.com/api/webhooks/"+webhookID))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(patchParams.toJSONString()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bot "+botToken) //security issue
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }


    public static enum Result {
        ALLOW,
        DENY;
    }
}
