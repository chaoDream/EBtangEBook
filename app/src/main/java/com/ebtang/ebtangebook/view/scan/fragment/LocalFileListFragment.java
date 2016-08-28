package com.ebtang.ebtangebook.view.scan.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.view.scan.adapter.FileAdapter;
import com.ebtang.ebtangebook.view.scan.bean.BookList;
import com.ebtang.ebtangebook.view.scan.util.Fileutil;
import com.ebtang.ebtangebook.widget.myWebView.WebViewActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/10 0010.
 */
public class LocalFileListFragment extends BaseFragment{

    private File root;
    @Bind(R.id.local_File_drawer)
    ListView listView;
    @Bind(R.id.local_File_return_btn)
    ImageButton returnBtn;
    @Bind(R.id.local_File_title)
    TextView titleView;

    private static Button chooseAllButton;
    private static Button deleteButton;
    private static Button addfileButton;

    private static HashMap<Integer,Boolean> isSelected;
    public static int checkNum1 = 0; // 记录选中的条目数量
    public static ArrayList<String> paths = new ArrayList<String>();
    public static Map<String,Integer> mapin = new HashMap<String, Integer>() ;
    private Map<String, String> map = null;// 这个用来保存复制和粘贴的路径

    protected List<AsyncTask<Void, Void, Boolean>> myAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();

    public static final int EXTERNAL_STORAGE_REQ_CODE = 10 ;
    // path的堆栈
    private static Stack<String> pathStack;
    private List<File> listFile;

    private static FileAdapter adapter;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.local_file_scan,container,false);
        ButterKnife.bind(this,rootView);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.) {
//            checkPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE, EXTERNAL_STORAGE_REQ_CODE);
//        }
        root = Environment.getExternalStorageDirectory();
        initView();
        handledButtonListener();
        return rootView;
    }

    @Override
    public void initView() {
        chooseAllButton = (Button) rootView.findViewById(R.id.choose_all);
        deleteButton = (Button) rootView.findViewById(R.id.delete);
        addfileButton = (Button) rootView.findViewById(R.id.add_file);
        adapter = new FileAdapter(getActivity(), listFile, isSelected);
        listView.setAdapter(adapter);
        map = new HashMap<String, String>();
        listView.setOnItemClickListener(new DrawerItemClickListener());
        listView.setOnItemLongClickListener(new DrawerItemClickListener());//

        searchData(root.getAbsolutePath());
        addPath(root.getAbsolutePath());
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
    private void handledButtonListener () {
        /**
         * 返回上一文件层
         * */
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLastPath().equals(root.getAbsolutePath())) {
                    return;
                }
                Fileutil.folderNum = 0;
                LocalFileListFragment.paths.clear();
                mapin.clear();
                removeLastPath();//从栈中移除当前路径，得到上一文件层路径
                searchData(getLastPath());
            }
        });
        /**
         * 全选
         * */
        chooseAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = Fileutil.getFileNum(listFile);
                int j = listFile.size()-num;  //获得选择时的position
                for ( int k = j ; k < listFile.size() ; k++) {
                    LocalFileListFragment.getIsSelected().put(k, true);

                    if(!mapin.containsKey(paths.get(k-j))) {
                        mapin.put(paths.get(k-j),k);
                    }
                }
                checkNum1 = FileAdapter.CheckNum(LocalFileListFragment.getIsSelected());
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });
        /**
         * 取消选择
         * */
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listFile.size(); i++) {
                    LocalFileListFragment.getIsSelected().put(i, false);
                    checkNum1 = FileAdapter.CheckNum(LocalFileListFragment.getIsSelected());

                    mapin.clear();

                    dataChanged();
                }
            }
        });
        /**
         * 把已经选择的书加入书架
         * */
        addfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Map<String, Integer> map = mapin;
//
//                int addNum = FileAdapter.CheckNum(LocalFileListFragment.getIsSelected());
//                if (addNum > 0) {
//                    for (String key : map.keySet()) {
//                        BookList bookList = new BookList();
//                        File file = new File(key);
//                        String bookName = Fileutil.getFileNameNoEx(file.getName());
//                        bookList.setBookname(bookName);
//                        bookList.setBookpath(key);
//                        saveBooktoSqlite3(bookName, key, bookList);//开启线程存储书到数据库
//                    }
//                }
            }
        });
    }
    //checkBox的选择状态hashMap
    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
        LocalFileListFragment.isSelected = isSelected;
    }
    /**
     * 获取堆栈最上层的路径
     *
     * @return
     */
    public String getLastPath() {
        return pathStack.lastElement();
    }
    /**
     * 移除堆栈最上层路径
     */
    public void removeLastPath() {
        pathStack.remove(getLastPath());
    }
    public static void dataChanged() {
        // 通知listView刷新
        adapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        addfileButton.setText("加入书架("+ checkNum1 + ")项");

    }

    private void checkPermission (Activity thisActivity, String permission, int requestCode) {
        //判断当前Activity是否已经获得了该权限
//        if(ContextCompat.checkSelfPermission(thisActivity, permission) != PackageManager.PERMISSION_GRANTED) {
//            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
//            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
//                    permission)) {
//                Toast.makeText(getActivity(), "添加图书需要此权限，请允许", Toast.LENGTH_SHORT).show();
//                //进行权限请求
//                ActivityCompat.requestPermissions(thisActivity,
//                        new String[]{permission},
//                        requestCode);
//            } else {
//                //进行权限请求
//                ActivityCompat.requestPermissions(thisActivity,
//                        new String[]{permission},
//                        requestCode);
//            }
//        } else {
//
//        }
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener,ListView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            adapter.setSelectedPosition(position);

            selectItem(position);

        }

        @Override
        public boolean  onItemLongClick (AdapterView<?> parent, View view, int position,
                                         long id) {

            return true;
        }

    }

    /**
     * 查询调用方法
     */
    public void searchData(String path) {
        searchViewData(path);
        titleView.setText(path);
    }

    /**
     * 添加路径到堆栈
     *
     * @param path
     */
    public void addPath(String path) {

        if (pathStack == null) {
            pathStack = new Stack<String>();
        }
        pathStack.add(path);
    }

    /**
     * 异步查询view的数据
     */
    public void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
        myAsyncTasks.add(asyncTask.execute());
    }

    public void searchViewData(final String path) {

        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    listFile = Fileutil.getFileListByPath(path);
                } catch (Exception e) {
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);

                if (result) {
                    adapter.setFiles(listFile);  //list值传到adapter
                    isSelected = new HashMap<Integer, Boolean>();//异步线程后checkBox初始赋值
                    for (int i = 0; i < listFile.size(); i++) {
                        getIsSelected().put(i, false);
                    }
                    adapter.setSelectedPosition(-1);
                    addfileButton.setText("加入书架(" + 0 + ")项");//异步线程后重新执行初始化
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "查询失败", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    /**
     * item点击时分文件夹和文件，是文件夹则继续进入，是文件则打开
     * @param position
     */
    private void selectItem(int position) {
        BookList bookList = new BookList();
        String filePath = adapter.getFiles().get(position).getAbsolutePath();
        String fileName = adapter.getFiles().get(position).getName();

        if (adapter.getFiles().get(position).isDirectory()) {
            Fileutil.folderNum = 0;
            mapin.clear();
            LocalFileListFragment.paths.clear();
            searchData(filePath);
            addPath(filePath);
        } else if (adapter.getFiles().get(position).isFile()) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), WebViewActivity.class);
            intent.putExtra(Constants.APP_WEBVIEW_TITLE, "阅读");
            intent.putExtra(Constants.APP_WEBVIEW_URL,"http://www.jd.com/");
            startActivity(intent);
        }
    }

}
