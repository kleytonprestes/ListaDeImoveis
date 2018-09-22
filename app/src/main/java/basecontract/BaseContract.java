package basecontract;

import android.content.Context;

/**
 * Created by kleyton on 16/01/18.
 */

public class BaseContract {

    public interface Presenter<V> {
        void attachView(V view);

        void detachView();
    }

    public interface View {
        Context getContext();
    }
}
