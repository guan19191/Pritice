package sgcf.zz.com.pritice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;

/**
 *
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {

    List<HomeArticleListBean.DatasBean> list;
    Context context;

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
    public void onBindViewHolder(@NonNull HomeAdapter.Holder holder, int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_author.setText(list.get(position).getAuthor());
        holder.tv_date.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_author;
        public TextView tv_date;

        public Holder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }


}
