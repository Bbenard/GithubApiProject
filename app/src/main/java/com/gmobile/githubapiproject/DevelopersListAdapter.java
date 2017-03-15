package com.gmobile.githubapiproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import static android.R.attr.id;
import static com.gmobile.githubapiproject.R.id.developercard;

/**
 * Created by mark on 3/13/17.
 */
public class DevelopersListAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity mActivity;
    private List<DevelopersItem> mdevelopersItem;
    ViewHolder holder = null;
    private ImageLoader mImageloader;
    private ImageCircular mImageCircular;
    private LayoutInflater mInflater;
    private DevelopersItem item;


    public DevelopersListAdapter(Activity mActivity, List<DevelopersItem> mdevelopersItem) {
        this.mActivity = mActivity;
        this.mdevelopersItem = mdevelopersItem;


    }

    @Override
    public int getCount() {
        return mdevelopersItem.size();
    }

    @Override
    public Object getItem(int location) {
        return mdevelopersItem.get(location);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (mInflater == null)
            mInflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.developer_item, null);
            holder = new ViewHolder(convertView);
            holder.developercard.setOnClickListener(this);
            holder.username.setOnClickListener(this);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        item = (DevelopersItem) getItem(position);
        holder.username.setText(item.getUsername());
        mImageloader = MainApplication.getInstance().getImageLoader();
        mImageloader.get(item.getUserimage(), new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    mImageCircular = new ImageCircular(response.getBitmap());
                    holder.photo.setImageDrawable(mImageCircular);
                }
            }
        });



        return convertView;
    }


    private class ViewHolder {

        TextView username;
        ImageView photo;
        LinearLayout developercard;

        public ViewHolder(View v) {
            username = (TextView) v.findViewById(R.id.developername);
            photo = (ImageView) v.findViewById(R.id.picture);
            developercard = (LinearLayout) v.findViewById(R.id.developercard);

        }


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == holder.developercard.getId()){
            Bundle detailsbundle = new Bundle();
            detailsbundle.putString("Username",item.getUsername( ));
            detailsbundle.putString("Photo", item.getUserimage());
            detailsbundle.putString("Profileurl", item.getProfieUrl());
            Intent startact = new Intent(MainApplication.getInstance(), ProfileActivity.class);

            startact.putExtras(detailsbundle);
            MainApplication.getInstance().startActivity(startact);
        }else if(v.getId() == holder.username.getId()){
            Bundle detailsbundle = new Bundle();
            detailsbundle.putString("Username",item.getUsername( ));
            detailsbundle.putString("Photo", item.getUserimage());
            detailsbundle.putString("Profileurl", item.getProfieUrl());
            Intent startact = new Intent(MainApplication.getInstance(), ProfileActivity.class);

            startact.putExtras(detailsbundle);
            MainApplication.getInstance().startActivity(startact);

        }

    }
}
