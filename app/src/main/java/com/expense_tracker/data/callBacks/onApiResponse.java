package com.expense_tracker.data.callBacks;

public interface onApiResponse<ApiResponse> {
    void  onSuccess(ApiResponse response );
    void onFailure(String message);
}
