package com.example.presenter;

import com.example.helper.ApiInterface;
import com.example.response.NomorResponse;
import com.example.view.NomorView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NomorPresenter {
    private NomorView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public NomorPresenter(NomorView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getNoHp(String no_kartu){
        compositeDisposable.add(apiInterface.nohp(no_kartu)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<NomorResponse>() {
                    @Override
                    public void onNext(NomorResponse nomorResponse) {
                        view.getNohp(nomorResponse);
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
