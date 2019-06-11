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
import br.com.fernandodutra.prj_android_digio.model.Products;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/05/2019
 * Time: 00:18
 * Prj_Android_Digio
 */
public class ProductsAdapter extends PagerAdapter {

    private List<Products> cash;
    private LayoutInflater layoutInflater;
    private Context context;

    private ImageView iv_ImageURL;

    public ProductsAdapter(List<Products> cash, Context context) {
        this.cash = cash;
        this.context = context;
    }

    private void initiateVariables(View view) {
        iv_ImageURL = view.findViewById(R.id.products_activity_image);
    }

    @Override
    public int getCount() {
        return cash.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.products_activity, container, false);

        initiateVariables(view);

        Picasso.with(context).load(cash.get(position).getImageURL()).fit().into(iv_ImageURL);

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
