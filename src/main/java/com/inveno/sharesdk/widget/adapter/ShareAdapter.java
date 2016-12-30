package com.inveno.sharesdk.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inveno.sharesdk.R;
import com.inveno.sharesdk.data.ShareList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng.hu on 2016/4/11.
 */
public class ShareAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public ShareAdapter(Context context, ArrayList<String> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if ((this.list != null) && (!this.list.isEmpty()))
            return this.list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.share_item_view, null);
            hoder = new ViewHoder();
            hoder.platformLogo = ((ImageView)convertView.findViewById(R.id.image));
            hoder.platformName = ((TextView)convertView.findViewById(R.id.title));
            convertView.setTag(hoder);
        } else {
            hoder = (ViewHoder)convertView.getTag();
        }

        fillView(hoder, position);

        return convertView;
    }

    private void fillView(ViewHoder hoder, int position)
    {
        hoder.platformLogo.setImageResource(ShareList.getLogo((String)this.list.get(position), this.context));
        hoder.platformName.setText(ShareList.getTitle((String) this.list.get(position), this.context));

    }

    protected class ViewHoder
    {
        ImageView platformLogo;
        TextView platformName;
    }
}
