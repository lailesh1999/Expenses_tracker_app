package com.expense_tracker.listener;

import com.expense_tracker.models.category;

public interface OnCategoryUpdatedListener {
    void onCategoryUpdate(category category,int position);
}
