package demo.zy.zy_test_dialog;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    OkHttpClient mOkHttpClient = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "helo");

//        packageManager = getPackageManager();
//        List<PackageInfo> mAllPackages = new ArrayList<PackageInfo>();
//        mAllPackages = packageManager.getInstalledPackages(0);
//
//        for(int i = 0; i < mAllPackages.size(); i ++) {
//            PackageInfo packageInfo = mAllPackages.get(i);
//            Log.e(TAG ,"package name = " +packageInfo.packageName);
//            Log.e(TAG,"apk name = "+ packageInfo.applicationInfo.loadLabel(packageManager).toString() );
//        }



//        params.add("data","%257B%2522Action%2522%253A%2522GetAppEdition%2522%252C%2522Message%2522%253A%257B%2522OS%2522%253A2%252C%2522Type%2522%253A1%257D%257D");

        String url = "http://cdn.api18tv.com/vodapi.html";
        String contentType = "application/x-www-form-urlencoded";

//        RequestBody requestBody = new FormBody.Builder()
//                .add("data","%257B%2522Action%2522%253A%2522GetAppEdition%2522%252C%2522Message%2522%253A%257B%2522OS%2522%253A2%252C%2522Type%2522%253A1%257D%257D")
//                .build();

        MediaType mediaType = MediaType.parse("text");


        String text1 = "data={\"Action\":\"GetAppEdition\",\n" +
                "\"Message\":{\n" +
                "\t\t\"OS\":2,\n" +
                "\t\t\"Type\":1\n" +
                "}\n" +
                "}";

        String text2 ="data=%257B%2522Action%2522%253A%2522GetAppEdition%2522%252C%2522Message%2522%253A%257B%2522OS%2522%253A2%252C%2522Type%2522%253A1%257D%257D";


        PostModeBean postModeBean = new PostModeBean();
        postModeBean.setAction("GetAppEdition");
        MessageBean messageBean = new MessageBean();
        messageBean.setOS(2);
        messageBean.setType(1);
        postModeBean.setMessage(messageBean);

        Gson gson = new Gson();
        String json = gson.toJson(postModeBean);
        Log.e(TAG, "json = "+json);
        String text3 = "data="+json;







        RequestBody requestBody = RequestBody.create(mediaType,text3);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .post(requestBody)
                .build();


        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "zy test body ="+"onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "zy test body ="+response.body().string());

            }
        });










//
//        float right = 1.3456f;
//        DecimalFormat df = new DecimalFormat("#.00");
//        String xxx = df.format(right);
//
//        Log.e("MainActivity", xxx);
//
//
//
//
//
//        List<PriceTipsDialog.DialogDataBean> dialogDataBeanList = new ArrayList<>();
//        dialogDataBeanList.add(new PriceTipsDialog.DialogDataBean("搜狐移动包月[搜狐移动包月下乡费收费方式]","移动话费支付","6.0"));
//        dialogDataBeanList.add(new PriceTipsDialog.DialogDataBean("搜狐移动单片","移动话费支付","1.0"));
//
//        PriceTipsDialog priceTipsDialog = new PriceTipsDialog(this,dialogDataBeanList);
//        priceTipsDialog.setPositiveButton(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        priceTipsDialog.setSingleSelectListen(new PriceTipsDialog.SingleSelectListen() {
//            @Override
//            public void onSelected(int id) {
//                Log.e("MainActivity", "selected id:" + id);
//            }
//        });
//        priceTipsDialog.show();

    }
}
