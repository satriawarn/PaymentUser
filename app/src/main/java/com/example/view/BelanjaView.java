package com.example.view;

import com.example.response.BelanjaResponse;

public interface BelanjaView {
    void onSuccess();
    void onError(String message);
    void getBelanja(BelanjaResponse belanjaResponse);

}
