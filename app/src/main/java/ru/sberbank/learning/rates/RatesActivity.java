package ru.sberbank.learning.rates;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class RatesActivity extends Activity implements CurrenciesStorage.mListener {

    private Url mUrl;
    private CurrenciesStorage mStorage;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        mListView = (ListView) findViewById(R.id.curr_list);
        mStorage = ((App) getApplication()).getStorage();

        if (!mStorage.isReady()) {
            startListening();
            mUrl = new Url(mStorage);
            mUrl.execute();
        } else {
            onDataChanged(mStorage);
        }
    }

    @Override
    public void onDataChanged(CurrenciesStorage storage) {
        mListView.setAdapter(new AdapterList(storage.getLoadedList().getCurrencies()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopListening();
        mUrl = null;
    }

    public void startListening() {
        mStorage.setListener(this);
    }
    public void stopListening() {
        mStorage.setListener(null);
    }
}
