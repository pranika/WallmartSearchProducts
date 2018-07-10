package products.walmart.com.walmartapp.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import products.walmart.com.walmartapp.R;
import products.walmart.com.walmartapp.WalmartApplication;
import products.walmart.com.walmartapp.model.ImageEntity;
import products.walmart.com.walmartapp.model.Product;
import products.walmart.com.walmartapp.viewModel.ItemViewModel;


public class DetailFragmentRecommended extends Fragment {
    int productid;
    Unbinder unbinder;
    ItemViewModel itemViewModel;
    Product product;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.stock)
    TextView stock;
    @BindView(R.id.customerRating)
    TextView customerRating;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.slider)
    ImageView imageView;

    public DetailFragmentRecommended() {
        // Required empty public constructor
    }
    public void bindButterKnife(View vi) {
        unbinder = ButterKnife.bind(this, vi);
    }

    public static DetailFragmentRecommended newInstance(int productid) {
        Bundle args = new Bundle();
        args.putInt("productid",productid);
        DetailFragmentRecommended fragment = new DetailFragmentRecommended();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
        {
            productid =(Integer) getArguments().get("productid");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail_fragment_recommended, container, false);
        bindButterKnife(view);


        itemViewModel = ViewModelProviders.of(this.getActivity()).get(ItemViewModel.class);
        itemViewModel.getProduct().observe(this,(item)->{

            product=item;
            title.setText(product.getName());
            String desc=product.getShortDescription();
            if(desc!=null)
            desc= Html.fromHtml(desc).toString();
            description.setText(desc);

            Picasso.get().load(product.getMediumImage()).into(imageView);
            price.setText(String.valueOf(product.getSalePrice()));
            customerRating.setText(String.valueOf(product.getCustomerRating()));
            stock.setText(product.getStock());


        });
        itemViewModel.returnProduct(productid);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
