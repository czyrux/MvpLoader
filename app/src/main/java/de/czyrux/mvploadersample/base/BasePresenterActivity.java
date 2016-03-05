package de.czyrux.mvploadersample.base;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BasePresenterActivity<P extends Presenter> extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<P> {

    private static final int LOADER_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        Log.i("base-activity", "onCreateLoader");
        return new PresenterLoader<>(this, getPresenterFactory(), tag());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P data) {
        Log.i("base-activity", "onLoadFinished");
        onPresenterPrepared(data);
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        Log.i("base-activity", "onLoaderReset");
        onPresenterDestroyed();
    }

    protected abstract String tag();

    protected abstract PresenterFactory<P> getPresenterFactory();

    protected abstract void onPresenterPrepared(P presenter);

    protected final void onPresenterDestroyed() {
        // hook for subclasses
    }

}
