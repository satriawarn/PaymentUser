package com.example.view;
;
import com.example.response.TopUpResponse;

public interface TopUpView {
    void onSuccess();
    void onError(String message);
    void getTopup(TopUpResponse topUpResponse);
}
