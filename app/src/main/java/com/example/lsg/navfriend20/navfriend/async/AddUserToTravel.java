package com.example.lsg.navfriend20.navfriend.async;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lsg.navfriend20.navfriend.MapNavfriend;
import com.example.lsg.navfriend20.navfriend.data.RequestSuccess;
import com.example.lsg.navfriend20.navfriend.data.UserTravel;
import com.example.lsg.navfriend20.navfriend.manager.RestServiceManager;
import com.google.gson.Gson;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import us.monoid.json.JSONException;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import static us.monoid.web.Resty.content;
import static us.monoid.web.Resty.put;

public class AddUserToTravel extends AsyncTask<UserTravel, Void, RequestSuccess> {

    Activity activity;
    UserTravel travel;
    public AddUserToTravel(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected RequestSuccess doInBackground(UserTravel... params) {
        travel = params[0];




        RequestSuccess request;

        try {
            Gson gson = new Gson();
            String json = gson.toJson(travel);

            Resty resty = new Resty();

            JSONResource res = resty.json(RestServiceManager.getInstance().getURI("adduser"), put(content(json)));
            request = gson.fromJson(res.toObject().toString(), RequestSuccess.class);

            return request;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(RequestSuccess success) {


        if(success.isStatus()){
            Intent q = new Intent(activity , MapNavfriend.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", travel.getUser());
            bundle.putSerializable("travel", travel.getTravel());
            q.putExtras(bundle);
            activity.startActivity(q);
        }else{
            Toast.makeText(activity, "SOTTERCESS", Toast.LENGTH_LONG);
        }
    }
}
