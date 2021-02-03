package org.dr_romantic.gui;

import java.util.HashMap;
import java.util.Map;


public enum Lang {
    KR(Map.ofEntries(
            Map.entry(Messages.ERROR_GENERAL_TITLE, "오류"),
            Map.entry(Messages.ERROR_GENERAL, "오류가 발생했습니다."),
            Map.entry(Messages.ERROR_INVALID_WEBHOOK_LINK, "잘못된 형식의 웹훅 링크입니다."),
            Map.entry(Messages.ERROR_WEBHOOK_NOT_FOUND, "존재하지 않는 웹훅입니다."),
            Map.entry(Messages.ERROR_IMAGE_NOT_FOUND, "이미지를 찾을 수 없습니다."),
            Map.entry(Messages.ERROR_CHANNEL_NOT_FOUND, "채널을 찾을 수 없습니다."),
            Map.entry(Messages.V_ERROR_LENGTH_INVALID, "<html>웹훅 이름 또는 내용의 길이가 너무 길거나 너무 짧습니다.<br>" +
                                                          "웹훅 이름 길이(최대 80자): {$USERNAME_LENGTH}자<br>" +
                                                          "웹훅 내용 길이(최대 2000자): {$CONTENT_LENGTH}자</html>"),
            Map.entry(Messages.TITLE, "웹훅 전송기"),
            Map.entry(Messages.WEBHOOK_ID_LABEL, "웹훅 ID:"),
            Map.entry(Messages.WEBHOOK_TOKEN_LABEL, "웹훅 Token:"),
            Map.entry(Messages.CHANNEL_ID_LABEL, "채널 ID:"),
            Map.entry(Messages.BOT_TOKEN_LABEL, "권한 봇 Token:"),
            Map.entry(Messages.CHANGE_WEBHOOK, "웹훅 변경"),
            Map.entry(Messages.CHECK_CHANNEL_AVAILABILITY, "확인"),
            Map.entry(Messages.WEBHOOK_CHANGE_SUCCESS_TITLE, "완료"),
            Map.entry(Messages.WEBHOOK_SEND_SUCCESS_TITLE, "전송 완료"),
            Map.entry(Messages.WEBHOOK_CHANGE_SUCCESS, "웹훅 변경을 완료했습니다."),
            Map.entry(Messages.WEBHOOK_SEND_SUCCESS, "성공적으로 메시지를 전송했습니다."),
            Map.entry(Messages.AVATAR_LABEL, "프로필 이미지 URL"),
            Map.entry(Messages.SHOW_AVATAR_PREVIEW, "미리보기"),
            Map.entry(Messages.USERNAME_LABEL,  "웹훅 이름:"),
            Map.entry(Messages.CONTENT_LABEL, "보낼 내용:"),
            Map.entry(Messages.SEND, "전송"),
            Map.entry(Messages.WEBHOOK_SET_SCREEN_TITLE, "웹훅 지정"),
            Map.entry(Messages.WEBHOOK_POPUP_MSG, "사용할 웹훅 링크를 입력하세요:")
    )),
    US(Map.ofEntries(
            Map.entry(Messages.ERROR_GENERAL_TITLE, "ERROR"),
            Map.entry(Messages.ERROR_GENERAL, "An error occurred."),
            Map.entry(Messages.ERROR_INVALID_WEBHOOK_LINK, "The URL provided is invalid."),
            Map.entry(Messages.ERROR_WEBHOOK_NOT_FOUND, "Could not find valid Webhook."),
            Map.entry(Messages.ERROR_CHANNEL_NOT_FOUND, "Could not find the channel."),
            Map.entry(Messages.ERROR_IMAGE_NOT_FOUND, "Could not find valid Image."),
            Map.entry(Messages.V_ERROR_LENGTH_INVALID, "<html>The length of Webhoon Name or Message is invalid.<br>" +
                                                          "Webhook Name(up to 80char): {$USERNAME_LENGTH}char<br>" +
                                                          "Message(Up to 2000char, at least 1 char): {$CONTENT_LENGTH}char</html>"),
            Map.entry(Messages.TITLE, "Webhook Sender"),
            Map.entry(Messages.WEBHOOK_ID_LABEL, "Webhook ID:"),
            Map.entry(Messages.WEBHOOK_TOKEN_LABEL, "Webhook Token:"),
            Map.entry(Messages.CHANNEL_ID_LABEL, "Channel ID:"),
            Map.entry(Messages.BOT_TOKEN_LABEL, "Bot Token:"),
            Map.entry(Messages.CHANGE_WEBHOOK, "Change Webhook"),
            Map.entry(Messages.CHECK_CHANNEL_AVAILABILITY, "Check"),
            Map.entry(Messages.WEBHOOK_CHANGE_SUCCESS_TITLE, "Success"),
            Map.entry(Messages.WEBHOOK_SEND_SUCCESS_TITLE, "Success"),
            Map.entry(Messages.WEBHOOK_CHANGE_SUCCESS, "Webhook successfully changed."),
            Map.entry(Messages.WEBHOOK_SEND_SUCCESS, "Successfully sent out the message."),
            Map.entry(Messages.SEND, "Send"),
            Map.entry(Messages.AVATAR_LABEL, "Avatar Image URL:"),
            Map.entry(Messages.SHOW_AVATAR_PREVIEW, "Preview"),
            Map.entry(Messages.USERNAME_LABEL,  "Webhook Name:"),
            Map.entry(Messages.CONTENT_LABEL, "Message:"),
            Map.entry(Messages.WEBHOOK_SET_SCREEN_TITLE, "Set Webhook"),
            Map.entry(Messages.WEBHOOK_POPUP_MSG, "Type the URL of Webhook you're going to use:")
    )),

    JP(Map.ofEntries(
            Map.entry(Messages.ERROR_GENERAL_TITLE, "エラー"),
            Map.entry(Messages.ERROR_GENERAL, "エラーが発生しました。"),
            Map.entry(Messages.ERROR_INVALID_WEBHOOK_LINK, "利用できない形式のリンク形式です."),
            Map.entry(Messages.ERROR_WEBHOOK_NOT_FOUND, "無効なウェブフックです."),
            Map.entry(Messages.ERROR_CHANNEL_NOT_FOUND, "存在しないチャンネル"),
            Map.entry(Messages.ERROR_IMAGE_NOT_FOUND, "無効なイメージです"),
            Map.entry(Messages.V_ERROR_LENGTH_INVALID, "<html>ウェブフック名もしくは内容の長さが無効です。<br>" +
                                                          "ウェブフック名(最大80字): {$USERNAME_LENGTH}字<br>" +
                                                          "内容(最大2000字, 最小1字): {$CONTENT_LENGTH}字</html>"),
            Map.entry(Messages.TITLE, "Discordウェブフック送信機"),
            Map.entry(Messages.WEBHOOK_ID_LABEL, "ウェブフック ID:"),
            Map.entry(Messages.WEBHOOK_TOKEN_LABEL, "ウェブフック Token:"),
            Map.entry(Messages.CHANNEL_ID_LABEL, "チャンネル ID:"),
            Map.entry(Messages.BOT_TOKEN_LABEL, "ボット Token:"),
            Map.entry(Messages.CHANGE_WEBHOOK, "ウェブフック変更"),
            Map.entry(Messages.CHECK_CHANNEL_AVAILABILITY, "チャック"),
            Map.entry(Messages.WEBHOOK_CHANGE_SUCCESS_TITLE, "変更完了"),
            Map.entry(Messages.WEBHOOK_SEND_SUCCESS_TITLE, "送信完了"),
            Map.entry(Messages.WEBHOOK_CHANGE_SUCCESS, "ウェブフックを変更しました."),
            Map.entry(Messages.WEBHOOK_SEND_SUCCESS, "送信が正常に処理されました。"),
            Map.entry(Messages.SEND, "送信"),
            Map.entry(Messages.AVATAR_LABEL, "アバターイメージURL:"),
            Map.entry(Messages.SHOW_AVATAR_PREVIEW, "プレヴュー"),
            Map.entry(Messages.USERNAME_LABEL,  "ウェブフック名:"),
            Map.entry(Messages.CONTENT_LABEL, "内容:"),
            Map.entry(Messages.WEBHOOK_SET_SCREEN_TITLE, "ウェブフック指定"),
            Map.entry(Messages.WEBHOOK_POPUP_MSG, "ウェブフック固有リンクを入力してください：")
    ))
    ;


    <K, V> Lang(Map<K,V> map) {
        this.map = map;
    }
    private final Map map;
    public String get(Messages type){
        return (String) this.map.get(type);
    }
    public enum Messages{
        NULL,
        ERROR_GENERAL_TITLE,
        ERROR_GENERAL,
        ERROR_INVALID_WEBHOOK_LINK,
        ERROR_WEBHOOK_NOT_FOUND,
        ERROR_CHANNEL_NOT_FOUND,
        ERROR_IMAGE_NOT_FOUND,
        V_ERROR_LENGTH_INVALID,
        TITLE,
        WEBHOOK_ID_LABEL,
        WEBHOOK_TOKEN_LABEL,
        BOT_TOKEN_LABEL,
        CHANNEL_ID_LABEL,
        CHANGE_WEBHOOK,
        CHECK_CHANNEL_AVAILABILITY,
        SEND,
        WEBHOOK_SET_SCREEN_TITLE,
        WEBHOOK_CHANGE_SUCCESS_TITLE,
        WEBHOOK_SEND_SUCCESS_TITLE,
        WEBHOOK_CHANGE_SUCCESS,
        WEBHOOK_SEND_SUCCESS,
        AVATAR_LABEL,
        SHOW_AVATAR_PREVIEW,
        USERNAME_LABEL,
        CONTENT_LABEL,
        WEBHOOK_POPUP_MSG
    }
}


