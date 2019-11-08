package com.example.view;

import com.example.response.BelanjaResponse;
import com.example.response.VerifResponse;

public interface VerifView {
    void onSuccess();
    void onError(String message);
    void getVerifData(VerifResponse verifResponse);
}
