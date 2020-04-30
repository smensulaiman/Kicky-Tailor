package com.ekattorit.kickytailor.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekattorit.kickytailor.R;
import com.ekattorit.kickytailor.models.ProductModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopRatedProductAdapter extends RecyclerView.Adapter<TopRatedProductAdapter.ViewHolder> {

    private Context context;
    private List<ProductModel> productModels;
    private NewReleaseProductAdapterListener newReleaseProductAdapterListener;

    public TopRatedProductAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    public void setNewReleaseProductAdapterListener(NewReleaseProductAdapterListener newReleaseProductAdapterListener) {
        this.newReleaseProductAdapterListener = newReleaseProductAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ProductModel productModel = productModels.get(position);
        Uri uri = Uri.parse(productModel.getProductImage());

        holder.imgProduct.setImageURI(uri);
        holder.txtTitle.setText(productModel.getProductName());
        holder.txtPrice.setText(productModel.getProductPrice());

        holder.layoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newReleaseProductAdapterListener != null){
                    newReleaseProductAdapterListener.onItemClicked(productModel.getProductId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public void setProductModels(List<ProductModel> productModels){
        this.productModels = productModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProduct)
        SimpleDraweeView imgProduct;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtPrice)
        TextView txtPrice;
        @BindView(R.id.btnMore)
        ImageButton btnMore;
        @BindView(R.id.layoutProduct)
        LinearLayout layoutProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
