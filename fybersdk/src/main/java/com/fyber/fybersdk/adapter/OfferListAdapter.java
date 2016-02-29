package com.fyber.fybersdk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fybersdk.R;
import com.fyber.fybersdk.data.model.rest.Offer;
import com.fyber.fybersdk.data.model.rest.OfferType;
import com.squareup.picasso.Picasso;

import java.util.List;

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
     * it has been changed to normal findViewById pattern due to being converted to library module
     * because ids in R class are not constant in library modules.
     */
    static class ViewHolder {


        ImageView imgThumbnail;
        TextView txtTitle;
        LinearLayout lytOfferTypes;
        TextView txtRequiredActions;
        TextView txtReward;

        ViewHolder(View view) {
            imgThumbnail = (ImageView) view.findViewById(R.id.imgThumbnail);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            lytOfferTypes = (LinearLayout) view.findViewById(R.id.lytOfferTypes);
            txtRequiredActions = (TextView) view.findViewById(R.id.txtRequiredActions);
            txtReward = (TextView) view.findViewById(R.id.txtReward);
            ButterKnife.bind(this, view);

        }
    }
}
