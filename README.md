[![Build Status](https://travis-ci.org/bohdan-leonchyk/skypebot.svg?branch=master)](https://travis-ci.org/bohdan-leonchyk/skypebot)

# Skype Bot
### Sends notification into Skype when Steam friend starts playing the game

It works only with old accounts (non registered in Microsoft Skype account)

#### Fill information to run:
- _Application/steamNickname_
- _Application/steamId_
- _SteamService/API_KEY_
- _SkypeService/USERNAME_
- _SkypeService/PASSWORD_
- _SkypeService/CHAT_ID (for your group chat)_

Analyzes state every 20 seconds and send messages into skype group in a case steamId-user is currently playing.
