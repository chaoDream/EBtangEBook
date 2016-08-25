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

package org.geometerplus.zlibrary.core.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.ebtang.ebtangebook.R;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.zlibrary.core.application.ZLApplication;
import org.geometerplus.zlibrary.core.library.ZLibrary;
import org.geometerplus.zlibrary.core.util.ZLColor;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidApplication;

public abstract class SelectionCursor {

    public enum Which {
        Left,
        Right
    }

    public static void draw(ZLPaintContext context, Which which, int x, int y, ZLColor color) {
        context.setFillColor(color);
        final int dpi = ZLibrary.Instance().getDisplayDPI();
        final int unit = dpi / 120;
        final int xCenter = which == Which.Left ? x - unit - 1 : x + unit + 1;
        final Bitmap bitmap_left = BitmapFactory.decodeResource(((FBReader)ZLApplication.Instance().getWindow()).getResources(), R.drawable.select_left);
        final Bitmap bitmap_right = BitmapFactory.decodeResource(((FBReader)ZLApplication.Instance().getWindow()).getResources(), R.drawable.select_right);
        final Rect srcRect = new Rect(0,0,bitmap_left.getWidth(),bitmap_left.getHeight());
//        context.fillRectangle(xCenter - unit, y + dpi / 8, xCenter + unit, y - dpi / 8);
        if (which == Which.Left) {
//            context.fillCircle(xCenter, y - dpi / 8, unit * 6);
            // 计算左边位置
            int left = x - bitmap_left.getWidth() / 2;
            // 计算上边位置
            int top = y -  bitmap_left.getHeight();
            final Rect descRect = new Rect(left,top,left+bitmap_left.getWidth(),top+bitmap_left.getHeight());
            context.fillBitMap(bitmap_left,srcRect,descRect);
        }else {
//            context.fillCircle(xCenter, y + dpi / 8, unit * 6);
            int left = x - bitmap_left.getWidth() / 2;
            // 计算上边位置
            int top = y;
            final Rect descRect = new Rect(left,top,left+bitmap_left.getWidth(),top+bitmap_left.getHeight());
            context.fillBitMap(bitmap_right,srcRect,descRect);
        }
    }
}
