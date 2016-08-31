package com.ebtang.ebtangebook.view.scan;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.db.read.LocalFile;
import com.ebtang.ebtangebook.db.read.LocalFileDb;
import com.ebtang.ebtangebook.view.scan.adapter.AutoScanAdapter;
import com.ebtang.ebtangebook.view.scan.adapter.FileAdapter;
import com.ebtang.ebtangebook.view.scan.fragment.LocalFileListFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/10 0010.
 */
public class AutoScanActivity extends BaseActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.top_title_right)
    TextView textView_select;
    @Bind(R.id.auto_scan_lv)
    ListView listView;
    private static TextView textView_bt;
    @Bind(R.id.auto_scan_txt_count)
    TextView textView_txt_count;
    @Bind(R.id.auto_scan_epub_count)
    TextView textView_epub_count;
    @Bind(R.id.auto_scan_status)
    TextView textView_status;
    @Bind(R.id.auto_scan_none)
    TextView textView_none;
    static List<File> fileList;
    private File root;

    protected AutoScanAdapter adapter;

    private static HashMap<Integer,Boolean> isSelected = new HashMap<>();
    public static int checkNum1 = 0; // 记录选中的条目数量
    public static ArrayList<String> paths = new ArrayList<String>();
    public static Map<String,Integer> mapin = new HashMap<String, Integer>() ;

    private int txt_count = 0;
    private int epub_count = 0;

    private boolean isSelectAll = true;//当前title右按钮是不是全选
    private LocalFileDb localFileDb;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    changeCount(msg.arg1);
                    break;
                case 2:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(fileList.size()>0){
                                adapter.notifyDataSetChanged();
                            }else{
                                textView_none.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }
                            textView_status.setText("扫描完成，共扫描到"+fileList.size()+"个文件");
                        }
                    },200);
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_scan_activity);
        localFileDb = new LocalFileDb(this);
        fileList = new ArrayList<File>();
        root = Environment.getExternalStorageDirectory();
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        textView_title.setText("本地文件扫描");
        textView_bt = (TextView)findViewById(R.id.auto_scan_bt);
        imageView_back.setOnClickListener(this);
        textView_select.setOnClickListener(this);
        textView_bt.setOnClickListener(this);
    }

    @Override
    public void initData() {
        adapter = new AutoScanAdapter(this,fileList,isSelected);
        listView.setAdapter(adapter);
        new Thread(){
            @Override
            public void run() {
                readFile();
            }
        }.start();
//        getAllFiles(root);
    }


    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
    private void getAllFiles(File root){
        File files[] = root.listFiles();
        if(files != null){
            for (File f : files){
                if(f.isDirectory()){
                    getAllFiles(f);
                }else{
                    System.out.println(f);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auto_scan_bt:
                if(mapin.size()==0){
                    Toast.makeText(AutoScanActivity.this,"请选择你要导入的文件",Toast.LENGTH_SHORT).show();
                }else{
                    for(String key : mapin.keySet()){
                        String lastName = key.substring(key.lastIndexOf("/")+1);
                        String title = lastName.substring(0, lastName.lastIndexOf("."));
                        int type = lastName.contains("txt")? 1 : 2 ;
                        if(localFileDb.find(key,title) == null){
                            LocalFile localFile = new LocalFile();
                            localFile.setPath(key);
                            localFile.setType(type);
                            localFile.setName(title);
                            localFile.setTime(System.currentTimeMillis());
                            localFile.setLocalBook(1);
                            localFileDb.insert(localFile);
                        }
                    }
                    Toast.makeText(AutoScanActivity.this,"导入成功",Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.top_title_left:
                finish();
                break;
            case R.id.top_title_right:
                if(fileList.size()>0){
                    if(isSelectAll){
                        for(int i=0;i<fileList.size();i++){
                            String p =AutoScanActivity.paths.get(i);//减去文件夹的数量
                            String lastName = p.substring(p.lastIndexOf("/") + 1);
                            String title = lastName.substring(0, lastName.lastIndexOf("."));
                            if(localFileDb.find(p,title) != null) {
                                //把num 与position 对应起来
                                isSelected.put(i,false);
                                if (AutoScanActivity.mapin.containsKey(p)) {
                                    AutoScanActivity.mapin.remove(p);
                                }
                            }else{
                                //把num 与position 对应起来
                                isSelected.put(i,true);
                                if (!AutoScanActivity.mapin.containsKey(p)) {
                                    AutoScanActivity.mapin.put(p,i);
                                }
                            }
                        }
                        isSelectAll = false;
                        if(mapin.size()>0)
                            textView_select.setText("撤销");
                        else
                            Toast.makeText(AutoScanActivity.this,"已全部导入",Toast.LENGTH_SHORT).show();
                    }else{
                        for(int i=0;i<fileList.size();i++){
                            isSelected.put(i,false);
                            String p =AutoScanActivity.paths.get(i);//减去文件夹的数量
                            //把num 与position 对应起来
                            if (!AutoScanActivity.mapin.containsKey(p)) {
                                AutoScanActivity.mapin.put(p,i);
                            }
                        }
                        isSelectAll = true;
                        textView_select.setText("全选");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    //checkBox的选择状态hashMap
    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    public void dataChanged() {
        // 通知listView刷新
        adapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        textView_bt.setText("导入书架(" + checkNum1 + ")项");
        List<LocalFile> localFiles = localFileDb.findAllBook();
        if(checkNum1+localFiles.size() == fileList.size()){
            isSelectAll = false;
            textView_select.setText("撤销");
        }else {
            isSelectAll = true;
            textView_select.setText("全选");
        }
    }

    private void readFile() {
        final File[] file = new File(root.getAbsolutePath()).listFiles();//设定扫描路径
        readFile(file, true);
    }
    private void readFile(final File[] file,boolean isFirstIn) {
        for(int i=0 ; file!= null && i<file.length ;i++) {
            try {
                //判读是否文件以及文件后缀名
                if (file[i].isFile()) {
                    if (file[i].getName().endsWith("txt")) {
                        fileList.add(file[i]);
                        txt_count++;
                        paths.add(file[i].toString());
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.arg1 = txt_count + epub_count - 1;
                        handler.sendMessage(message);
                    } else if (file[i].getName().endsWith("epub")) {
                        fileList.add(file[i]);
                        epub_count++;
                        paths.add(file[i].toString());
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.arg1 = txt_count + epub_count - 1;
                        handler.sendMessage(message);
                    }
                }
                //如果是文件夹，递归扫描
                else if (file[i].isDirectory()) {
                    final File[] newFileList = new File(file[i].getAbsolutePath()).listFiles();
                    readFile(newFileList, false);
                    //通过多线程来加速
                    /*new Thread(new Runnable() {
                        public void run() {
                            readFile(newFileList);
                        }
                    }).start();*/
                }
            }catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }

        if(isFirstIn){
            handler.sendEmptyMessage(2);
        }
    }

    private void changeCount(int position){
        isSelected.put(position, false);
//        adapter.notifyDataSetChanged();
        textView_txt_count.setText("("+txt_count+")");
        textView_epub_count.setText("("+epub_count+")");
    }

}
