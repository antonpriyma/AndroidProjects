package com.example.anton.liberies_practice;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class API {
    Type itemsListType=new TypeToken<List<UserInfo>>(){}.getType();
    private static final API INSTANCE=new API();
    private static final Gson GSON = new GsonBuilder()
           // .registerTypeAdapter(Gender.class, new GenderAdapter())
            .create();

    private final Executor executor = Executors.newSingleThreadExecutor();

    private final Service service;

    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private API() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://json-storage.herokuapp.com/")
                .build();
        service = retrofit.create(Service.class);
    }

    static API getInstance() {
        return INSTANCE;
    }

    ListenerHandler<OnUserGetListener> getUser(final String number, final OnUserGetListener listener) {
        final ListenerHandler<OnUserGetListener> handler = new ListenerHandler<>(listener);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Response<ResponseBody> response = service.getUser(number).execute();
                        try (final ResponseBody responseBody = response.body()) {
                            if (response.code() != 200) {
                                throw new IOException("HTTP code " + response.code());
                            }
                            if (responseBody == null) {
                                throw new IOException("Cannot get body");
                            }
                            final String body = responseBody.string();
                            invokeSuccess(handler, parseUsers(body));
                        }
                    } catch (IOException e) {
                        invokeError(handler, e);
                    }
                }
            });
        }
        return handler;
    }

    private void invokeSuccess(final ListenerHandler<OnUserGetListener> handler, final StudentsInfo users) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                OnUserGetListener listener = handler.getListener();
                if (listener != null) {
                    Log.d("API", "listener NOT null");
                    listener.onUserSuccess(users);
                } else {
                    Log.d("API", "listener is null");
                }
            }
        });
    }

    private void invokeError(final ListenerHandler<OnUserGetListener> handler, final Exception error) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                OnUserGetListener listener = handler.getListener();
                if (listener != null) {
                    Log.d("API", "listener NOT null");
                    listener.onUserError(error);
                } else {
                    Log.d("API", "listener is null");
                }
            }
        });
    }

    private StudentsInfo parseUsers(final String body) throws IOException {
        try {
            StudentsInfo result= GSON.fromJson(body, StudentsInfo.class);
            return result;
        } catch (JsonSyntaxException e) {
            throw new IOException(e);
        }
    }

    public interface OnUserGetListener {
        void onUserSuccess(final StudentsInfo users);

        void onUserError(final Exception error);
    }
}
