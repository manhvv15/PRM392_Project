package com.example.prm392_project.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.Admin_Product;
import com.example.prm392_project.DetailProduct;
import com.example.prm392_project.R;
import com.example.prm392_project.dal.ProductDAO;
import com.example.prm392_project.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    private List produList;
    private ProductItemListener productItem;
    public void setClickListener(ProductItemListener productItem) {
        this.productItem = productItem;
    }
    // Lưu Context để dễ dàng truy cập
    private Context mContext;
    public int IdProduct = 1 ;
   int productId = 1;
    Product product;

    public ProductAdapter(Product product){
        this.product = product;
    }
    public int getIdProduct() {
        return IdProduct;
    }
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
     //   IdProduct = product.getId();
        holder.Id.setText(product.getId()+"");
        holder.Name.setText(product.getName());
        holder.Price.setText(product.getPrice()+"");
        holder.Quantity.setText(product.getQuantity()+"");
        holder.CreatedAt.setText(product.getCreatedAt()+"");
        holder.CreatedBy.setText(product.getCreatedBy()+"");

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productId = product.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirm Deletion");
                builder.setMessage("Are you sure you want to delete this item?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform deletion here
//                        deleteProduct(product);
                        // Optionally, you may want to remove the item from the list and notify adapter
                        productDAO.delete(product,productId);
                        notifyDataSetChanged();
                        produList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, produList.size());
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });

        holder.btnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 productId = product.getId();

                // Start the detail activity with the productId
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailProduct.class);
                intent.putExtra("IdProduct", productId);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (produList != null) {
            return produList.size();
        }
        return 0;

    }
    ProductDAO productDAO;

    public  class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private View itemview;
        public TextView Id,Name,Price,Quantity,CreatedAt,CreatedBy;
        Button btnInfor,btnDelete;
        ImageView imgProduct;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            productDAO = new ProductDAO();
            product = new Product();
            Id = itemView.findViewById(R.id.txtIdProduct);
            Name = itemView.findViewById(R.id.txtNameProduct);
            Price = itemView.findViewById(R.id.txtPriceProduct);
            Quantity = itemView.findViewById(R.id.txtQuantityProduct);
            CreatedAt = itemView.findViewById(R.id.txtCreatedAtProduct);
            CreatedBy = itemView.findViewById(R.id.txtTaoBoiProduct);
            btnInfor  = itemView.findViewById(R.id.btnInforProduct);
            btnDelete  = itemView.findViewById(R.id.btnDeleteProduct);
//            btnInfor.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    IdProduct = getAdapterPosition()+1;
//                    Context context = itemView.getContext();
//                    Intent intent = new Intent(context, DetailProduct.class);
//                    intent.putExtra("IdProduct",IdProduct);
//                    context.startActivity(intent);
//                }
//            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = itemView.getContext();
                    // Tạo một hộp thoại cảnh báo
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xác nhận xóa");
                    builder.setMessage("Bạn có chắc chắn muốn xóa mục này không?");

                    // Thêm nút xác nhận (Có)
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Thực hiện xóa mục ở đây
                            // Ví dụ: productDAO.delete(productId);
                            productDAO.delete(product,IdProduct);
                            productDAO.getListProduct();
                        }
                    });

                    // Thêm nút hủy bỏ (Không)
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Đóng hộp thoại cảnh báo và không thực hiện hành động xóa
                            dialog.dismiss();
                        }
                    });

                    // Hiển thị hộp thoại cảnh báo
                    builder.show();
                }
            });

        }

        @Override
        public void onClick(View view) {
            if (productItem != null) {
                productItem.onItemClick(view, getAdapterPosition());
            }
        }
    }
    public interface ProductItemListener {
        void onItemClick(View view, int position);


    }
}
