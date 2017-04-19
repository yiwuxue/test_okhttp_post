package demo.zy.zy_test_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yizhang210244 on 2017/3/27.
 */

public class PriceTipsDialog extends Dialog{
    private TextView mProductValue;
    private TextView mPriceValue;
    private RadioGroup mRadioGroup;
    private List<DialogDataBean> mData;
    private Button mOpenBtn;
    private SingleSelectListen mSingleSelectListen;
    private View.OnClickListener mPositiveButtonClickListen;

    public PriceTipsDialog(Context context, List<DialogDataBean> data) {
        super(context,R.style.CustomDialogStyle);
        this.mData = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        mProductValue = (TextView) findViewById(R.id.nameValTxt);
        mPriceValue = (TextView) findViewById(R.id.priceValTxt);
        mRadioGroup = (RadioGroup) findViewById(R.id.payTypeGroup);
        mOpenBtn = (Button) findViewById(R.id.bt_open);

        if(mData != null ){
            for (int i = 0; i < mData.size(); i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setId(i);
                radioButton.setText(mData.get(i).getPayType());
                if(i == 0){
                    radioButton.setChecked(true);
                }
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = view.getId();
                        mProductValue.setText(mData.get(id).getName());
                        mPriceValue.setText(mData.get(id).getPrice());
                        if(mSingleSelectListen != null){
                            mSingleSelectListen.onSelected(id);
                        }
                    }
                });
                mRadioGroup.addView(radioButton);
            }
            if(mData.size() > 0){
                mProductValue.setText(mData.get(0).getName());
                mPriceValue.setText(mData.get(0).getPrice());
            }
        }

        if(mPositiveButtonClickListen != null){
            mOpenBtn.setOnClickListener(mPositiveButtonClickListen);
        }

    }


    public void setPositiveButton(View.OnClickListener listen){
        this.mPositiveButtonClickListen = listen;
    }

    public void setSingleSelectListen(SingleSelectListen listen){
        this.mSingleSelectListen = listen;
    }

    public interface SingleSelectListen{
        void onSelected(int id);
    }


    public static class DialogDataBean{
        private String name;
        private String payType;
        private String price;

        public DialogDataBean(String name, String payType, String price) {
            this.name = name;
            this.payType = payType;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

}
