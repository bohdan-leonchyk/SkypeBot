package com.leonchyk.skypebot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.events.Listener;

/**
 * @author Bohdan Leonchyk
 * Works only with non Microsoft registered Skype accounts.
 */
public class SkypeService implements Listener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkypeService.class);
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";
    // This is GroupChatId as example
    private static final String CHAT_ID = "19:9f9f8270ee994f83aefeac27c5645aa5@thread.skype";
    private Skype skype;

    private static class Wrapper {
        static SkypeService INSTANCE = new SkypeService();
    }

    private SkypeService() {
        this.skype = new SkypeBuilder(USERNAME, PASSWORD).withAllResources().build();
    }

    public static SkypeService getInstance() {
        return Wrapper.INSTANCE;
    }

    public void startBot(String steamNickname, String steamId) {
        try {
            skype.login();
            skype.joinChat(CHAT_ID);
            skype.subscribe();
            LOGGER.info(String.format("%s connected.", steamNickname));
            analyzeState(steamNickname, steamId);
        } catch (Exception e) {
            LOGGER.info("Could not connect.");
            e.printStackTrace();
        }
    }

    /**
     * Analyzes state every 20 seconds.
     * Send message in a case steamId-user is currently playing.
     */
    private void analyzeState(String steamNickname, String steamId) {
        AtomicReference<String> cache = new AtomicReference<String>("inactive");
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable activity = () -> {
            String apiStatus = SteamService.getGameExtraInfo(steamId);
            if (apiStatus != null && !cache.get().equals(apiStatus)) {
                try {
                    String message = String.format("%s is playing \"%s\"", steamNickname, apiStatus);
                    cache.set(apiStatus);
                    skype.getChat(CHAT_ID)
                        .sendMessage(message);
                    LOGGER.info(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            } else if (apiStatus == null) {
                cache.set("inactive");
            }
        };
        executor.scheduleAtFixedRate(activity, 0, 20, TimeUnit.SECONDS);
    }

}
