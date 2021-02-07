# DIscordWebhookSender [![CodeFactor](https://www.codefactor.io/repository/github/rkdrns4747/discordwebhooksender/badge/master?s=5b223cdbf6da97491863b9a4852bf46ad6a2c5c2)](https://www.codefactor.io/repository/github/rkdrns4747/discordwebhooksender/overview/master)

디스코드에서 손쉽게 Webhook 메시지를 전송할 수 있는 프로그램으로 사용자에게 친숙하게 GUI로 제공되고 있습니다.

**Java 버전은 최소 Java 11 이상이어야 합니다.**

## [다운로드 (Downloads / ダウンロード)](https://github.com/rkdrns4747/DiscordWebhookSender/releases)

## 모습

![한국어이미지](https://imgur.com/WgoMCBN.png) ![Eng Image](https://imgur.com/KmgUn1b.png) ![日本語イメージ](https://imgur.com/f4VFO2S.png)


## 사용법

0. **[원하는 채널에 웹훅을 생성하세요.](https://support.discord.com/hc/ko/articles/228383668-%EC%9B%B9%ED%9B%85%EC%9D%84-%EC%86%8C%EA%B0%9C%ED%95%A9%EB%8B%88%EB%8B%A4)**

1. **프로그램을 11버전 이상의 자바로 실행하면 다음 창이 표시됩니다.**

![img](https://imgur.com/k9HrGVP.png)

여기에서 빈칸에 사용할 웹훅의 고유 링크를 입력하고 `OK` 버튼을 누르세요. 프로그램에서 웹훅의 ID, 토큰, 채널 등의 정보를 자동으로 불러옵니다.



2. **그 후에는 다음과 같이 창이 표시됩니다.**

![img](https://imgur.com/jGQE3sR.png)

3. **가장 상단의 다음 공간은 방금 입력한 웹훅의 정보를 표시합니다.**

![img](https://imgur.com/8IOzoXn.png)

변경을 원하면 우측 하단의 `웹훅 변경` 버튼을 클릭하여 웹훅 정보를 변경할 수 있습니다. 변경하지 않는다면 해당 부분은 그대로 두셔도 됩니다.

**[선택]** 4. **그 다음의 공간은 권한 봇의 토큰 입력공간과 방금 입력한 웹훅의 채널 ID를 표시합니다.**

![img](https://imgur.com/KfhQsN6.png)

권한 봇의 토큰을 입력하고 `확인` 버튼을 누르면 프로그램이 해당 봇의 유효성을 확인합니다. 유효함이 확인되면 해당 **토큰 필드**는 비활성화(고정) 되며, **채널 ID 필드**가 활성화(입력 가능) 됩니다.
만약 채널을 변경할 필요가 없고 표시된 채널로 보내는 것이라면, 봇 토큰은 입력하지 않아도 됩니다. 권한 봇은 비어있는 봇을 생성하고, *관리자* 권한을 부여하기만 하면 됩니다. 자세한 사항은 [여기](https://abc.xyz) 를 참조하십시오.


5. **그 다음의 공간은 전송 시 메시지에 표시되는 콘텐츠들을 입력합니다.**

![img](https://imgur.com/A37UR6Z.png)


- 첫 필드는 프로필 이미지로 사용할 이미지의 URL을 입력합니다. 웹훅의 이미지를 그대로 사용한다면 입력할 필요가 없으며, 이외의 이미지를 사용할 경우, 해당 이미지의 웹 URL을 붙여넣어 주세요. 웹 이미지 업로드 호스트는 [imgur](https://imgur.com) 을 추천합니다. 해당 URL의 맨 끝에는 `.jpg` 또는 `.png` 가 있어야 합니다.
- 두 번째 필드는 전송 시에 웹훅의 이름으로 표시될 텍스트를 입력합니다. 웹훅의 이름을 그대로 사용한다면 입력할 필요가 없으며, 이외의 텍스트를 이름으로 사용할 경우, 텍스트를 입력해 주세요. **이 필드는 80자를 초과할 수 없습니다.**
- 세 번째 필드는 전송할 메시지를 입력합니다. `**` 또는 `_`, ` ``` ` 와 같은 디스코드 내의 꾸밈문자를 모두 사용할 수 있으며, Tab 역시 사용할 수 있습니다. **이 필드는 필수 입력이며, 2000자를 초과할 수 없습니다.**

6. **모든 입력이 끝났다면, Send 버튼을 통해 메시지를 전송합니다.**
