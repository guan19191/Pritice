package sgcf.zz.com.pritice.adapter.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 */
public class BaseHolder<T> extends RecyclerView.ViewHolder {

    T mData;

    public void setmData(T mData) {
        if (null == mData) {
            return;
        }
        this.mData = mData;
    }

    public BaseHolder(View itemView) {
        super(itemView);
    }
}
