/*
 * Copyright (C) 2009-2015 FBReader.ORG Limited <contact@fbreader.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.android.fbreader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.db.read.BookMarkEbt;
import com.ebtang.ebtangebook.db.read.BookMarkEbtDb;
import com.ebtang.ebtangebook.event.AnimStyle;
import com.ebtang.ebtangebook.event.BookMarkModify;
import com.ebtang.ebtangebook.event.LongClickDrawLine;
import com.ebtang.ebtangebook.event.OpenBookDone;
import com.ebtang.ebtangebook.event.RegisteSuccess;
import com.ebtang.ebtangebook.event.TimeChange;
import com.ebtang.ebtangebook.event.VolumeChangePage;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.bookinfo.BookLabelActivity;
import com.ebtang.ebtangebook.view.read.ReadSetColorFontPop;
import com.ebtang.ebtangebook.view.read.ReadSettingPopWindow;
import com.ebtang.ebtangebook.view.read.service.MyTimeService;
import com.ebtang.ebtangebook.view.read.widget.BatteryView;

import org.geometerplus.android.fbreader.api.ApiListener;
import org.geometerplus.android.fbreader.api.ApiServerImplementation;
import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.api.MenuNode;
import org.geometerplus.android.fbreader.api.PluginApi;
import org.geometerplus.android.fbreader.dict.DictionaryUtil;
import org.geometerplus.android.fbreader.formatPlugin.PluginUtil;
import org.geometerplus.android.fbreader.httpd.DataService;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.android.fbreader.sync.SyncOperations;
import org.geometerplus.android.fbreader.tips.TipsActivity;
import org.geometerplus.android.util.DeviceType;
import org.geometerplus.android.util.SearchDialogUtil;
import org.geometerplus.android.util.UIMessageUtil;
import org.geometerplus.android.util.UIUtil;
import org.geometerplus.fbreader.Paths;
import org.geometerplus.fbreader.book.Book;
import org.geometerplus.fbreader.book.BookUtil;
import org.geometerplus.fbreader.book.Bookmark;
import org.geometerplus.fbreader.bookmodel.BookModel;
import org.geometerplus.fbreader.bookmodel.TOCTree;
import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.DictionaryHighlighting;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.fbreader.fbreader.options.CancelMenuHelper;
import org.geometerplus.fbreader.fbreader.options.ColorProfile;
import org.geometerplus.fbreader.fbreader.options.MiscOptions;
import org.geometerplus.fbreader.formats.ExternalFormatPlugin;
import org.geometerplus.fbreader.formats.PluginCollection;
import org.geometerplus.fbreader.tips.TipsManager;
import org.geometerplus.zlibrary.core.application.ZLApplicationWindow;
import org.geometerplus.zlibrary.core.filesystem.ZLFile;
import org.geometerplus.zlibrary.core.library.ZLibrary;
import org.geometerplus.zlibrary.core.options.Config;
import org.geometerplus.zlibrary.core.resources.ZLResource;
import org.geometerplus.zlibrary.core.view.ZLViewEnums;
import org.geometerplus.zlibrary.core.view.ZLViewWidget;
import org.geometerplus.zlibrary.text.view.ZLTextRegion;
import org.geometerplus.zlibrary.text.view.ZLTextView;
import org.geometerplus.zlibrary.ui.android.error.ErrorKeys;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidApplication;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidLibrary;
import org.geometerplus.zlibrary.ui.android.view.AndroidFontUtil;
import org.geometerplus.zlibrary.ui.android.view.ZLAndroidWidget;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主界面，入口
 */
public final class FBReader extends FBReaderMainActivity implements ZLApplicationWindow {

    public static final int RESULT_DO_NOTHING = RESULT_FIRST_USER;
    public static final int RESULT_REPAINT = RESULT_FIRST_USER + 1;

    public static Intent defaultIntent(Context context) {
        return new Intent(context, FBReader.class).setAction(FBReaderIntents.Action.VIEW).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public static void openBookActivity(Context context, Book book, Bookmark bookmark) {
        final Intent intent = defaultIntent(context);
        FBReaderIntents.putBookExtra(intent, book);
        FBReaderIntents.putBookmarkExtra(intent, bookmark);
        context.startActivity(intent);
    }

    public FBReaderApp myFBReaderApp;
    private volatile Book myBook;

    private RelativeLayout myRootView;
    /**
     * 翻页组件
     */
    private ZLAndroidWidget myMainView;

    private volatile boolean myShowStatusBarFlag;
    private String myMenuLanguage;

    final DataService.Connection DataConnection = new DataService.Connection();

    volatile boolean IsPaused = false;
    private volatile long myResumeTimestamp;
    volatile Runnable OnResumeAction = null;

    private Intent myCancelIntent = null;
    private Intent myOpenBookIntent = null;

    private final FBReaderApp.Notifier myNotifier = new AppNotifier(this);

    private static final String PLUGIN_ACTION_PREFIX = "___";
    private final List<PluginApi.ActionInfo> myPluginActions = new LinkedList<PluginApi.ActionInfo>();
    private final BroadcastReceiver myPluginInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ArrayList<PluginApi.ActionInfo> actions = getResultExtras(true).<PluginApi.ActionInfo> getParcelableArrayList(PluginApi.PluginInfo.KEY);
            if (actions != null) {
                synchronized (myPluginActions) {
                    int index = 0;
                    while (index < myPluginActions.size()) {
                        myFBReaderApp.removeAction(PLUGIN_ACTION_PREFIX + index++);
                    }
                    myPluginActions.addAll(actions);
                    index = 0;
                    for (PluginApi.ActionInfo info : myPluginActions) {
                        myFBReaderApp.addAction(PLUGIN_ACTION_PREFIX + index++, new RunPluginAction(FBReader.this, myFBReaderApp, info.getId()));
                    }
                }
            }
        }
    };

    /**
     * 打开图书
     *
     * @param intent
     * @param action
     * @param force
     */
    private synchronized void openBook(Intent intent, final Runnable action, boolean force) {
        if (!force && myBook != null) {
            return;
        }

        myBook = FBReaderIntents.getBookExtra(intent, myFBReaderApp.Collection);
        final Bookmark bookmark = FBReaderIntents.getBookmarkExtra(intent);
        if (myBook == null) {
            final Uri data = intent.getData();
            if (data != null) {
                myBook = createBookForFile(ZLFile.createFileByPath(data.getPath()));
            }
        }
        if (myBook != null) {
            ZLFile file = BookUtil.fileByBook(myBook);
            if (!file.exists()) {
                if (file.getPhysicalFile() != null) {
                    file = file.getPhysicalFile();
                }
                UIMessageUtil.showErrorMessage(this, "fileNotFound", file.getPath());
                myBook = null;
            }else {
                NotificationUtil.drop(this, myBook);
            }
        }
        Config.Instance().runOnConnect(new Runnable() {
            public void run() {
                myFBReaderApp.openBook(myBook, bookmark, action, myNotifier);
                AndroidFontUtil.clearFontCache();
            }
        });
    }

    private Book createBookForFile(ZLFile file) {
        if (file == null) {
            return null;
        }
        Book book = myFBReaderApp.Collection.getBookByFile(file.getPath());
        if (book != null) {
            return book;
        }
        if (file.isArchive()) {
            for (ZLFile child : file.children()) {
                book = myFBReaderApp.Collection.getBookByFile(child.getPath());
                if (book != null) {
                    return book;
                }
            }
        }
        return null;
    }

    private Runnable getPostponedInitAction() {
        return new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        new TipRunner().start();
                        DictionaryUtil.init(FBReader.this, null);
                        final Intent intent = getIntent();
                        if (intent != null && FBReaderIntents.Action.PLUGIN.equals(intent.getAction())) {
                            new RunPluginAction(FBReader.this, myFBReaderApp, intent.getData()).run();
                        }
                    }
                });
            }
        };
    }

    /**************************************自增内容**********************************/
    private Animation mAnimSlideInTop;
    private Animation mAnimSlideInBottom;
    private Animation mAnimSlideOutTop;
    private Animation mAnimSlideOutBottom;
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.read_title)
    LinearLayout linearLayout_top;
    @Bind(R.id.top_title_right)
    ImageView imageView_menu;
    @Bind(R.id.read_util_bottom)
    LinearLayout linearLayout_bottom;
    @Bind(R.id.read_daynight)
    ImageView imageView_daynight;
    @Bind(R.id.read_book_name)
    TextView textView_book_name;
    @Bind(R.id.read_current_bookpage)
    TextView textView_current_bookpage;
    @Bind(R.id.read_total_bookpage)
    TextView textView_total_bookpage;
    @Bind(R.id.read_bookpage_fenge)
    TextView textView_fenge;
    @Bind(R.id.read_dianliang)
    BatteryView batteryView;
    @Bind(R.id.read_time)
    TextView textView_time;
    @Bind(R.id.read_progress_sb)
    SeekBar seekBar_progress;
    @Bind(R.id.read_current_page)
    TextView textView_page_now;
    @Bind(R.id.read_totle_page)
    TextView textView_page_all;
    @Bind(R.id.read_page_bili)
    TextView textView_progress_bili;
    @Bind(R.id.read_set_mulu)
    ImageView imageView_set_mulu;
    @Bind(R.id.read_util_lastchapter)
    TextView textView_lastChapter;
    @Bind(R.id.read_util_nextchapter)
    TextView textView_nextchapter;
    @Bind(R.id.zhanwei_view)
    View view_zhanwei;
    @Bind(R.id.read_search)
    ImageView imageView_search;
    private boolean isShowUtil = false;

    private ReadSettingPopWindow readSettingPopWindow;
    public ReadSetColorFontPop readSetColorFontPop;

    private boolean isNightMode = false;//当前是否为夜间模式

    //本书当前当前章节
    private TOCTree root;
    //绑定章节的adapter
    private CalculateChapterAdapter calculateChapterAdapter;

    private boolean isFiratChapter = false;//当前阅读的是否是第一章
    private boolean isLastChapter = false;//当前阅读的是否的最后一章

    private int lastChapterNum;//上一章的index
    private int nextChapterNum;//下一章的Index

    private View statusView;//生成的跟状态栏大小一样的view用于站位
    private int statusViewHeight;//
    private boolean isChenjin = false;//当前是否支持沉浸式阅读

    private BookMarkEbtDb bookMarkEbtDb;//自定义的书签存储数据库

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        bindService(new Intent(this, DataService.class), DataConnection, DataService.BIND_AUTO_CREATE);
        Intent service=new Intent(this, MyTimeService.class);
        startService(service);
        bookMarkEbtDb = new BookMarkEbtDb(this);
        final Config config = Config.Instance();
        //ConfigShadow
        config.runOnConnect(new Runnable() {
            public void run() {
                config.requestAllValuesForGroup("Options");
                config.requestAllValuesForGroup("Style");
                config.requestAllValuesForGroup("LookNFeel");
                config.requestAllValuesForGroup("Fonts");
                config.requestAllValuesForGroup("Colors");
                config.requestAllValuesForGroup("Files");
            }
        });

        final ZLAndroidLibrary zlibrary = getZLibrary();
        myShowStatusBarFlag = zlibrary.ShowStatusBarOption.getValue();
        //隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setStatusBarVisibility(false);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        myRootView = (RelativeLayout)findViewById(R.id.root_view);
        myMainView = (ZLAndroidWidget)findViewById(R.id.main_view);
        //按键会打开本地搜索
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);

        myFBReaderApp = (FBReaderApp) FBReaderApp.Instance();

        if (myFBReaderApp == null) {
            myFBReaderApp = new FBReaderApp(Paths.systemInfo(this), new BookCollectionShadow());
        }
//        checkFirstOrLastChapter();
        /******************自增(上下留出来空间)******************/
        myFBReaderApp.ViewOptions.TopMargin.setValue(45);
        myFBReaderApp.ViewOptions.BottomMargin.setValue(40);
        ZLAndroidLibrary.Instance().getOrientationOption().setValue("portrait");//设置只支持竖屏显示

        myBook = null;

        myFBReaderApp.setWindow(this);
        myFBReaderApp.initWindow();

        myFBReaderApp.setExternalFileOpener(new ExternalFileOpener(this));

        //沉浸式
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            isChenjin = true;
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, myShowStatusBarFlag ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN);
            initStatusView(this,getResources().getColor(R.color.primary));
        }

        //添加3个popup到FBreaderApp
        if (myFBReaderApp.getPopupById(TextSearchPopup.ID) == null) {
            //内容查找后，显示的操作
            new TextSearchPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(NavigationPopup.ID) == null) {
            //快速翻看
            new NavigationPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(SelectionPopup.ID) == null) {
            //长按文本，显示的文本框
            new SelectionPopup(myFBReaderApp);
        }

        //操作action
        myFBReaderApp.addAction(ActionCode.SHOW_LIBRARY, new ShowLibraryAction(this, myFBReaderApp));//本地书库
        myFBReaderApp.addAction(ActionCode.SHOW_PREFERENCES, new ShowPreferencesAction(this, myFBReaderApp));//
        myFBReaderApp.addAction(ActionCode.SHOW_BOOK_INFO, new ShowBookInfoAction(this, myFBReaderApp));//书籍详情
        myFBReaderApp.addAction(ActionCode.SHOW_TOC, new ShowTOCAction(this, myFBReaderApp));//目录
        myFBReaderApp.addAction(ActionCode.SHOW_BOOKMARKS, new ShowBookmarksAction(this, myFBReaderApp));//书签
        myFBReaderApp.addAction(ActionCode.SHOW_NETWORK_LIBRARY, new ShowNetworkLibraryAction(this, myFBReaderApp));//网络书库

        myFBReaderApp.addAction(ActionCode.SHOW_MENU, new ShowMenuAction(this, myFBReaderApp));//显示菜单
        myFBReaderApp.addAction(ActionCode.SHOW_NAVIGATION, new ShowNavigationAction(this, myFBReaderApp));//快速翻看
        myFBReaderApp.addAction(ActionCode.SEARCH, new SearchAction(this, myFBReaderApp));//查找
        myFBReaderApp.addAction(ActionCode.SHARE_BOOK, new ShareBookAction(this, myFBReaderApp));//分享

        myFBReaderApp.addAction(ActionCode.SELECTION_SHOW_PANEL, new SelectionShowPanelAction(this, myFBReaderApp));//显示长按划线pop
        myFBReaderApp.addAction(ActionCode.SELECTION_HIDE_PANEL, new SelectionHidePanelAction(this, myFBReaderApp));//隐藏划线pop
        myFBReaderApp.addAction(ActionCode.SELECTION_COPY_TO_CLIPBOARD, new SelectionCopyAction(this, myFBReaderApp));//复制
        myFBReaderApp.addAction(ActionCode.SELECTION_SHARE, new SelectionShareAction(this, myFBReaderApp));//分享选中文本
        myFBReaderApp.addAction(ActionCode.SELECTION_TRANSLATE, new SelectionTranslateAction(this, myFBReaderApp));//翻译
        myFBReaderApp.addAction(ActionCode.SELECTION_BOOKMARK, new SelectionBookmarkAction(this, myFBReaderApp));//点击划线文本弹出窗体

        myFBReaderApp.addAction(ActionCode.DISPLAY_BOOK_POPUP, new DisplayBookPopupAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.PROCESS_HYPERLINK, new ProcessHyperlinkAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.OPEN_VIDEO, new OpenVideoAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.HIDE_TOAST, new HideToastAction(this, myFBReaderApp));//隐藏toast

        myFBReaderApp.addAction(ActionCode.SHOW_CANCEL_MENU, new ShowCancelMenuAction(this, myFBReaderApp));//取消菜单
        myFBReaderApp.addAction(ActionCode.OPEN_START_SCREEN, new StartScreenAction(this, myFBReaderApp));

        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_SYSTEM, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_SYSTEM));
        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_SENSOR, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_SENSOR));
        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_PORTRAIT, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_PORTRAIT));
        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_LANDSCAPE, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_LANDSCAPE));

        //是否支持横竖屏
        if (getZLibrary().supportsAllOrientations()) {
            myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_REVERSE_PORTRAIT, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_REVERSE_PORTRAIT));
            myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_REVERSE_LANDSCAPE, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_REVERSE_LANDSCAPE));
        }

        myFBReaderApp.addAction(ActionCode.OPEN_WEB_HELP, new OpenWebHelpAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.INSTALL_PLUGINS, new InstallPluginsAction(this, myFBReaderApp));

        myFBReaderApp.addAction(ActionCode.SWITCH_TO_DAY_PROFILE, new SwitchProfileAction(this, myFBReaderApp, ColorProfile.DAY));
        myFBReaderApp.addAction(ActionCode.SWITCH_TO_NIGHT_PROFILE, new SwitchProfileAction(this, myFBReaderApp, ColorProfile.NIGHT));

        final Intent intent = getIntent();
        final String action = intent.getAction();

        myOpenBookIntent = intent;
        if ((intent.getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) == 0) {
            if (FBReaderIntents.Action.CLOSE.equals(action)) {
                myCancelIntent = intent;
                myOpenBookIntent = null;
            }else if (FBReaderIntents.Action.PLUGIN_CRASH.equals(action)) {
                myFBReaderApp.ExternalBook = null;
                myOpenBookIntent = null;
                getCollection().bindToService(this, new Runnable() {
                    public void run() {
                        myFBReaderApp.openBook(null, null, null, myNotifier);
                    }
                });
            }
        }
        /***************自增***********/
        initAnimation();
        initView();
        initConfig();
        EventBus.getDefault().register(this);
    }

    /**
     * 是每次在display Menu之前，都会去调用，
     * 只要按一次Menu按鍵，就会调用一次,
     * 所以可以在这里动态的改变menu。
     *
     * @param menu
     *
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        setStatusBarVisibility(true);

        //设置menu
        setupMenu(menu);

        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 只会调用一次，他只会在Menu显示之前去调用一次，之后就不会在去调用
     *
     * @param menu
     *
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //设置menu
        setupMenu(menu);

        return true;
    }

    /**
     * 每次菜单被关闭时调用
     *
     * @param menu
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        setStatusBarVisibility(false);
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     *
     * @param item
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setStatusBarVisibility(false);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        final String action = intent.getAction();
        final Uri data = intent.getData();

        if ((intent.getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) != 0) {
            super.onNewIntent(intent);
        }else if (Intent.ACTION_VIEW.equals(action) && data != null && "fbreader-action".equals(data.getScheme())) {
            myFBReaderApp.runAction(data.getEncodedSchemeSpecificPart(), data.getFragment());
        }else if (Intent.ACTION_VIEW.equals(action) || FBReaderIntents.Action.VIEW.equals(action)) {
            myOpenBookIntent = intent;
            if (myFBReaderApp.Model == null && myFBReaderApp.ExternalBook != null) {
                final BookCollectionShadow collection = getCollection();
                final Book b = FBReaderIntents.getBookExtra(intent, collection);
                if (!collection.sameBook(b, myFBReaderApp.ExternalBook)) {
                    try {
                        final ExternalFormatPlugin plugin = (ExternalFormatPlugin)BookUtil.getPlugin(PluginCollection.Instance(Paths.systemInfo(this)), myFBReaderApp.ExternalBook);
                        startActivity(PluginUtil.createIntent(plugin, FBReaderIntents.Action.PLUGIN_KILL));
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if (FBReaderIntents.Action.PLUGIN.equals(action)) {
            new RunPluginAction(this, myFBReaderApp, data).run();
        }else if (Intent.ACTION_SEARCH.equals(action)) {
            final String pattern = intent.getStringExtra(SearchManager.QUERY);
            final Runnable runnable = new Runnable() {
                public void run() {
                    final TextSearchPopup popup = (TextSearchPopup)myFBReaderApp.getPopupById(TextSearchPopup.ID);
                    popup.initPosition();
                    myFBReaderApp.MiscOptions.TextSearchPattern.setValue(pattern);
                    if (myFBReaderApp.getTextView().search(pattern, true, false, false, false) != 0) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                myFBReaderApp.showPopup(popup.getId());
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                UIMessageUtil.showErrorMessage(FBReader.this, "textNotFound");
                                popup.StartPosition = null;
                            }
                        });
                    }
                }
            };
            UIUtil.wait("search", runnable, this);
        }else if (FBReaderIntents.Action.CLOSE.equals(intent.getAction())) {
            myCancelIntent = intent;
            myOpenBookIntent = null;
        }else if (FBReaderIntents.Action.PLUGIN_CRASH.equals(intent.getAction())) {
            final Book book = FBReaderIntents.getBookExtra(intent, myFBReaderApp.Collection);
            myFBReaderApp.ExternalBook = null;
            myOpenBookIntent = null;
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    final BookCollectionShadow collection = getCollection();
                    Book b = collection.getRecentBook(0);
                    if (collection.sameBook(b, book)) {
                        b = collection.getRecentBook(1);
                    }
                    myFBReaderApp.openBook(b, null, null, myNotifier);
                }
            });
        }else {
            super.onNewIntent(intent);
        }
    }

    @Override
    protected void onStart() {
        getCollection().bindToService(this, null);
        super.onStart();
        getCollection().bindToService(this, new Runnable() {
            public void run() {
                new Thread() {
                    public void run() {
                        getPostponedInitAction().run();
                    }
                }.start();

                myFBReaderApp.getViewWidget().repaint();
            }
        });

        initPluginActions();

        final ZLAndroidLibrary zlibrary = getZLibrary();

        //检查屏幕大小是否符合，不符重开activity
        Config.Instance().runOnConnect(new Runnable() {
            public void run() {
                final boolean showStatusBar = zlibrary.ShowStatusBarOption.getValue();
                if (showStatusBar != myShowStatusBarFlag) {
                    finish();
                    startActivity(new Intent(FBReader.this, FBReader.class));
                }
                zlibrary.ShowStatusBarOption.saveSpecialValue();
                myFBReaderApp.ViewOptions.ColorProfileName.saveSpecialValue();
                SetScreenOrientationAction.setOrientation(FBReader.this, zlibrary.getOrientationOption().getValue());
            }
        });

        ((PopupPanel)myFBReaderApp.getPopupById(TextSearchPopup.ID)).setPanelInfo(this, myRootView);
        ((NavigationPopup)myFBReaderApp.getPopupById(NavigationPopup.ID)).setPanelInfo(this, myRootView);
        ((PopupPanel)myFBReaderApp.getPopupById(SelectionPopup.ID)).setPanelInfo(this, myRootView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        switchWakeLock(hasFocus && getZLibrary().BatteryLevelToTurnScreenOffOption.getValue() < myFBReaderApp.getBatteryLevel());
    }

    private void initPluginActions() {
        synchronized (myPluginActions) {
            int index = 0;
            while (index < myPluginActions.size()) {
                myFBReaderApp.removeAction(PLUGIN_ACTION_PREFIX + index++);
            }
            myPluginActions.clear();
        }

        sendOrderedBroadcast(new Intent(PluginApi.ACTION_REGISTER), null, myPluginInfoReceiver, null, RESULT_OK, null, null);
    }

    private class TipRunner extends Thread {

        TipRunner() {
            setPriority(MIN_PRIORITY);
        }

        public void run() {
            final TipsManager manager = new TipsManager(Paths.systemInfo(FBReader.this));

            switch (manager.requiredAction()) {
                case Initialize:
                    startActivity(new Intent(TipsActivity.INITIALIZE_ACTION, null, FBReader.this, TipsActivity.class));
                    break;

                case Show:
                    startActivity(new Intent(TipsActivity.SHOW_TIP_ACTION, null, FBReader.this, TipsActivity.class));
                    break;

                case Download:
                    manager.startDownloading();
                    break;

                case None:
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        myStartTimer = true;
        Config.Instance().runOnConnect(new Runnable() {
            public void run() {

                //同步数据
                SyncOperations.enableSync(FBReader.this, myFBReaderApp.SyncOptions);

//                final int brightnessLevel = getZLibrary().ScreenBrightnessLevelOption.getValue();
//                if (brightnessLevel != 0) {
//                    getViewWidget().setScreenBrightness(brightnessLevel);
//                }else {
//                    setScreenBrightnessAuto();//设置屏幕亮度
//                }
                if(!SharedPrefHelper.getInstance(FBReader.this).getIsUseSystemLiangdu()){
                    final float brightnessLevel = SharedPrefHelper.getInstance(FBReader.this).getScreenLiangdu();
                    setScreenBrightnessSystem(brightnessLevel);
                }else{
                    try{
                        setScreenBrightnessSystem(Settings.System.getInt(FBReader.this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS) / 255.0f);
                    }catch (Exception e){
                    }
                }

//                getViewWidget().setScreenBrightness(brightnessLevel);
                if (getZLibrary().DisableButtonLightsOption.getValue()) {
                    setButtonLight(false);
                }

                getCollection().bindToService(FBReader.this, new Runnable() {
                    public void run() {
                        final BookModel model = myFBReaderApp.Model;
                        if (model == null || model.Book == null) {
                            return;
                        }
                        onPreferencesUpdate(myFBReaderApp.Collection.getBookById(model.Book.getId()));
                    }
                });
            }
        });

        //注册电池电量监听
        registerReceiver(myBatteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        IsPaused = false;
        myResumeTimestamp = System.currentTimeMillis();
        if (OnResumeAction != null) {
            final Runnable action = OnResumeAction;
            OnResumeAction = null;
            action.run();
        }

        registerReceiver(mySyncUpdateReceiver, new IntentFilter(FBReaderIntents.Event.SYNC_UPDATED));

        SetScreenOrientationAction.setOrientation(this, getZLibrary().getOrientationOption().getValue());
        if (myCancelIntent != null) {
            final Intent intent = myCancelIntent;
            myCancelIntent = null;
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    runCancelAction(intent);
                }
            });
            return;
        }else if (myOpenBookIntent != null) {
            //打开图书
            final Intent intent = myOpenBookIntent;
            myOpenBookIntent = null;
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    openBook(intent, null, true);
                }
            });
        }else if (myFBReaderApp.getCurrentServerBook(null) != null) {
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    myFBReaderApp.useSyncInfo(true, myNotifier);
                }
            });
        }else if (myFBReaderApp.Model == null && myFBReaderApp.ExternalBook != null) {
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    myFBReaderApp.openBook(myFBReaderApp.ExternalBook, null, null, myNotifier);
                }
            });
        }else {
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    myFBReaderApp.useSyncInfo(true, myNotifier);
                }
            });
        }

        PopupPanel.restoreVisibilities(myFBReaderApp);
        ApiServerImplementation.sendEvent(this, ApiListener.EVENT_READ_MODE_OPENED);
    }

    @Override
    protected void onPause() {
        getCollection().unbind();
        SyncOperations.quickSync(this, myFBReaderApp.SyncOptions);

        IsPaused = true;
        try {
            unregisterReceiver(mySyncUpdateReceiver);
        }catch (IllegalArgumentException e) {
        }

        try {
            unregisterReceiver(myBatteryInfoReceiver);
        }catch (IllegalArgumentException e) {
            // do nothing, this exception means that myBatteryInfoReceiver was not registered
        }

        myFBReaderApp.stopTimer();
        if (getZLibrary().DisableButtonLightsOption.getValue()) {
            setButtonLight(true);
        }
        myFBReaderApp.onWindowClosing();

        super.onPause();
    }

    @Override
    protected void onStop() {
        ApiServerImplementation.sendEvent(this, ApiListener.EVENT_READ_MODE_CLOSED);
        PopupPanel.removeAllWindows(myFBReaderApp, this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        getCollection().unbind();
        unbindService(DataConnection);
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLowMemory() {
        myFBReaderApp.onWindowClosing();
        super.onLowMemory();
    }

    @Override
    public boolean onSearchRequested() {
        final FBReaderApp.PopupPanel popup = myFBReaderApp.getActivePopup();
        myFBReaderApp.hideActivePopup();
        if (DeviceType.Instance().hasStandardSearchDialog()) {
            final SearchManager manager = (SearchManager)getSystemService(SEARCH_SERVICE);
            manager.setOnCancelListener(new SearchManager.OnCancelListener() {
                public void onCancel() {
                    if (popup != null) {
                        myFBReaderApp.showPopup(popup.getId());
                    }
                    manager.setOnCancelListener(null);
                }
            });
            startSearch(myFBReaderApp.MiscOptions.TextSearchPattern.getValue(), true, null, false);
        }else {
            SearchDialogUtil.showDialog(this, FBReader.class, myFBReaderApp.MiscOptions.TextSearchPattern.getValue(), new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface di) {
                    if (popup != null) {
                        myFBReaderApp.showPopup(popup.getId());
                    }
                }
            });
        }
        return true;
    }

    /**
     * 显示长按时候出现的popwindow
     */
    public void showSelectionPanel() {
        final ZLTextView view = myFBReaderApp.getTextView();
        ((SelectionPopup)myFBReaderApp.getPopupById(SelectionPopup.ID)).move(view.getSelectionStartY(), view.getSelectionEndY());
        myFBReaderApp.showPopup(SelectionPopup.ID);
    }

    public void hideSelectionPanel() {
        final FBReaderApp.PopupPanel popup = myFBReaderApp.getActivePopup();
        if (popup != null && popup.getId() == SelectionPopup.ID) {
            myFBReaderApp.hideActivePopup();
        }
    }

    private void onPreferencesUpdate(Book book) {
        AndroidFontUtil.clearFontCache();
        myFBReaderApp.onBookUpdated(book);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
            case REQUEST_PREFERENCES:
                if (resultCode != RESULT_DO_NOTHING && data != null) {
                    final Book book = FBReaderIntents.getBookExtra(data, myFBReaderApp.Collection);
                    if (book != null) {
                        getCollection().bindToService(this, new Runnable() {
                            public void run() {
                                onPreferencesUpdate(book);
                            }
                        });
                    }
                }
                break;
            case REQUEST_CANCEL_MENU:
                runCancelAction(data);
                break;
        }
    }

    private void runCancelAction(Intent intent) {
        final CancelMenuHelper.ActionType type;
        try {
            type = CancelMenuHelper.ActionType.valueOf(intent.getStringExtra(FBReaderIntents.Key.TYPE));
        }catch (Exception e) {
            // invalid (or null) type value
            return;
        }
        Bookmark bookmark = null;
        if (type == CancelMenuHelper.ActionType.returnTo) {
            bookmark = FBReaderIntents.getBookmarkExtra(intent);
            if (bookmark == null) {
                return;
            }
        }
        myFBReaderApp.runCancelAction(type, bookmark);
    }

    public void navigate() {
        ((NavigationPopup)myFBReaderApp.getPopupById(NavigationPopup.ID)).runNavigation();
    }

    private Menu addSubmenu(Menu menu, String id) {
        return menu.addSubMenu(ZLResource.resource("menu").getResource(id).getValue());
    }

    private void addMenuItem(Menu menu, String actionId, Integer iconId, String name) {
        if (name == null) {
            name = ZLResource.resource("menu").getResource(actionId).getValue();
        }
        final MenuItem menuItem = menu.add(name);
        if (iconId != null) {
            menuItem.setIcon(iconId);
        }
        menuItem.setOnMenuItemClickListener(myMenuListener);
        myMenuItemMap.put(menuItem, actionId);
    }

    private void addMenuItem(Menu menu, String actionId, String name) {
        addMenuItem(menu, actionId, null, name);
    }

    private void addMenuItem(Menu menu, String actionId, int iconId) {
        addMenuItem(menu, actionId, iconId, null);
    }

    private void addMenuItem(Menu menu, String actionId) {
        addMenuItem(menu, actionId, null, null);
    }

    private void fillMenu(Menu menu, List<MenuNode> nodes) {
        for (MenuNode n : nodes) {
            if (n instanceof MenuNode.Item) {
                final Integer iconId = ((MenuNode.Item)n).IconId;
                if (iconId != null) {
                    addMenuItem(menu, n.Code, iconId);
                }else {
                    addMenuItem(menu, n.Code);
                }
            }else /* if (n instanceof MenuNode.Submenu) */ {
                final Menu submenu = addSubmenu(menu, n.Code);
                fillMenu(submenu, ((MenuNode.Submenu)n).Children);
            }
        }
    }

    /**
     * 创建menu
     *
     * @param menu
     */
    private void setupMenu(Menu menu) {
        //获取配置的语言
        final String menuLanguage = ZLResource.getLanguageOption().getValue();

        if (menuLanguage.equals(myMenuLanguage)) {
            return;
        }

        myMenuLanguage = menuLanguage;

        menu.clear();
        //添加menu
        fillMenu(menu, MenuData.topLevelNodes());

        synchronized (myPluginActions) {
            int index = 0;
            for (PluginApi.ActionInfo info : myPluginActions) {
                if (info instanceof PluginApi.MenuActionInfo) {
                    addMenuItem(menu, PLUGIN_ACTION_PREFIX + index++, ((PluginApi.MenuActionInfo)info).MenuItemName);
                }
            }
        }

        refresh();
    }

    protected void onPluginNotFound(final Book book) {
        final BookCollectionShadow collection = getCollection();
        collection.bindToService(this, new Runnable() {
            public void run() {
                final Book recent = collection.getRecentBook(0);
                if (recent != null && !collection.sameBook(recent, book)) {
                    myFBReaderApp.openBook(recent, null, null, null);
                }else {
                    myFBReaderApp.openHelpBook();
                }
            }
        });
    }

    /**
     * 设置状态栏显示
     *
     * @param visible
     */
    private void setStatusBarVisibility(boolean visible) {
        final ZLAndroidLibrary zlibrary = getZLibrary();

        if (DeviceType.Instance() != DeviceType.KINDLE_FIRE_1ST_GENERATION && !myShowStatusBarFlag) {

            if (visible) {
                //window非全屏显示
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            }else {
                //取消window非全屏显示
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return (myMainView != null && myMainView.onKeyDown(keyCode, event)) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return (myMainView != null && myMainView.onKeyUp(keyCode, event)) || super.onKeyUp(keyCode, event);
    }

    private void setButtonLight(boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            setButtonLightInternal(enabled);
        }
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    private void setButtonLightInternal(boolean enabled) {
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.buttonBrightness = enabled ? -1.0f : 0.0f;
        getWindow().setAttributes(attrs);
    }

    private PowerManager.WakeLock myWakeLock;
    private boolean myWakeLockToCreate;
    private boolean myStartTimer;

    public final void createWakeLock() {
        if (myWakeLockToCreate) {
            synchronized (this) {
                if (myWakeLockToCreate) {
                    myWakeLockToCreate = false;
                    myWakeLock = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "FBReader");
                    myWakeLock.acquire();
                }
            }
        }
        if (myStartTimer) {
            myFBReaderApp.startTimer();
            myStartTimer = false;
        }
    }

    private final void switchWakeLock(boolean on) {
        if (on) {
            if (myWakeLock == null) {
                myWakeLockToCreate = true;
            }
        }else {
            if (myWakeLock != null) {
                synchronized (this) {
                    if (myWakeLock != null) {
                        myWakeLock.release();
                        myWakeLock = null;
                    }
                }
            }
        }
    }

    private BroadcastReceiver myBatteryInfoReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            final int level = intent.getIntExtra("level", 100);
            final ZLAndroidApplication application = (ZLAndroidApplication)getApplication();
            setBatteryLevel(level);
            switchWakeLock(hasWindowFocus() && getZLibrary().BatteryLevelToTurnScreenOffOption.getValue() < level);
            /*****************************自增*************************/
            setDianLiang(level);
        }
    };

    public BookCollectionShadow getCollection() {
        return (BookCollectionShadow)myFBReaderApp.Collection;
    }

    // methods from ZLApplicationWindow interface
    @Override
    public void showErrorMessage(String key) {
        UIMessageUtil.showErrorMessage(this, key);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showErrorMessage(String key, String parameter) {
        UIMessageUtil.showErrorMessage(this, key, parameter);
    }

    @Override
    public FBReaderApp.SynchronousExecutor createExecutor(String key) {
        return UIUtil.createExecutor(this, key);
    }

    private int myBatteryLevel;

    @Override
    public int getBatteryLevel() {
        return myBatteryLevel;
    }

    private void setBatteryLevel(int percent) {
        myBatteryLevel = percent;
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public ZLViewWidget getViewWidget() {
        return myMainView;
    }

    private final HashMap<MenuItem, String> myMenuItemMap = new HashMap<MenuItem, String>();

    private final MenuItem.OnMenuItemClickListener myMenuListener = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem item) {
            myFBReaderApp.runAction(myMenuItemMap.get(item));
            return true;
        }
    };

    @Override
    public void refresh() {
        runOnUiThread(new Runnable() {
            public void run() {
                for (Map.Entry<MenuItem, String> entry : myMenuItemMap.entrySet()) {
                    final String actionId = entry.getValue();
                    final MenuItem menuItem = entry.getKey();
                    menuItem.setVisible(myFBReaderApp.isActionVisible(actionId) && myFBReaderApp.isActionEnabled(actionId));
                    switch (myFBReaderApp.isActionChecked(actionId)) {
                        case TRUE:
                            menuItem.setCheckable(true);
                            menuItem.setChecked(true);
                            break;
                        case FALSE:
                            menuItem.setCheckable(true);
                            menuItem.setChecked(false);
                            break;
                        case UNDEFINED:
                            menuItem.setCheckable(false);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void processException(Exception exception) {
        exception.printStackTrace();

        final Intent intent = new Intent(FBReaderIntents.Action.ERROR, new Uri.Builder().scheme(exception.getClass().getSimpleName()).build());
        intent.setPackage(FBReaderIntents.DEFAULT_PACKAGE);
        intent.putExtra(ErrorKeys.MESSAGE, exception.getMessage());
        final StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        intent.putExtra(ErrorKeys.STACKTRACE, stackTrace.toString());
        /*
        if (exception instanceof BookReadingException) {
			final ZLFile file = ((BookReadingException)exception).File;
			if (file != null) {
				intent.putExtra("file", file.getPath());
			}
		}
		*/
        try {
            startActivity(intent);
        }catch (ActivityNotFoundException e) {
            // ignore
            e.printStackTrace();
        }
    }

    @Override
    public void setWindowTitle(final String title) {
        runOnUiThread(new Runnable() {
            public void run() {
                setTitle(title);
            }
        });
    }

    private BroadcastReceiver mySyncUpdateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            myFBReaderApp.useSyncInfo(myResumeTimestamp + 10 * 1000 > System.currentTimeMillis(), myNotifier);
        }
    };

    public void outlineRegion(ZLTextRegion.Soul soul) {
        myFBReaderApp.getTextView().outlineRegion(soul);
        myFBReaderApp.getViewWidget().repaint();
    }

    public void hideOutline() {
        myFBReaderApp.getTextView().hideOutline();
        myFBReaderApp.getViewWidget().repaint();
    }

    public void hideDictionarySelection() {
        myFBReaderApp.getTextView().hideOutline();
        myFBReaderApp.getTextView().removeHighlightings(DictionaryHighlighting.class);
        myFBReaderApp.getViewWidget().reset();
        myFBReaderApp.getViewWidget().repaint();
    }

    /****************自增方法****************/
    private void initAnimation() {
        mAnimSlideInTop = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_top);
        mAnimSlideOutTop = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_top);
        mAnimSlideInBottom = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_bottom);
        mAnimSlideOutBottom = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_bottom);
    }

    private void initView(){
        readSettingPopWindow = new ReadSettingPopWindow(this,imageView_menu);
        readSetColorFontPop = new ReadSetColorFontPop(this);
        imageView_back.setOnClickListener(clickListener);
        imageView_menu.setOnClickListener(clickListener);
        imageView_daynight.setOnClickListener(clickListener);
        imageView_set_mulu.setOnClickListener(clickListener);
        textView_lastChapter.setOnClickListener(clickListener);
        textView_nextchapter.setOnClickListener(clickListener);
        imageView_search.setOnClickListener(clickListener);
        textView_time.setText(ZLibrary.Instance().getCurrentTimeString());
        seekBar_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private void gotoPage(int page) {
                final ZLTextView view = myFBReaderApp.getTextView();
                if (page == 1) {
                    view.gotoHome();
                } else {
                    view.gotoPage(page);
                }
                myFBReaderApp.getViewWidget().reset();
                myFBReaderApp.getViewWidget().repaint();
                setReadInfo();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    final int page = ((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total * progress / 100;
                    gotoPage(page);
                }
            }
        });
    }

    /**
     * 点击阅读页面的打开菜单位置打开初始菜单
     */
    public void showUtil(){
        if(isShowUtil){
            if(isChenjin){
                setStatusBarVisibility(false);
//                statusView.setVisibility(View.GONE);
            }
            linearLayout_top.startAnimation(mAnimSlideOutBottom);
            linearLayout_bottom.startAnimation(mAnimSlideOutTop);
            linearLayout_top.setVisibility(View.GONE);
            linearLayout_bottom.setVisibility(View.GONE);
            isShowUtil = false;
        }else{
            if(isChenjin){
//                statusView.setVisibility(View.VISIBLE);
                setStatusBarVisibility(true);
            }
            linearLayout_top.setVisibility(View.VISIBLE);
            linearLayout_bottom.setVisibility(View.VISIBLE);
            linearLayout_top.startAnimation(mAnimSlideInBottom);
            linearLayout_bottom.startAnimation(mAnimSlideInTop);
            isShowUtil = true;
        }

    }

    public void initConfig(){
        isNightMode = myFBReaderApp.ViewOptions.ColorProfileName.getValue().equals(ColorProfile.NIGHT);
        if(isNightMode)
            imageView_daynight.setImageResource(R.drawable.read_night);
        else
            imageView_daynight.setImageResource(R.drawable.read_day);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.read_search:
                    showUtil();
                    onSearchRequested();
                    break;
                case R.id.top_title_left:
                    myFBReaderApp.closeWindow();
                    break;
                case R.id.top_title_right:
                    readSettingPopWindow.showPop();
                    break;
                case R.id.read_daynight:
                    if(isNightMode){
                        myFBReaderApp.ViewOptions.ColorProfileName.setValue(ColorProfile.DAY);
                        myFBReaderApp.getViewWidget().reset();
                        myFBReaderApp.getViewWidget().repaint();
                        imageView_daynight.setImageResource(R.drawable.read_day);
                        isNightMode = false;
                    }else{
                        myFBReaderApp.ViewOptions.ColorProfileName.setValue(ColorProfile.NIGHT);
                        myFBReaderApp.getViewWidget().reset();
                        myFBReaderApp.getViewWidget().repaint();
                        imageView_daynight.setImageResource(R.drawable.read_night);
                        isNightMode = true;
                    }
                    break;
                case R.id.read_set_mulu:
                    showUtil();
                    Intent intent = new Intent(FBReader.this, BookLabelActivity.class);
                    FBReaderIntents.putBookExtra(intent, myFBReaderApp.getCurrentBook());
                    startActivity(intent);
                    break;
                case R.id.read_util_lastchapter:
                    gotoLastChapter();
                    setReadInfo();
                    break;
                case R.id.read_util_nextchapter:
                    gotoNextChapter();
                    setReadInfo();
                    break;
            }
        }
    };

    /**
     * 一本书加载完成的监听
     * @param event
     */
    @Subscribe
    public void onEventMainThread(OpenBookDone event){
        textView_book_name.setText(myFBReaderApp.getCurrentBook().getTitle());
        textView_current_bookpage.setText(((ZLTextView)myFBReaderApp.getTextView()).pagePosition().Current+"");
        textView_total_bookpage.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total + "");
        textView_current_bookpage.setVisibility(View.VISIBLE);
        textView_total_bookpage.setVisibility(View.VISIBLE);
        textView_fenge.setVisibility(View.VISIBLE);
        textView_book_name.setVisibility(View.VISIBLE);
        batteryView.setVisibility(View.VISIBLE);
        textView_time.setText(ZLibrary.Instance().getCurrentTimeString());
        seekBar_progress.setProgress(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Current * 100 /
                ((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total);
        textView_page_now.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Current + "");
        textView_page_all.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total + "");
        textView_progress_bili.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Current * 100 /
                ((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total  + "%");
        textView_time.setVisibility(View.VISIBLE);
        batteryView.setVisibility(View.VISIBLE);
    }

    /**
     * 系统时间改变的监听
     * @param timeChange
     */
    @Subscribe
    public void onEventMainThread(TimeChange timeChange){
        textView_time.setText(ZLibrary.Instance().getCurrentTimeString());
    }

    /**
     * 是否允许音量键进行翻页
     */
    @Subscribe
    public void onEventMainThread(VolumeChangePage volumeChangePage){
        if(volumeChangePage.getIsOpen()){
            myFBReaderApp.keyBindings().bindKey(KeyEvent.KEYCODE_VOLUME_DOWN, false, ActionCode.VOLUME_KEY_SCROLL_FORWARD);
            myFBReaderApp.keyBindings().bindKey(KeyEvent.KEYCODE_VOLUME_UP, false, ActionCode.VOLUME_KEY_SCROLL_BACK);
        }else{
            myFBReaderApp.keyBindings().bindKey(KeyEvent.KEYCODE_VOLUME_DOWN, false, FBReaderApp.NoAction);
            myFBReaderApp.keyBindings().bindKey(KeyEvent.KEYCODE_VOLUME_UP, false, FBReaderApp.NoAction);
        }
    }

    /**
     * 长按是否可以画线
     * @param longClickDrawLine
     */
    @Subscribe
    public void onEventMainThread(LongClickDrawLine longClickDrawLine){
        if(longClickDrawLine.isCanDraw())
            myFBReaderApp.MiscOptions.WordTappingAction.setValue(MiscOptions.WordTappingActionEnum.startSelecting);
        else
            myFBReaderApp.MiscOptions.WordTappingAction.setValue(MiscOptions.WordTappingActionEnum.doNothing);
    }

    /**
     * 翻页动画更改
     */
    @Subscribe
    public void onEventMainThread(AnimStyle animStyle){
        if(animStyle.getStyleStr().equals("none")){
            myFBReaderApp.PageTurningOptions.Animation.setValue(ZLViewEnums.Animation.none);
        }else if(animStyle.getStyleStr().equals("curl")){
            myFBReaderApp.PageTurningOptions.Animation.setValue(ZLViewEnums.Animation.curl);
        }else if(animStyle.getStyleStr().equals("slide")){
            myFBReaderApp.PageTurningOptions.Animation.setValue(ZLViewEnums.Animation.slide);
        }else {
            myFBReaderApp.PageTurningOptions.Animation.setValue(ZLViewEnums.Animation.shift);
        }
    }

    @Subscribe
    public void onEventMainThread(final BookMarkModify bookMarkModify){
        getCollection().bindToService(this, new Runnable() {
            @Override
            public void run() {
                myFBReaderApp.getBookmark().setText(bookMarkModify.getContent());
                getCollection().saveBookmark(myFBReaderApp.getBookmark());
            }
        });
    }

    /**
     * 设置电量
     * @param level
     */
    private void setDianLiang(int level){
        batteryView.setPower(level);
    }

    /**
     * 设置相关阅读信息的显示
     */
    private void setReadInfo(){
        textView_book_name.setText(myFBReaderApp.getCurrentBook().getTitle());
        textView_current_bookpage.setText(((ZLTextView)myFBReaderApp.getTextView()).pagePosition().Current+"");
        textView_total_bookpage.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total + "");
        textView_time.setText(ZLibrary.Instance().getCurrentTimeString());
        seekBar_progress.setProgress(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Current * 100 /
                ((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total);
        textView_page_now.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Current + "");
        textView_page_all.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total + "");
        textView_progress_bili.setText(((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Current * 100 /
                ((ZLTextView) myFBReaderApp.getTextView()).pagePosition().Total + "%");
    }

    /**
     * 设置标签颜色
     */
    public void setMarkColor(final int id){
        getCollection().bindToService(this, new Runnable() {
            @Override
            public void run() {
                myFBReaderApp.getBookmark().setStyleId(id);
                getCollection().setDefaultHighlightingStyleId(id);
                getCollection().saveBookmark(myFBReaderApp.getBookmark());
            }
        });
    }

    /**
     * 删除标签
     */
    public void deleteMark(){
        getCollection().bindToService(this, new Runnable() {
            @Override
            public void run() {
                getCollection().deleteBookmark(myFBReaderApp.getBookmark());
                bookMarkEbtDb.delete(myFBReaderApp.getBookmark().BookId,myFBReaderApp.getBookmark().ParagraphIndex,
                        myFBReaderApp.getBookmark().ElementIndex);
            }
        });
        myFBReaderApp.getViewWidget().reset();
        myFBReaderApp.getViewWidget().repaint();
    }

    /**
     * 判断当前是不是最后一章或者第一章
     */
    private void checkFirstOrLastChapter(){
        int pageCurrent = myFBReaderApp.BookTextView.getStartCursor().getParagraphIndex();
        int firstPage = ((TOCTree)calculateChapterAdapter.getItem(1)).getReference().ParagraphIndex;
        int lastPage = ((TOCTree)calculateChapterAdapter.getItem(calculateChapterAdapter.getCount()-1)).getReference().ParagraphIndex;
        if(pageCurrent < firstPage){
            isFiratChapter = true;
        }else{
            isFiratChapter = false;
        }
        if(pageCurrent >=lastPage){
            isLastChapter = true;
        }else{
            isLastChapter = false;
        }
        calculateChapterNum(pageCurrent);
    }

    /**
     * 计算下一章和上一章的Index
     */
    private void calculateChapterNum(int pageCurrent){
//        if(isFiratChapter){
//            nextChapterNum = ((TOCTree)calculateChapterAdapter.getItem(1)).getReference().ParagraphIndex;
//        }else{
//            if(isLastChapter){
//                lastChapterNum = ((TOCTree)calculateChapterAdapter.getItem(calculateChapterAdapter.getCount()-2)).getReference().ParagraphIndex;
//            }
//        }
        if(!isFiratChapter && !isLastChapter){
            for(int i=1;i<calculateChapterAdapter.getCount();i++){
                if(pageCurrent>=((TOCTree)calculateChapterAdapter.getItem(i)).getReference().ParagraphIndex &&
                        pageCurrent<((TOCTree)calculateChapterAdapter.getItem(i+1)).getReference().ParagraphIndex){
                    lastChapterNum = ((TOCTree)calculateChapterAdapter.getItem(i-1)).getReference().ParagraphIndex;
                    nextChapterNum = ((TOCTree)calculateChapterAdapter.getItem(i+1)).getReference().ParagraphIndex;
                    break;
                }
            }
        }else if(isFiratChapter){
            nextChapterNum = ((TOCTree)calculateChapterAdapter.getItem(1)).getReference().ParagraphIndex;
        }else if(isLastChapter){
            lastChapterNum = ((TOCTree)calculateChapterAdapter.getItem(calculateChapterAdapter.getCount()-2)).getReference().ParagraphIndex;
        }
    }

    /**
     * 阅读上一章节
     */
    public void gotoLastChapter(){
        initTOCTreeAdapter();
        checkFirstOrLastChapter();
        if(isFiratChapter){
            Toast.makeText(this,"当前已经是第一章节",Toast.LENGTH_SHORT).show();
            return;
        }else{
            myFBReaderApp.addInvisibleBookmark();
            myFBReaderApp.BookTextView.gotoPosition(lastChapterNum, 0, 0);
            myFBReaderApp.showBookTextView();
            myFBReaderApp.storePosition();
        }
    }

    /**
     * 阅读下一章节
     */
    public void gotoNextChapter(){
        initTOCTreeAdapter();
        checkFirstOrLastChapter();
        if(isLastChapter){
            Toast.makeText(this,"当前已经是最后章节",Toast.LENGTH_SHORT).show();
            return;
        }else{
            myFBReaderApp.addInvisibleBookmark();
            myFBReaderApp.BookTextView.gotoPosition(nextChapterNum, 0, 0);
            myFBReaderApp.showBookTextView();
            myFBReaderApp.storePosition();
        }
    }

    /**
     * 初始化adapter
     */
    public void initTOCTreeAdapter(){
        if(root == null){
            root = myFBReaderApp.Model.TOCTree;
            calculateChapterAdapter = new CalculateChapterAdapter(new ListView(this),root);
        }
    }

    /**
     * 用于装载TOCTree，已便于取出其中的TOCTree，来进行相应计算
     */
    private class CalculateChapterAdapter extends ZLTreeAdapter{

        CalculateChapterAdapter(ListView listView,TOCTree tocTree){
            super(listView,tocTree);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    /**
     * 生成站位view
     */
    private void initStatusView(Activity activity,int color){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //生成一个状态栏大小的矩形
            statusView = createStatusView(activity,color);
            statusView.setVisibility(View.GONE);
            //添加到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            //设置根布局参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 生成一个和状态栏一样大小的矩形色块
     */
    private View createStatusView(Activity activity,int color){
        //获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height","dimen","android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        //绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        view_zhanwei.setLayoutParams(params);
        return statusView;
    }

}
