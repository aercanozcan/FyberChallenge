package com.ercan.fyberchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ercan.fyberchallenge.R;
import com.ercan.fyberchallenge.data.model.rest.Offer;
import com.ercan.fyberchallenge.data.model.rest.OfferType;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ercan on 2/11/2016.
 */
public class OfferListAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private List<Offer> offers;


    public OfferListAdapter(Context context, List<Offer> offers) {
        inflater = LayoutInflater.from(context);
        this.offers = offers;
    }

    @Override
    public int getCount() {
        return offers == null ? 0 : offers.size();
    }

    @Override
    public Offer getItem(int position) {
        return offers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Offer offer = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_offer_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.lytOfferTypes.removeAllViews();
        Picasso.with(convertView.getContext()).load(offer.getThumbnail().getHiRes()).into(viewHolder.imgThumbnail);
        viewHolder.txtTitle.setText(offer.getTitle());
        viewHolder.txtReward.setText("+" + offer.getPayout());
        viewHolder.txtRequiredActions.setText(offer.getRequiredActions());

        for (OfferType offerType : offer.getOfferTypes()) {
            TextView textView = (TextView) inflater.inflate(R.layout.item_offer_type_layout, viewHolder.lytOfferTypes, false);
            textView.setText(offerType.getReadable());
            viewHolder.lytOfferTypes.addView(textView);
        }

        convertView.setTag(viewHolder);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_offer_layout.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.imgThumbnail)
        ImageView imgThumbnail;
        @Bind(R.id.txtTitle)
        TextView txtTitle;
        @Bind(R.id.lytOfferTypes)
        LinearLayout lytOfferTypes;
        @Bind(R.id.txtRequiredActions)
        TextView txtRequiredActions;
        @Bind(R.id.txtReward)
        TextView txtReward;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
