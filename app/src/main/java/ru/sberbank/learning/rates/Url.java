package ru.sberbank.learning.rates;

/**
 * Created by eugene on 02.05.17.
 */

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.sberbank.learning.rates.networking.CurrenciesList;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class Url extends AsyncTask<Void, Void, CurrenciesList> {

    private CurrenciesStorage storage;

    public Url(CurrenciesStorage storage) {
        this.storage = storage;
    }

    @Override
    protected CurrenciesList doInBackground(Void... params) {
        InputStream is;
        CurrenciesList list = null;

        try {
            String str = "http://www.cbr.ru/scripts/XML_daily.asp";
            URL url = new URL(str);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            is = urlConnection.getInputStream();
            list = CurrenciesList.readFromStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(CurrenciesList currenciesList) {
        if (storage.getListener().get() != null) {
            storage.setLoadedList(currenciesList);
            storage.getListener().get().onDataChanged(storage);
        }
    }
}