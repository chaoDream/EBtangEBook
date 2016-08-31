package com.ebtang.ebtangebook.view.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.animation.ContentScaleAnimation;
import com.ebtang.ebtangebook.animation.Rotate3DAnimation;
import com.ebtang.ebtangebook.app.AppManager;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.db.read.LocalFile;
import com.ebtang.ebtangebook.db.read.LocalFileDb;
import com.ebtang.ebtangebook.view.main.MainActivity;
import com.ebtang.ebtangebook.view.main.NewMainActivity;
import com.ebtang.ebtangebook.view.main.adapter.BookShelfAdapter;
import com.ebtang.ebtangebook.view.main.popwindow.BookShelfPopwindow;
import com.ebtang.ebtangebook.view.search.SearchActivity;
import com.ebtang.ebtangebook.widget.dragGridView.CommonUtil;
import com.ebtang.ebtangebook.widget.dragGridView.DragGridView;
import com.ebtang.ebtangebook.widget.myWebView.WebViewActivity;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.fbreader.book.Book;
import org.geometerplus.zlibrary.core.filesystem.ZLFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzognwei on 2016/7/11 0011.
 */
public class BookShelfFragment extends BaseFragment implements Animation.AnimationListener{

    @Bind(R.id.top_title_left)
    ImageView imageView_menu;
    @Bind(R.id.top_title_right)
    ImageView imageView_search;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.book_shelf_mygridview)
    DragGridView dragGridView;

    private WindowManager mWindowManager;

    private AbsoluteLayout wmRootView;

    private int[] location = new int[2];
    public static final int ANIMATION_DURATION = 800;

    private int animationCount=0;  //动画加载计数器  0 默认  1一个动画执行完毕   2二个动画执行完毕
    private boolean mIsOpen = false;

    private static ContentScaleAnimation contentAnimation;
    private static Rotate3DAnimation coverAnimation;

    private BookShelfAdapter bookShelfAdapter;

    private static TextView cover;
    private TextView itemTextView;
    private View rootView;
    private Typeface typeface;
    private static ImageView content;

    private float scaleTimes;

    private BookShelfPopwindow bookShelfPopwindow;

    private LocalFileDb localFileDb;
    private List<LocalFile> localFileList = new ArrayList<>();

    private NewMainActivity newMainActivity;

    private Book book;//点击item将要打开的书

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.main_bookshelf, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        newMainActivity = (NewMainActivity)getActivity();
        mWindowManager = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
        wmRootView = new AbsoluteLayout(getActivity());
        typeface = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "font/QH.ttf");
        ButterKnife.bind(this, rootView);
        localFileDb = new LocalFileDb(getActivity());
        initView();
//        initData();
        return rootView;
    }

    @Override
    public void onResume() {
        initData();
        dragGridView.hideDeleteBt();
        closeBookAnimation();
        super.onResume();
    }

    @Override
    public void onStop() {
        dragGridView.hideDeleteBt();
        super.onStop();
    }

    @Override
    public void onPause() {
        dragGridView.hideDeleteBt();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        dragGridView.hideDeleteBt();
        super.onDestroy();
    }

    @Override
    public void initView() {
        bookShelfPopwindow = new BookShelfPopwindow(getActivity(),imageView_menu);
        imageView_menu.setImageResource(R.drawable.bookshelf_menu_big);
        imageView_search.setImageResource(R.drawable.bookshelf_menu_search);
        imageView_search.setVisibility(View.VISIBLE);
        imageView_menu.setVisibility(View.VISIBLE);
        textView_title.setText("书架");

        dragGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (localFileList.size() > position) {
                    LocalFile localFile = localFileList.get(position);
                    localFile.setTime(System.currentTimeMillis());
                    localFileDb.update(localFile);
                    ZLFile zlFile = ZLFile.createFileByPath(localFileList.get(position).getPath());
                    book = createBookForFile(zlFile);
                    String bookname = localFileList.get(position).getName();
                    itemTextView = (TextView) view.findViewById(R.id.imageView1);
                    itemTextView.getLocationInWindow(location);

                    mWindowManager.addView(wmRootView, getDefaultWindowParams());

                    cover = new TextView(getActivity().getApplicationContext());
                    cover.setBackgroundDrawable(getResources().getDrawable(R.drawable.cover_default_new));
                    cover.setCompoundDrawablesWithIntrinsicBounds(null, null, null, getResources().getDrawable(R.drawable.cover_type_txt));
                    cover.setText(bookname);
                    cover.setTextColor(getResources().getColor(R.color.black));
                    cover.setTypeface(typeface);
                    int coverPadding = (int) CommonUtil.convertDpToPixel(getActivity().getApplicationContext(), 10);
                    cover.setPadding(coverPadding, coverPadding, coverPadding, coverPadding);

                    content = new ImageView(getActivity().getApplicationContext());
                    // content.setBackgroundDrawable(getResources().getDrawable(R.drawable.open_book_bg));
                    Bitmap contentBitmap = Bitmap.createBitmap(itemTextView.getMeasuredWidth(), itemTextView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                    contentBitmap.eraseColor(getResources().getColor(R.color.read_background_paperYellow));
                    content.setImageBitmap(contentBitmap);

                    AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
                            itemTextView.getLayoutParams());
                    params.x = location[0];
                    params.y = location[1];

                    wmRootView.addView(content, params);
                    wmRootView.addView(cover, params);

                    initAnimation();

                    if (contentAnimation.getMReverse()) {
                        contentAnimation.reverse();
                    }
                    if (coverAnimation.getMReverse()) {
                        coverAnimation.reverse();
                    }
                    cover.clearAnimation();
                    cover.startAnimation(coverAnimation);
                    content.clearAnimation();
                    content.startAnimation(contentAnimation);
                }
            }
        });

        imageView_search.setOnClickListener(this);
        imageView_menu.setOnClickListener(this);

    }

    @Override
    public void initData() {
        localFileList.clear();
        if(localFileDb.findAllBook()!=null){
            List<LocalFile> localFiles = localFileDb.findAllBook();
            localFileList.addAll(localFiles);
        }
        bookShelfAdapter = new BookShelfAdapter(getActivity(),localFileList);
        dragGridView.setAdapter(bookShelfAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_right:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.top_title_left:
                bookShelfPopwindow.showPop();
                break;
        }
    }

    private WindowManager.LayoutParams getDefaultWindowParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                0, 0,
                WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,//windown类型,有层级的大的层级会覆盖在小的层级
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                PixelFormat.RGBA_8888);

        return params;
    }

    private void initAnimation() {
        AccelerateInterpolator interpolator = new AccelerateInterpolator();

        float scale1 = NewMainActivity.getWindowWidth() / (float) itemTextView.getMeasuredWidth();
        float scale2 = NewMainActivity.getWindowHeight() / (float) itemTextView.getMeasuredHeight();
        scaleTimes = scale1 > scale2 ? scale1 : scale2;  //计算缩放比例

        contentAnimation = new ContentScaleAnimation( location[0], location[1],scaleTimes, false);
        contentAnimation.setInterpolator(interpolator);  //设置插值器
        contentAnimation.setDuration(ANIMATION_DURATION);
        contentAnimation.setFillAfter(true);  //动画停留在最后一帧
        contentAnimation.setAnimationListener(this);

        coverAnimation = new Rotate3DAnimation(0, -180, location[0], location[1], scaleTimes, false);
        coverAnimation.setInterpolator(interpolator);
        coverAnimation.setDuration(ANIMATION_DURATION);
        coverAnimation.setFillAfter(true);
        coverAnimation.setAnimationListener(this);

    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //有两个动画监听会执行两次，所以要判断
        if (!mIsOpen) {
            animationCount++;
            if (animationCount >= 2) {
                mIsOpen = true;
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), FBReader.class);
////                intent.setClass(getActivity(), WebViewActivity.class);
//                intent.putExtra(Constants.APP_WEBVIEW_TITLE,"阅读");
//                intent.putExtra(Constants.APP_WEBVIEW_URL,"http://www.baidu.com");
//                startActivity(intent);
                FBReader.openBookActivity(getActivity(),book,null);
            }

        } else {
            animationCount--;
            if (animationCount <= 0) {
                mIsOpen = false;
                wmRootView.removeView(cover);
                wmRootView.removeView(content);
                mWindowManager.removeView(wmRootView);
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void closeBookAnimation() {
        if (mIsOpen && wmRootView!=null) {
            //因为书本打开后会移动到第一位置，所以要设置新的位置参数
            contentAnimation.setmPivotXValue(dragGridView.getFirstLocation()[0]);
            contentAnimation.setmPivotYValue(dragGridView.getFirstLocation()[1]);
            coverAnimation.setmPivotXValue(dragGridView.getFirstLocation()[0]);
            coverAnimation.setmPivotYValue(dragGridView.getFirstLocation()[1]);

            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
                    itemTextView.getLayoutParams());
            params.x = dragGridView.getFirstLocation()[0];
            params.y = dragGridView.getFirstLocation()[1];//firstLocation[1]在滑动的时候回改变,所以要在dispatchDraw的时候获取该位置值
            wmRootView.updateViewLayout(cover,params);
            wmRootView.updateViewLayout(content,params);
            //动画逆向运行
            if (!contentAnimation.getMReverse()) {
                contentAnimation.reverse();
            }
            if (!coverAnimation.getMReverse()) {
                coverAnimation.reverse();
            }
            //清除动画再开始动画
            content.clearAnimation();
            content.startAnimation(contentAnimation);
            cover.clearAnimation();
            cover.startAnimation(coverAnimation);
        }
    }

    public boolean isShowDeleteBt(){
        return dragGridView.getShowDeleteButton();
    }

    public void hideDeleteBt(){
        dragGridView.hideDeleteBt();
    }


    private org.geometerplus.fbreader.book.Book createBookForFile(final ZLFile file) {
        if (file == null) {
            return null;
        }
        newMainActivity.getCollection().bindToService(newMainActivity, new Runnable() {
            @Override
            public void run() {
                book = newMainActivity.getCollection().getBookByFile(file.getPath());
            }
        });

        if (book != null) {
            return book;
        }
        if (file.isArchive()) {
            for (final ZLFile child : file.children()) {
                newMainActivity.getCollection().bindToService(newMainActivity, new Runnable() {
                    @Override
                    public void run() {
                        book = newMainActivity.getCollection().getBookByFile(child.getPath());
                    }
                });
                if (book != null) {
                    return book;
                }
            }
        }
        return null;
    }

}
