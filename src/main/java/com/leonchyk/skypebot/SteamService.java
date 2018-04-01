package com.leonchyk.skypebot;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Bohdan Leonchyk
 */
public abstract class SteamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SteamService.class);
    // API request from https://developer.valvesoftware.com/wiki/Steam_Web_API
    private static final String GET_INFO = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=%s&steamids=%s";

    // Your API key from https://steamcommunity.com/dev/apikey
    public static final String API_KEY = "your_apikey";

    public static String getGameExtraInfo(String steamId) {
        String request = String.format(GET_INFO, API_KEY, steamId);
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(request);
        HttpResponse httpResponse;
        String result = null;

        try {
            httpResponse = client.execute(httpGet);
            ObjectNode node = new ObjectMapper().readValue(httpResponse.getEntity().getContent(), ObjectNode.class);
            List<JsonNode> jsonNodeList = node.findValues("gameextrainfo");
            if (!jsonNodeList.isEmpty()) {
                result = jsonNodeList.get(0).textValue();
            }
        } catch (IOException e) {
            LOGGER.info("SteamService IOException catched.");
            // do nothing
        }
        return result;
    }
}
