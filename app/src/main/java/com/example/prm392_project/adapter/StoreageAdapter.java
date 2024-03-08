package com.example.prm392_project.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.DetailProduct;
import com.example.prm392_project.DetailStorerage;
import com.example.prm392_project.R;
import com.example.prm392_project.model.Product;
import com.example.prm392_project.model.Storage;

import java.util.List;

public class StoreageAdapter extends RecyclerView.Adapter<StoreageAdapter.StoreageHolder>{
    private List<Storage> storageList;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;
    public StoreageAdapter( List<Storage> storageList, Context mContext) {
        this.storageList = storageList;
        this.mContext = mContext;
    }
    long storeId = 1;
    @NonNull
    @Override
    public StoreageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh
        View view =
                inflater.inflate(R.layout.storeage_list, parent, false);

        StoreageAdapter.StoreageHolder viewHolder = new StoreageAdapter.StoreageHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreageHolder holder, int position) {
        Storage storage =  storageList.get(position);
        holder.Id.setText(storage.getId()+"");
        holder.Name.setText(storage.getProduct().getName());
        holder.Price.setText(storage.getProduct().getPrice()+"");
        holder.CreatedAt.setText(storage.getProduct().getCreatedAt()+"");
        holder.btnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeId = storage.getId();

                // Start the detail activity with the productId
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailStorerage.class);
                intent.putExtra("storeId", storeId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (storageList != null) {
            return storageList.size();
        }
        return 0;
    }
    public int IdStorage  = 1;
    public  class StoreageHolder extends RecyclerView.ViewHolder{

        TextView Id,Name,Price,CreatedAt;
        Button btnInfor,btnDelete;
        public StoreageHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.IdStore);
            Name = itemView.findViewById(R.id.NameStore);
            Price = itemView.findViewById(R.id.PriceStore);
            CreatedAt = itemView.findViewById(R.id.CreatedAtStore);
            btnInfor = itemView.findViewById(R.id.btnInforStore);
            btnDelete = itemView.findViewById(R.id.btnDeleteStore);
            btnInfor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IdStorage = getAdapterPosition()+1;
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, DetailStorerage.class);
                    intent.putExtra("IdStorage",IdStorage);
                    context.startActivity(intent);
                }
            });
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
    }
}
