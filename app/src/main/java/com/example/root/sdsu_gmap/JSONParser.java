package com.example.root.sdsu_gmap;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by root on 12/13/17.
 */

public class JSONParser {

    public static HashMap<String, Object> Parse(String JSONString) {

        HashMap<String, Object>  data = (HashMap<String, Object>) RecursiveJSONParsing(JSONString);

        return data;
    }

    private static Object RecursiveJSONParsing(String str)
    {
        HashMap<String, Object>  tempdata = new HashMap<>();

        try{
            JSONArray temparray = new JSONArray(str);

            for(int i = 0; i < temparray.length(); i++)
            {
                Object temp = RecursiveJSONParsing(temparray.getString(i));
                tempdata.put("JSONArray" + i, temp);
            }

        } catch (JSONException _) {
            try {
                JSONObject tempobject = new JSONObject(str);

                JSONArray names = tempobject.names();

                for(int i = 0; i < names.length(); i++)
                {
                    Object temp = RecursiveJSONParsing(tempobject.getString(names.getString(i)));
                    tempdata.put(names.getString(i), temp);
                }

            } catch (JSONException _2) {
                return str;
            }
        }

        return tempdata;
    }

}