package ru.sberbank.learning.rates;

/**
 * Created by eugene on 02.05.17.
 */

import android.app.Application;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class App extends Application {

    private CurrenciesStorage mStorage = new CurrenciesStorage();
    public CurrenciesStorage getStorage() {
        return mStorage;
    }

}
