package com.yuzhua.universalproject;

import android.os.Bundle;

import com.tuo.customview.VerificationCodeView;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TestBaseActivity extends BaseActivity {

    @BindView(R.id.verificationcodeview)
    VerificationCodeView verificationCodeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("SmsCodeView");
        verificationCodeInput.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if (verificationCodeInput.getInputContent().toString().length()==5){
                    SmsCodeDialog dialog = new SmsCodeDialog(TestBaseActivity.this,"18428324461");
                    dialog.show();

                }
            }

            @Override
            public void deleteContent() {

            }
        });
    }

}
