package sgcf.zz.com.pritice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

/**
 *
 */
public class HomePageAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_TOP_BANNER = 1;//轮播图
    public static final int TYPE_HEADER = 2;//固定按钮
    public static final int TYPE_QUICK_NEWS = 3;//一号快报
    public static final int TYPE_TUTORIALS = 4;//新手教程
    public static final int TYPE_TOP_RECOMMEND = 5;//推荐
    public static final int TYPE_FLOWER_KNOWLEDGE = 6;//新手教程
    private Context context;

    public HomePageAdapter(Context context) {
        this.context = context;

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


}
