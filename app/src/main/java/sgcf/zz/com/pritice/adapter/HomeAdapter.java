package sgcf.zz.com.pritice.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;

/**
 *
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {

    List<HomeArticleListBean.DatasBean> list;
    Context context;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(List<Integer> list, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HomeAdapter(List<HomeArticleListBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public HomeAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new Holder(View.inflate(context, R.layout.item_home_article, null));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.Holder holder, final int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_author.setText(list.get(position).getAuthor());
        holder.tv_desc.setText(list.get(position).getDesc());

        Date date = new Date(list.get(position).getPublishTime());
        holder.tv_date.setText(date.toLocaleString());
        Glide.with(holder.iv_img).load(list.get(position).getEnvelopePic()).into(holder.iv_img);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rv_sub_list.setLayoutManager(linearLayoutManager);


        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        if (position == list.size() - 1) {

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(list,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_author;
        public TextView tv_date;
        public TextView tv_desc;
        public ImageView iv_img;
        private RecyclerView rv_sub_list;

        public Holder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            rv_sub_list = itemView.findViewById(R.id.rv_sub_list);
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }


}
