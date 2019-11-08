package com.example.view;

import com.example.response.CekBayarResponse;

public interface CekBayarView {
    void onSuccess();
    void onError(String message);
    void getCekBayar(CekBayarResponse cekBayarResponse);
}
