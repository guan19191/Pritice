package sgcf.zz.com.pritice.adapter.Holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.List;

import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.adapter.TypeAbstractViewHolder;
import sgcf.zz.com.pritice.bean.HomeBannerDataBean;

public class HomeBannerHolder extends TypeAbstractViewHolder<List<HomeBannerDataBean>> {
    ConvenientBanner<HomeBannerDataBean> convenientBanner;
    Context context;

    public HomeBannerHolder(View itemView, Context context) {
        super(itemView);
        convenientBanner = itemView.findViewById(R.id.banner);
        this.context = context;
    }

    @Override
    public void bindHolder(List<HomeBannerDataBean> model) {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView, context);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_home_banner;
            }
        }, model);
    }

    public class LocalImageHolderView extends Holder<HomeBannerDataBean> {
        private ImageView imageView;
        Context context;

        public LocalImageHolderView(View itemView, Context context) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.iv_banner);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void updateUI(HomeBannerDataBean data) {
//            imageView.setImageResource(data);
            Glide.with(context).load(data.getImagePath()).into(imageView);
        }
    }
}

