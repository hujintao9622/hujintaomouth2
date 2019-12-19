package com.bawei.hujintaomouth2.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.hujintaomouth2.R;
import com.bawei.hujintaomouth2.model.bean.ShopBean;
import com.bawei.hujintaomouth2.util.NetUtil;

import java.util.List;

/**
 * 功能:  适配器
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 7:37
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<ShopBean.ResultBean> list;

    public MyAdapter(List<ShopBean.ResultBean> result) {

        list = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ShopBean.ResultBean resultBean = list.get(position);
        holder.name.setText(resultBean.getCommodityName());
        holder.price.setText(resultBean.getPrice()+"");
        NetUtil.getInstance().getPhoto(resultBean.getMasterPic(),holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClicListener != null) {
                    onItemClicListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.it_img);
            name=itemView.findViewById(R.id.it_name);
            price=itemView.findViewById(R.id.it_price);
        }
    }
    OnItemClicListener onItemClicListener;

    public void setOnItemClicListener(OnItemClicListener onItemClicListener) {
        this.onItemClicListener = onItemClicListener;
    }

    public interface OnItemClicListener{
        void onItemClick(int position);
    }
}
