package com.mobiledevelopment.werewolf.util;

import androidx.annotation.StringRes;

import com.mobiledevelopment.werewolf.model.Role;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class UtilAPI
{
    public static String JSON_KEY_DECK_ID = "deck_id";
    public static String JSON_KEY_DECK_CARDS = "cards";
    public static String JSON_KEY_DECK_CARDS_CODE = "code";

    // All Stuff related to the API
    private static String url = "https://www.deckofcardsapi.com/api";



    /**
     * Get a number of cards from a deck
     * @param deckID ID of the deck
     * @param cardCount number of cards to draw
     * @return the API's response
     */
    public static String urlDrawCards(String deckID, int cardCount)
    {
        return url + "/deck/" + deckID + "/draw/?count=" + cardCount;
    }


    /**
     * Get a Partial deck
     * @param cards List of cards code
     * @return the API's response
     */
    public static String urlPartialDeck(List<String> cards)
    {
        StringBuilder urlPartialDeck = new StringBuilder(url + "/deck/new/shuffle/?cards=");

        for (String code : cards)
        {
            urlPartialDeck.append(code).append(",");
        }


        return urlPartialDeck.toString();
    }
}
