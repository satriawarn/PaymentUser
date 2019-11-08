package com.example.presenter;

import com.example.helper.ApiInterface;
import com.example.response.TopUpResponse;
import com.example.view.TopUpView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TopUpPresenter {
    private TopUpView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public TopUpPresenter(TopUpView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void topUp(String no_kartu, String nominal){
        compositeDisposable.add(apiInterface.topup(no_kartu,nominal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<TopUpResponse>() {
                    @Override
                    public void onNext(TopUpResponse topUpResponse) {
                        view.getTopup(topUpResponse);
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
