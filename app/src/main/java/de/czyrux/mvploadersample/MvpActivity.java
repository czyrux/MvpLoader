package de.czyrux.mvploadersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import de.czyrux.mvploadersample.base.BasePresenterActivity;
import de.czyrux.mvploadersample.base.Presenter;
import de.czyrux.mvploadersample.base.PresenterFactory;
import de.czyrux.mvploadersample.pager.PagerFragment;
import de.czyrux.mvploadersample.single.SimpleFragment;
import de.czyrux.mvploadersample.single.SimplePresenter;
import de.czyrux.mvploadersample.single.SimplePresenterFactory;
import de.czyrux.mvploadersample.single.SimpleView;

public class MvpActivity extends BasePresenterActivity<SimplePresenter> implements SimpleView {
    private static final String TAG = "MvpActivity";

    private Presenter<SimpleView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        Log.e(TAG, "onCreate-" + tag());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart-" + tag());
        presenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
        Log.e(TAG, "onStop-" + tag());
    }

    @Override
    protected String tag() {
        return "activity";
    }

    @Override
    protected PresenterFactory<SimplePresenter> getPresenterFactory() {
        return new SimplePresenterFactory(tag());
    }

    @Override
    protected void onPresenterPrepared(SimplePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_single) {
            replaceFragment(SimpleFragment.newInstance("Single", ColorPicker.getRandomColor()));
            return true;
        } else if (id == R.id.menu_pager) {
            replaceFragment(PagerFragment.newInstance(4));
            return true;
        } else if (id == R.id.menu_activity) {
            startActivity(new Intent(this, MvpActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
