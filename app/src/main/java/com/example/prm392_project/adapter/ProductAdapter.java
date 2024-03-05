package com.example.prm392_project.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    private List produList;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public ProductAdapter(List produList, Context mContext) {
        this.produList = produList;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View studentView =
                inflater.inflate(R.layout.product_list_item, parent, false);

        ProductHolder viewHolder = new ProductHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product =  (Product) produList.get(position);
     //  int x = product.getSupplier().getId();
        holder.Id.setText(product.getId()+"");
        holder.Name.setText(product.getName());
        holder.Price.setText(product.getPrice()+"");
        holder.Quantity.setText(product.getQuantity()+"");
        holder.CreatedAt.setText(product.getCreatedAt()+"");
        holder.CreatedBy.setText(product.getCreatedBy()+"");
       // holder.imgProduct.setImageResource((product.getSupplier().getId()));
    }

    @Override
    public int getItemCount() {
        if (produList != null) {
            return produList.size();
        }
        return 0;

    }

    public  class ProductHolder extends RecyclerView.ViewHolder{
        private View itemview;
        public TextView Id,Name,Price,Quantity,CreatedAt,CreatedBy;
        Button btnInfor,btnDelete;
        ImageView imgProduct;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            Id = itemView.findViewById(R.id.txtIdProduct);
            Name = itemView.findViewById(R.id.txtNameProduct);
            Price = itemView.findViewById(R.id.txtPriceProduct);
            Quantity = itemView.findViewById(R.id.txtQuantityProduct);
            CreatedAt = itemView.findViewById(R.id.txtCreatedAtProduct);
            CreatedBy = itemView.findViewById(R.id.txtTaoBoiProduct);
            btnInfor  = itemView.findViewById(R.id.btnInforProduct);
            btnDelete  = itemView.findViewById(R.id.btnDeleteProduct);
            btnInfor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
          //  imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
