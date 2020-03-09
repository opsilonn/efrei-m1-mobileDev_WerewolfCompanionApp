package com.mobiledevelopment.werewolf.api;

import androidx.annotation.StringRes;

import com.mobiledevelopment.werewolf.model.Role;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class API_Util
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






    /**
     * Reads a Stream ! I guess...
     * @param is InputStream we want to read
     * @return euuuh...
     * @throws IOException attention !
     */
    public static String readStream(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line =r.readLine())
        {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
