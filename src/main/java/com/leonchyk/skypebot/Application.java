package com.leonchyk.skypebot;

public class Application {

    public static void main(String[] args) {
        LoggerConfig.setupLogger();

        String steamName1 = "grizunoid";
        String steamId1 = "76561198003173615";
        SkypeService.getInstance().startBot(steamName1, steamId1);

        String steamName2 = "[EBA]CheG";
        String steamId2 = "76561198003217860";
        SkypeService.getInstance().startBot(steamName2, steamId2);

        String steamName3 = "DrAlan";
        String steamId3 = "76561198003171387";
        SkypeService.getInstance().startBot(steamName3, steamId3);
        
        String steamName4 = "Tavor";
        String steamId4 = "76561198003172267";
        SkypeService.getInstance().startBot(steamName4, steamId4);
        
        String steamName5 = "peet-bull";
        String steamId5 = "76561198017006073";
        SkypeService.getInstance().startBot(steamName5, steamId5);
        
        String steamName6 = "Shuffle";
        String steamId6 = "76561198028709429";
        SkypeService.getInstance().startBot(steamName6, steamId6);
        
        String steamName7 = "Step";
        String steamId7 = "76561198079724881";
        SkypeService.getInstance().startBot(steamName7, steamId7);
    }
}
