package sgcf.zz.com.pritice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.R;

/**
 * Created by admin
 * Date:  2018/3/21.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

public class ArticleAdapter extends BaseAdapter {

    List<HomeArticleBean.DataBean.DatasBean> list;
    Context context;

    public ArticleAdapter(List<HomeArticleBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_article_lv, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tvAuthor.setText("作者:" + list.get(position).getAuthor());
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDesc.setText(list.get(position).getDesc());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = dateFormat.format(list.get(position).getPublishTime());
        holder.tvTime.setText(format);
        return convertView;
    }

    class Holder {
        View convertView;

        TextView tvTitle, tvAuthor, tvDesc, tvTime;

        public Holder(View convertView) {
            this.convertView = tvTitle;
            tvTitle = convertView.findViewById(R.id.tv_title);
            tvAuthor = convertView.findViewById(R.id.tv_author);
            tvDesc = convertView.findViewById(R.id.tv_desc);
            tvTime = convertView.findViewById(R.id.tv_time);
        }
    }
}
