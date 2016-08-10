package com.ebtang.ebtangebook.view.scan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.scan.AutoScanActivity;
import com.ebtang.ebtangebook.view.scan.bean.BookList;
import com.ebtang.ebtangebook.view.scan.fragment.LocalFileListFragment;
import com.ebtang.ebtangebook.view.scan.util.Fileutil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/19.
 */
public  class AutoScanAdapter extends BaseAdapter {
    private List<File> files;

    private Context context;

    protected Map<String, Integer> map;

    private int selectedPosition = -1;

    public static int checkNum = 0; // 记录选中的条目数量

    private  HashMap<Integer,Boolean> isSelected;

    public static List<BookList> bookLists = new ArrayList<>();
    public static List<BookList> bookLists1 = new ArrayList<>();
  //  public static  BookList bookList = new BookList();

    public AutoScanAdapter(Context context) {
        this.context = context;
    }

    public AutoScanAdapter(Context context, List<File> files) {
        this.context = context;
        this.files = files;
    }

    public AutoScanAdapter(Context context, List<File> files, HashMap<Integer, Boolean> isSelected) {
        this.context = context;
        this.files = files;
        this.isSelected = isSelected;
    }

    @Override
    public int getCount() {
        if (files == null) {
            return 0;
        }
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        map = new HashMap<String, Integer>();
      final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.fileactivity_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView
                    .findViewById(R.id.local_file_text);
            viewHolder.textSize = (TextView) convertView
                    .findViewById(R.id.local_file_text_size);
            viewHolder.fileIcon = (ImageView) convertView
                    .findViewById(R.id.local_file_icon);
            viewHolder.checkBox = (CheckBox) convertView
                    .findViewById(R.id.local_file_image);
            viewHolder.linearLayout = (LinearLayout) convertView
                    .findViewById(R.id.local_file_lin);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (selectedPosition == position) {
            viewHolder.textView.setSelected(true);
           // viewHolder.linearLayout.setBackgroundColor(context.getResources()
                  //  .getColor(R.color.skyblue));
        } else {
            viewHolder.textView.setSelected(false);
            viewHolder.linearLayout.setBackgroundColor(Color.TRANSPARENT);
        }

        viewHolder.textView.setText(files.get(position).getName());//设置文件名
        //CheckBox状态变化监听
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSelected.put(position, viewHolder.checkBox.isChecked());
                AutoScanActivity.checkNum1 = CheckNum(AutoScanActivity.getIsSelected());

                String p =AutoScanActivity.paths.get(position);//减去文件夹的数量

                //把num 与position 对应起来

                if (viewHolder.checkBox.isChecked() == true) {

                    if (!AutoScanActivity.mapin.containsKey(p)) {

                        AutoScanActivity.mapin.put(p,position);
                    }

                }else if (viewHolder.checkBox.isChecked() == false) {

                    AutoScanActivity.mapin.remove(p);
                }

                ((AutoScanActivity)context).dataChanged();
                  //  Log.d("FileAdapter",p + "");
            }
        });

        //文件夹和文件逻辑判断
        if (files.get(position).isDirectory()) {
            viewHolder.fileIcon.setImageResource(R.drawable.folder);
          //  viewHolder.fileImage.setImageResource(R.drawable.file_folder);
            viewHolder.checkBox.setVisibility(View.INVISIBLE);
            viewHolder.textSize.setText("项");

        } else {
            if(files.get(position).getName().contains(".txt"))
                viewHolder.fileIcon.setImageResource(R.drawable.file_type_txt);
            else
                viewHolder.fileIcon.setImageResource(R.drawable.file_type_epub);
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            viewHolder.checkBox.setChecked(isSelected.get(position));
            viewHolder.textSize.setText(Fileutil.formatFileSize(files.get(position).length()));
        }

        return convertView;
    }

    class ViewHolder {
        TextView textView;
        TextView textSize;
        ImageView fileIcon;
        CheckBox checkBox;
        LinearLayout linearLayout;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static int CheckNum (HashMap<Integer,Boolean> isSelected ) {
        int i = 0 ;
        HashMap<Integer,Boolean> map = isSelected ;
        List<Boolean> isCheck = new ArrayList<>();
        //遍历Key Value
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
         //   System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            isCheck.add(entry.getValue());
        }
           //取出所有为true的数量
        for (int j =0 ; j <isCheck.size();j++) {
            if (isCheck.get(j)) {
                i++;
            }
        }
         return i;
    }

}
