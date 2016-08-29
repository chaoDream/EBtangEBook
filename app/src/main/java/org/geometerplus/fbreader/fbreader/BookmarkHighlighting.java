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

package org.geometerplus.fbreader.fbreader;

import android.graphics.Color;

import org.geometerplus.fbreader.book.Bookmark;
import org.geometerplus.fbreader.book.HighlightingStyle;
import org.geometerplus.fbreader.book.IBookCollection;
import org.geometerplus.zlibrary.core.util.ZLColor;
import org.geometerplus.zlibrary.text.view.ZLTextFixedPosition;
import org.geometerplus.zlibrary.text.view.ZLTextPosition;
import org.geometerplus.zlibrary.text.view.ZLTextSimpleHighlighting;
import org.geometerplus.zlibrary.text.view.ZLTextView;

public final class BookmarkHighlighting extends ZLTextSimpleHighlighting {

    final IBookCollection Collection;
    final org.geometerplus.fbreader.book.Bookmark Bookmark;

    private static ZLTextPosition startPosition(org.geometerplus.fbreader.book.Bookmark bookmark) {
        return new ZLTextFixedPosition(bookmark.getParagraphIndex(), bookmark.getElementIndex(), 0);
    }

    private static ZLTextPosition endPosition(org.geometerplus.fbreader.book.Bookmark bookmark) {
        final ZLTextPosition end = bookmark.getEnd();
        if (end != null) {
            return end;
        }
        // TODO: compute end and save bookmark
        return bookmark;
    }

    BookmarkHighlighting(ZLTextView view, IBookCollection collection, org.geometerplus.fbreader.book.Bookmark bookmark) {
        super(view, startPosition(bookmark), endPosition(bookmark));
        Collection = collection;
        Bookmark = bookmark;
    }

    @Override
    public ZLColor getBackgroundColor() {
//        final HighlightingStyle bmStyle = Collection.getHighlightingStyle(Bookmark.getStyleId());
//        return bmStyle != null ? bmStyle.getBackgroundColor() : null;
        ZLColor zlColor;
        switch (Bookmark.getStyleId()){
            case 1:
                zlColor =  new ZLColor(-541952);
                break;
            case 2:
                zlColor =  new ZLColor(-1166541);
                break;
            default:
                zlColor =  new ZLColor(-10803048);
                break;
        }
        return zlColor;
    }

    @Override
    public ZLColor getForegroundColor() {
        final HighlightingStyle bmStyle = Collection.getHighlightingStyle(Bookmark.getStyleId());
        return bmStyle != null ? bmStyle.getForegroundColor() : null;
    }

    @Override
    public ZLColor getOutlineColor() {
        return null;
    }
}
