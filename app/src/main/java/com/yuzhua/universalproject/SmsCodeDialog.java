package com.yuzhua.universalproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.tuo.customview.VerificationCodeView;
import com.yuzhua.universalinvinciblesdk.util.ToastUtil;

/**
 * @author GuZhC
 * @create 2018/4/24
 * @Describe
 */
public class SmsCodeDialog extends Dialog {
    private String mPhone;
    private Context mContext;
    private ProgressBar mLoading;
    private VerificationCodeView mVerificationInput;

    public SmsCodeDialog(Context context, String phone) {
        this(context, R.style.Dialog);
        // 上一个界面传来的手机号
        this.mPhone = phone;
        this.mContext = context;
    }

    public SmsCodeDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    LayoutInflater inflater = (LayoutInflater) getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View root = inflater.inflate(R.layout.dialog_smscode_input, null);
    setContentView(root);
    mVerificationInput = (VerificationCodeView) findViewById(R.id.icv);
    mLoading = findViewById(R.id.loading);
    initListeners();
}

    private void initListeners() {
        mVerificationInput.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if (mVerificationInput.getInputContent().toString().length()==5){
                    ToastUtil.toastSuccess(mContext, mVerificationInput.getInputContent().toString(), false);
                    mLoading.setVisibility(View.VISIBLE);
                    mVerificationInput.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void deleteContent() {

            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }
}
