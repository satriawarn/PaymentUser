package com.example.presenter;

import com.example.helper.ApiInterface;
import com.example.response.BelanjaResponse;
import com.example.response.NomorResponse;
import com.example.view.BelanjaView;
import com.example.view.NomorView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BelanjaPresenter {
    private BelanjaView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public BelanjaPresenter(BelanjaView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void inputBelanja(String no_kartu, String id_transaksi_user){
        compositeDisposable.add(apiInterface.inputBelanja(no_kartu, id_transaksi_user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<BelanjaResponse>() {
                    @Override
                    public void onNext(BelanjaResponse belanjaResponse) {
                        view.getBelanja(belanjaResponse);
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
