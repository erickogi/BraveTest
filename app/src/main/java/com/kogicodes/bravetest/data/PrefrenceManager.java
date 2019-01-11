package com.kogicodes.bravetest.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.kogicodes.bravetest.models.oauth.Token;

public class PrefrenceManager {
    private static final String PREF_NAME = "braveTest";
    private static final String KEY_TOKEN = "bearerToken";
    private static final String KEY_TOKEN_EXPIRY_DATE = "bearerTokenExpiryDate";


    SharedPreferences pref;


    SharedPreferences.Editor editor;


    Context _context;


    int PRIVATE_MODE = 0;


    public PrefrenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveExpiryDate(Token token) {
        editor.putString(KEY_TOKEN, token.getAccessToken());
        editor.putLong(KEY_TOKEN_EXPIRY_DATE, token.getExpiryDate());
        editor.apply();
    }

    public Token getToken() {
        Token token = new Token();
        token.setExpiryDate(pref.getLong(KEY_TOKEN_EXPIRY_DATE, 0));
        token.setAccessToken(pref.getString(KEY_TOKEN, null));
        return token;
    }


}
