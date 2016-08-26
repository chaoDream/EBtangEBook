/*
 * Copyright (C) 2007-2015 FBReader.ORG Limited <contact@fbreader.org>
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

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.view.setting.SettingInfoEditActivity;

import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.resources.ZLResource;

/**
 * 长按文本，显示的文本框
 */
class SelectionPopup extends PopupPanel implements View.OnClickListener {

    final static String ID = "SelectionPopup";

    SelectionPopup(FBReaderApp fbReader) {
        super(fbReader);
    }

    @Override
    public String getId() {
        return ID;
    }

    private LinearLayout linearLayout_huaxian,linearLayout_color;
    private TextView textView_mark;
    private ImageView imageView_delete,imageView_color_yellow,imageView_color_red,imageView_color_zi;

    private FBReader fbReader;

    @Override
    public void createControlPanel(FBReader activity, RelativeLayout root) {
        if (myWindow != null && activity == myWindow.getContext()) {
            return;
        }
        fbReader = getMyActivity();
        activity.getLayoutInflater().inflate(R.layout.selection_panel, root);
        myWindow = (SimplePopupWindow)root.findViewById(R.id.selection_panel);
        initView();
        final ZLResource resource = ZLResource.resource("selectionPopup");
//        setupButton(R.id.selection_panel_copy, resource.getResource("copyToClipboard").getValue());
        setupButton(R.id.selection_panel_share, resource.getResource("share").getValue());
//        setupButton(R.id.selection_panel_translate, resource.getResource("translate").getValue());
        setupButton(R.id.selection_panel_bookmark, resource.getResource("bookmark").getValue());
        setupButton(R.id.selection_panel_close, resource.getResource("close").getValue());
    }

    private void initView(){
        linearLayout_huaxian = (LinearLayout)myWindow.findViewById(R.id.selection_panel_bookmark);
        linearLayout_color = (LinearLayout)myWindow.findViewById(R.id.selection_panel_color_body);
        textView_mark = (TextView)myWindow.findViewById(R.id.selection_panel_mark);
        imageView_delete = (ImageView)myWindow.findViewById(R.id.selection_panel_delete);
        imageView_color_yellow = (ImageView)myWindow.findViewById(R.id.selection_panel_color_yellow);
        imageView_color_red = (ImageView)myWindow.findViewById(R.id.selection_panel_color_rea);
        imageView_color_zi = (ImageView)myWindow.findViewById(R.id.selection_panel_color_zi);
        imageView_color_yellow.setOnClickListener(this);
        imageView_color_red.setOnClickListener(this);
        imageView_color_zi.setOnClickListener(this);
        imageView_delete.setOnClickListener(this);
        textView_mark.setOnClickListener(this);
        if(fbReader.myFBReaderApp.isLongClickShowSelectPop()){
            linearLayout_huaxian.setVisibility(View.VISIBLE);
            linearLayout_color.setVisibility(View.GONE);
            imageView_delete.setVisibility(View.GONE);
            textView_mark.setVisibility(View.GONE);
        }else{
            linearLayout_huaxian.setVisibility(View.GONE);
            linearLayout_color.setVisibility(View.VISIBLE);
            imageView_delete.setVisibility(View.VISIBLE);
            textView_mark.setVisibility(View.VISIBLE);
        }
    }

    private void setupButton(int buttonId, String description) {
        final View button = myWindow.findViewById(buttonId);
        button.setOnClickListener(this);
        button.setContentDescription(description);
    }

    public void move(int selectionStartY, int selectionEndY) {
        if (myWindow == null) {
            return;
        }
        initView();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        final int verticalPosition;
        final int screenHeight = ((View)myWindow.getParent()).getHeight();
        final int diffTop = screenHeight - selectionEndY;
        final int diffBottom = selectionStartY;
        if (diffTop > diffBottom) {
            verticalPosition = diffTop > myWindow.getHeight() + 20 ? RelativeLayout.ALIGN_PARENT_BOTTOM : RelativeLayout.CENTER_VERTICAL;
        }else {
            verticalPosition = diffBottom > myWindow.getHeight() + 20 ? RelativeLayout.ALIGN_PARENT_TOP : RelativeLayout.CENTER_VERTICAL;
        }

        layoutParams.addRule(verticalPosition);
        myWindow.setLayoutParams(layoutParams);
    }

    @Override
    protected void update() {
    }

    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.selection_panel_copy:
//                Application.runAction(ActionCode.SELECTION_COPY_TO_CLIPBOARD);
//                break;
            case R.id.selection_panel_share:
                Application.runAction(ActionCode.SELECTION_SHARE);
                break;
//            case R.id.selection_panel_translate:
//                Application.runAction(ActionCode.SELECTION_TRANSLATE);
//                break;
            case R.id.selection_panel_bookmark:
                Application.runAction(ActionCode.SELECTION_BOOKMARK);
                break;
            case R.id.selection_panel_close:
                Application.runAction(ActionCode.SELECTION_CLEAR);
                break;
            case R.id.selection_panel_color_yellow:
                fbReader.setMarkColor(1);
                break;
            case R.id.selection_panel_color_rea:
                fbReader.setMarkColor(2);
                break;
            case R.id.selection_panel_color_zi:
                fbReader.setMarkColor(3);
                break;
            case R.id.selection_panel_delete:
                fbReader.deleteMark();
                break;
            case R.id.selection_panel_mark:
                Intent intent = new Intent(fbReader, SettingInfoEditActivity.class);
                intent.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE, Constants.SETTING_EDIT_MODE_BOOKMARK);
                intent.putExtra(IntentConfig.BOOK_MARK_CONTENT,fbReader.myFBReaderApp.getBookmark().getText());
                fbReader.startActivity(intent);
                break;
        }
        Application.hideActivePopup();
    }
}
