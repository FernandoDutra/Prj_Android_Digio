package br.com.fernandodutra.prj_android_digio.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.fernandodutra.prj_android_digio.R;
import br.com.fernandodutra.prj_android_digio.model.Spotlight;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 16/05/2019
 * Time: 21:45
 * Prj_Android_Digio
 */
public class SpotlightAdapter extends PagerAdapter {

    private List<Spotlight> spotlights;
    private LayoutInflater layoutInflater;
    private Context context;

    private ImageView iv_bannerURL;

    public SpotlightAdapter(List<Spotlight> spotlights, Context context) {
        this.spotlights = spotlights;
        this.context = context;
    }

    private void initiateVariables(View view) {
        iv_bannerURL = view.findViewById(R.id.spotlight_activity_image);
    }

    @Override
    public int getCount() {
        return spotlights.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spotlight_activity, container, false);

        initiateVariables(view);

        Picasso.with(context).load(spotlights.get(position).getBannerURL()).fit().into(iv_bannerURL);

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
