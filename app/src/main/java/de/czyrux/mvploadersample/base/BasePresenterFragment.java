package de.czyrux.mvploadersample.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

public abstract class BasePresenterFragment<P extends Presenter> extends Fragment implements
        LoaderManager.LoaderCallbacks<P> {

    private static final int LOADER_ID = 101;

    // boolean flag to avoid delivering the result twice. Calling initLoader in onActivityCreated makes
    // onLoadFinished will be called twice during configuration change.
    private boolean delivered = false;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("base-fragment", "onActivityCreated-" + tag());
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        Log.i("base-fragment", "onCreateLoader-" + tag());
        return new PresenterLoader<>(getContext(), getPresenterFactory(), tag());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter) {
        Log.i("base-fragment", "onLoadFinished-" + tag());
        if (!delivered) {
            delivered = true;
            onPresenterPrepared(presenter);
        }
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        Log.i("base-fragment", "onLoaderReset-" + tag());
        onPresenterDestroyed();
    }

    protected abstract String tag();

    protected abstract PresenterFactory<P> getPresenterFactory();

    protected abstract void onPresenterPrepared(P presenter);

    protected final void onPresenterDestroyed() {
        // hook for subclasses
    }
}
