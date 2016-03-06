package de.czyrux.mvploadersample.base;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BasePresenterActivity<P extends Presenter<V>, V> extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<P> {

    private static final String TAG = "base-activity";
    private static final int LOADER_ID = 101;
    private Presenter<V> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart-" + tag());
        presenter.onViewAttached(getPresenterView());
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
        Log.i(TAG, "onStop-" + tag());
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");
        return new PresenterLoader<>(this, getPresenterFactory(), tag());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter) {
        Log.i(TAG, "onLoadFinished");
        this.presenter = presenter;
        onPresenterPrepared(presenter);
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        Log.i(TAG, "onLoaderReset");
        this.presenter = null;
        onPresenterDestroyed();
    }

    protected abstract String tag();

    protected abstract PresenterFactory<P> getPresenterFactory();

    protected abstract void onPresenterPrepared(P presenter);

    protected final void onPresenterDestroyed() {
        // hook for subclasses
    }

    // Override in case of Activity not implementing Presenter<View> interface
    protected V getPresenterView() {return (V) this;}
}
