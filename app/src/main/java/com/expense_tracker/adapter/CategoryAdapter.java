package com.expense_tracker.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.expense_tracker.R;
import com.expense_tracker.models.category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<category> categoryList;
    private Context context;


    public CategoryAdapter(List<category> categoryList){
        this.categoryList = categoryList;
        Log.d("adapter1999", "inside the constructure");
    }

    public void updateCategoryList(List<category> newCategoryList) {
        this.categoryList = newCategoryList;
        Log.d("adapter1999", "inside the update list");
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        return new CategoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        category category = categoryList.get(position);
        Log.d("adapter1999", category.getCategoryName());
        holder.categoryName.setText(category.getCategoryName());
        holder.categoryType.setText(category.getCategoryType());

    }

    @Override
    public int getItemCount() {
        Log.d("adapter1999", String.valueOf(categoryList.size()));
        return categoryList.size();
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageButton editBtn,deleteBtn;
        TextView categoryName,categoryType;

        public CategoryViewHolder( View itemView) {
            super(itemView);
            editBtn = itemView.findViewById(R.id.edit_button);
            deleteBtn = itemView.findViewById(R.id.delete_button);
            categoryName= itemView.findViewById(R.id.category_name_text_view);
            categoryType = itemView.findViewById(R.id.category_type_text_view);
        }
    }
}
