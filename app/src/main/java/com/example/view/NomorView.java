package com.example.view;

import com.example.response.NomorResponse;

public interface NomorView {
    void onSuccess();
    void onError(String message);
    void getNohp(NomorResponse nomorResponse);
}
