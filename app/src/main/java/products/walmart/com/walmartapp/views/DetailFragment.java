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

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
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


public class DetailFragment extends Fragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.price)
            TextView price;
    @BindView(R.id.customerRating)
            TextView customerRating;
    @BindView(R.id.stock)
            TextView stock;
    Unbinder unbinder;
    ItemViewModel itemViewModel;

    static Product product;
    int productid=0;


    public DetailFragment() {
        // Required empty public constructor
    }

     public void bindButterKnife(View vi) {
        unbinder = ButterKnife.bind(this, vi);
    }


    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(int productid) {
        Bundle args = new Bundle();
        args.putInt("productid",productid);
        DetailFragment fragment = new DetailFragment();
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

        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        bindButterKnife(view);


        itemViewModel = ViewModelProviders.of(this.getActivity()).get(ItemViewModel.class);
        itemViewModel.getProduct().observe(this,(item)->{

            product=item;
            title.setText(product.getName());
            String desc=product.getShortDescription();
            if(desc!=null)
             desc=Html.fromHtml(desc).toString();
            description.setText(desc);
            Picasso.get().load(product.getMediumImage()).into(imageView);
            price.setText("$"+String.valueOf(product.getSalePrice()));
            customerRating.setText(String.valueOf(product.getCustomerRating()));
            stock.setText(product.getStock());

            getFragmentManager().beginTransaction().replace(R.id.recommendation_frame,RecommendationFragment
                    .newInstance(String.valueOf(productid))).commit();


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
