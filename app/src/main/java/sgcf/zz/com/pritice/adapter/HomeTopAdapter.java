package sgcf.zz.com.pritice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import sgcf.zz.com.pritice.Bean.HomeBannerBean;
import sgcf.zz.com.pritice.R;

/**
 * Created by admin
 * Date:  2018/3/22.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

public class HomeTopAdapter extends RecyclerView.Adapter<HomeTopAdapter.Holder> {
    private List<HomeBannerBean.DataBean> list;
    private Context context;

    public HomeTopAdapter(List<HomeBannerBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_home_banner_rv, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(list.get(position).getUrl()).into(holder.ivBanner);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class Holder extends RecyclerView.ViewHolder {
        ImageView ivBanner;

        public Holder(View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.iv_banner);
        }
    }
}
