package com.example.view;

import com.example.response.SaldoResponse;

public interface SaldoView {
    void onSuccess();
    void onError(String message);
    void getSaldo(SaldoResponse saldoResponse);
}
