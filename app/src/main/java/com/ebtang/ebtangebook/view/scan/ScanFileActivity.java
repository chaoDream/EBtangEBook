package com.ebtang.ebtangebook.view.scan;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.scan.adapter.ScanFileAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.dict.DictionaryUtil;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.fbreader.Paths;
import org.geometerplus.fbreader.fbreader.FBReaderApp;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/10 0010.
 */
public class ScanFileActivity extends BaseFragmentActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.scan_file_tab)
    SmartTabLayout smartTabLayout;
    @Bind(R.id.scan_file_vp)
    ViewPager viewPager;

    private ScanFileAdapter scanFileAdapter;

    private FBReaderApp myFBReaderApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_file_activity);
        ButterKnife.bind(this);
        initView();
        initData();
        myFBReaderApp = (FBReaderApp) FBReaderApp.Instance();
        if (myFBReaderApp == null) {
            myFBReaderApp = new FBReaderApp(Paths.systemInfo(this), new BookCollectionShadow());
        }
    }

    @Override
    public void onStart() {
        getCollection().bindToService(this, null);
        super.onStart();
    }

    @Override
    public void onDestroy() {
        ((BookCollectionShadow)myFBReaderApp.Collection).unbind();
        super.onDestroy();
    }

    public BookCollectionShadow getCollection() {
        return (BookCollectionShadow)myFBReaderApp.Collection;
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("导入本地书籍");

        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        scanFileAdapter = new ScanFileAdapter(getSupportFragmentManager());
        viewPager.setAdapter(scanFileAdapter);
        smartTabLayout.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
        }
    };

}
