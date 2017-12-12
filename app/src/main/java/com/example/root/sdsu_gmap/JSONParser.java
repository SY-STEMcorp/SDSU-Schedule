package com.example.root.sdsu_gmap;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 12/13/17.
 */

public class JSONParser {

    public static void Parse(String JSONString) throws JSONException {
        JSONObject mainJSONObject = new JSONObject(JSONString);
        JSONArray names = mainJSONObject.names();

        List<Course> data = new ArrayList<>();

        for(int i = 0; i < names.length(); i++)
        {
            String name = names.getString(i);

//            try
//            {
//                JSONObject jsonobject = mainJSONObject.getJSONObject(name);
//
//                Course course = new Course( jsonobject.getString("Course"),
//                                            jsonobject.getString("CourseDescription"),
//                                            jsonobject.getString("CourseFormat"),
//                                            jsonobject.getInt("Units"),
//                                            jsonobject.getString("ScheduleID"),
//                                            jsonobject.getString("Instructor"),
//                                            new ArrayList<Lecture>());
//
//
//
//            }
//            catch (JSONException e)
//            {
//                throw e;
//            }
        }
    }

}
