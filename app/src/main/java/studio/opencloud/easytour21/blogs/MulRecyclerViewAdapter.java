package studio.opencloud.easytour21.blogs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.mainspace.MainActivity_X;

/**
 * Created by 英俊的mrsail on 2018/6/29.
 */

public class MulRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int NEW_SIMPLE_TYPE = 0;//单图文模式
    private static final int NEW_MUL_TYPE = 1;//多图文模式
    private static final int NEW_OTHER_TYPE = 2;//多图文模式
    private Context context;
    private List<BowenListBean.BowenListInfo> list;
    private String userTel;



    public MulRecyclerViewAdapter(Context context, List<BowenListBean.BowenListInfo> list, String tel) {
        this.context = context;
        this.list = list;
        userTel = tel;
    }

    //重写getItemViewType方法,通过此方法来判断应该加载是哪种类型布局
//    @Override
//    public int getItemViewType(int position) {
//        int type = list.get(position).getType();
//        switch (type) {
//            case 0:
//                return NEW_SIMPLE_TYPE;
//            case 1:
//                return NEW_MUL_TYPE;
//        }
//        return NEW_OTHER_TYPE;
//    }

    //根据不同的item类型来加载不同的viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
//        switch (viewType) {
//            case NEW_SIMPLE_TYPE:
                return new NewsPhotoViewHolder(inflater.inflate(R.layout.recyclerview_item_type_02, parent, false));
//            case NEW_MUL_TYPE:
//                return new NewsPhotosViewHolder(inflater.inflate(R.layout.recyclerview_item_type_01, parent, false));
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        /**添加响应事件，使点击响应内容可以跳转到其具体内容处**/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"你点击了:"+list.get(position).getTitle(),Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(v.getContext(),CommentActivity.class);
                bundle.putInt("bowenID",list.get(position).getBowenId());
                bundle.putString("userid",list.get(position).getUserNickname());
                bundle.putString("userheadicon",list.get(position).getUserheadIcon());
                bundle.putString("userTel",userTel);

                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });

        //把对应位置的数据得到
        String title = list.get(position).getTitle();
        String time = list.get(position).getTime();
        String author = list.get(position).getUserNickname();
        int zanNum = list.get(position).getZanNumber();
        int collectNum = list.get(position).getCollectedNumber();
        String userPic = list.get(position).getImage();

        //设置图文内容
        ((NewsPhotoViewHolder) holder).tx_news_simple_photos_title.setText(title);
            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_time.setText(time);
          ((NewsPhotoViewHolder) holder).tx_news_simple_photos_author.setText(author);

        Picasso.with(((NewsPhotoViewHolder) holder).tx_head.getContext())
                .load(userPic)
                .into(((NewsPhotoViewHolder) holder).tx_head);

      //  List<String> ls = list.get(position).getList();//这里是json数据中的图片集合，也就是封面。不同类型item的封面图片数量是不一样的
        //  //无论是否单图文，标题和更新时间以及作者不变

//        //如果单图文
//        if (holder instanceof NewsPhotoViewHolder) {
//
//            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_title.setText(title);
//            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_time.setText(time);
//            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_author.setText(author);
////            ((NewsPhotoViewHolder) holder).img_news_simple_photos_01.setImageBitmap(btm_01);//单图文不用遍历直接将图片转换bitmap对象设置到ImageView上
//            return;
//        }
//        //如果多图文
//        if (holder instanceof NewsPhotosViewHolder) {
//            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_title.setText(title);
//            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_time.setText(time);
//            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_author.setText(author);
////            ((NewsPhotosViewHolder) holder).img_news_mul_photos_01.setImageBitmap(btm_01);//多图文需要遍历list将每个图片链接转换成Bitmap对象设置到ImageView上
////            ((NewsPhotosViewHolder) holder).img_news_mul_photos_02.setImageBitmap(btm_02);
////            ((NewsPhotosViewHolder) holder).img_news_mul_photos_03.setImageBitmap(btm_03);
//            return;
//        }


    }
    //具体item数据等于pages*10，每页10条
    @Override
    public int getItemCount() {

        return list.size();
    }

    /**
     * NewsPhotoViewHolder为单图文模式
     */
    class NewsPhotoViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_news_simple_photos_title;//标题
   //     private ImageView img_news_simple_photos_01;//单图文模式的唯一一张图
        private TextView tx_news_simple_photos_time;//单图文模式的更新时间
        private TextView tx_news_simple_photos_author;//单图文模式的新闻作者
        private ImageView tx_head;    //图片封面

        public NewsPhotoViewHolder(View itemView) {
            super(itemView);
            tx_news_simple_photos_title = (TextView) itemView.findViewById(R.id.tx_news_simple_photos_title);//标题
//            img_news_simple_photos_01 = (ImageView) itemView.findViewById(R.id.tx_news_simple_photos_01);//单图文模式的唯一一张图
            tx_news_simple_photos_time = (TextView) itemView.findViewById(R.id.tx_news_simple_photos_time);//单图文模式的更新时间
            tx_news_simple_photos_author = (TextView) itemView.findViewById(R.id.img_news_simple_photos_author);//单图文模式的新闻作者
            tx_head = (ImageView) itemView.findViewById((R.id.tx_head));
        }
    }

//    /**
//     * NewsPhotosViewHolder为多图模式
//     */
//    class NewsPhotosViewHolder extends RecyclerView.ViewHolder {
//        private TextView tx_news_mul_photos_title;//标题
//        //        private ImageView img_news_mul_photos_01;//多图文模式的第一张图
////        private ImageView img_news_mul_photos_02;//多图文模式的第二张图
////        private ImageView img_news_mul_photos_03;//多图文模式的第三张图
//        private TextView tx_news_mul_photos_time;//多图文模式的更新时间
//        private TextView tx_news_mul_photos_author;//多图文模式的新闻作者
//
//        public NewsPhotosViewHolder(View itemView) {
//            super(itemView);
//            tx_news_mul_photos_title = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_title);
////            img_news_mul_photos_01 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_01);
////            img_news_mul_photos_02 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_02);
////            img_news_mul_photos_03 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_03);
//            tx_news_mul_photos_time = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_time);
//            tx_news_mul_photos_author = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_author);
//        }
//    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public ItemClickListener mListener;

    public void setOnItemClickListener(ItemClickListener listener) {
        this.mListener = listener;
    }
}

