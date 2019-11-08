package com.example.presenter;

import com.example.helper.ApiInterface;
import com.example.response.SaldoResponse;
import com.example.view.SaldoView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SaldoPresenter {
    private SaldoView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public SaldoPresenter(SaldoView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getSaldo(String no_kartu){
        compositeDisposable.add(apiInterface.main(no_kartu)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<SaldoResponse>() {
                    @Override
                    public void onNext(SaldoResponse saldoResponse) {
                        view.getSaldo(saldoResponse);
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
