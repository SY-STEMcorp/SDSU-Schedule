package com.example.root.sdsu_gmap;

import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/13/17.
 */

public class JSONParser {

    public static List<Pair<String, Object>> Parse(String JSONString) {

        List<Pair<String, Object>> data = (List<Pair<String, Object>>) RecursiveJSONParsing(JSONString);

        return data;
    }

    private static Object RecursiveJSONParsing(String str)
    {
        List<Pair<String, Object>> tempdata = new ArrayList<>();

        try{
            JSONArray temparray = new JSONArray(str);

            for(int i = 0; i < temparray.length(); i++)
            {
                Object temp = RecursiveJSONParsing(temparray.getString(i));
                tempdata.add(new Pair<>("JSONArray" + i, temp));
            }

        } catch (JSONException _) {
            try {
                JSONObject tempobject = new JSONObject(str);

                JSONArray names = tempobject.names();

                for(int i = 0; i < names.length(); i++)
                {
                    Object temp = RecursiveJSONParsing(tempobject.getString(names.getString(i)));
                    tempdata.add(new Pair<>(names.getString(i), temp));
                }

            } catch (JSONException _2) {
                return str;
            }
        }

        return tempdata;
    }

    public static void test()
    {
        Parse("{\"test\": [\"hi\": \"hello\", \"egehe\": \"fdsfdsf\"]}");
    }

}

//        for(int i = 0; i < jsonArray.length(); i++)
//       {
//           JSONObject object;
//
//           try {
//               object = jsonArray.getJSONObject(i);
//           } catch (JSONException e) {
//               e.printStackTrace();
//               return null;
//           }
//
//           JSONArray names = object.names();
//
//           for(int n = 0; n < names.length(); n++)
//           {
//               String objname;
//               try {
//                   objname = names.getString(n);
//               } catch (JSONException e) {
//                   e.printStackTrace();
//                   return null;
//               }
//
//               try {
//                   JSONArray temp = object.getJSONArray(objname);
//                   data.add(new Pair<String, Object>(objname, RecursiveJSONParsing(temp)));
//               } catch (JSONException _) {
//                   try {
//                       JSONObject temp = object.getJSONObject(objname);
//                       data.add(new Pair<String, Object>(objname, temp.toString()));
//                   } catch (JSONException e) {
//                       e.printStackTrace();
//                       return null;
//                   }
//               }
//           }
//
//       }
//
//       return data;
