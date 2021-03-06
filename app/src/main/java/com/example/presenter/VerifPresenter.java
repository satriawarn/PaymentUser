package com.example.presenter;

import com.example.helper.ApiInterface;
import com.example.response.BelanjaResponse;
import com.example.response.VerifResponse;
import com.example.view.BelanjaView;
import com.example.view.VerifView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class VerifPresenter {
    private VerifView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public VerifPresenter(VerifView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getVerif(String no_kartu){
        compositeDisposable.add(apiInterface.verifBelanja(no_kartu)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<VerifResponse>() {
                    @Override
                    public void onNext(VerifResponse verifResponse) {
                        view.getVerifData(verifResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.onSuccess();
                    }
                })
        );
    }
}
