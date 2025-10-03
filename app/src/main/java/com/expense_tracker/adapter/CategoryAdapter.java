package com.expense_tracker.adapter;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.expense_tracker.CustomDialogs.CatgoryDialog;
import com.expense_tracker.listener.OnCategoryUpdatedListener;
import com.expense_tracker.R;
import com.expense_tracker.models.category;
import com.expense_tracker.viewmodel.CategoryViewModel;
import com.google.gson.JsonObject;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> implements OnCategoryUpdatedListener {

    private List<category> categoryList;
    private Context context;

    private CatgoryDialog customDialog;


    public CategoryAdapter(List<category> categoryList,Context context){
        this.categoryList = categoryList;
        this.context =context;
        Log.d("adapter1999", "inside the constructure");
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateCategoryList(List<category> newCategoryList) {
        this.categoryList = newCategoryList;
        Log.d("adapter1999", "inside the update list");
        notifyDataSetChanged();
    }
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
        holder.deleteBtn.setOnClickListener(v->{
            JsonObject data = new JsonObject();
            data.addProperty("CategoryId", category.getCategoryId());
            new AlertDialog.Builder(context)
                    .setTitle("Delete Category")
                    .setMessage("Are you sure you want to delete this category?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        deleteCategory(data,position);
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // User clicked No → dismiss dialog
                        dialog.dismiss();
                    })
                    .create()
                    .show();

        });

        holder.editBtn.setOnClickListener(v->{
            customDialog = new CatgoryDialog(
                    v.getContext(),
                    holder.categoryName.getText().toString(),
                    holder.categoryType.getText().toString(),
                    categoryList.get(position),
                    this::onCategoryUpdate,
                    position
                    );
            customDialog.show();
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteCategory(JsonObject data, int position){
        CategoryViewModel categoryViewModel = new CategoryViewModel();
        categoryViewModel.deleteCategory(data).observe((LifecycleOwner)context, apiResponse -> {
            if(apiResponse.getMessage() != null && apiResponse.getMessage().toLowerCase().contains("success")){
                Toast.makeText(context,apiResponse.getMessage(),LENGTH_LONG).show();
                categoryList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void onCategoryUpdate(category category, int position) {
        categoryList.set(position,category);
        notifyItemChanged(position);
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
