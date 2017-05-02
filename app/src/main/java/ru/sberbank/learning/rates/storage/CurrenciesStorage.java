package ru.sberbank.learning.rates.storage;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import ru.sberbank.learning.rates.networking.CurrenciesList;
import ru.sberbank.learning.rates.networking.Currency;

public final class CurrenciesStorage {

    private CurrenciesList mLoadedList;

    public synchronized boolean isReady() {
        return mLoadedList != null;
    }

    public synchronized CurrenciesList getLoadedList() {
        return mLoadedList;
    }

    public synchronized void setLoadedList(CurrenciesList loadedList) {
        mLoadedList = loadedList;
    }

    private WeakReference<mListener> mListener = new WeakReference<>(null);
    public WeakReference<mListener> getListener() {
        return mListener;
    }

    @Nullable
    public synchronized Currency findByCode(@Nullable String code) {
        if (mLoadedList != null && code != null) {
            for (Currency currency: mLoadedList.getCurrencies()) {
                if (currency.getCharCode().equals(code)) {
                    return currency;
                }
            }
        }

        return null;
    }

    public void setListener(mListener listener) {
        mListener = new WeakReference<>(listener);
    }
    public interface mListener {
        void onDataChanged(CurrenciesStorage storage);
    }
}
