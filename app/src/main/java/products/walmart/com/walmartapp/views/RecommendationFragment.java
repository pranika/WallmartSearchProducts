package products.walmart.com.walmartapp.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import products.walmart.com.walmartapp.R;
import products.walmart.com.walmartapp.model.Product;
import products.walmart.com.walmartapp.viewModel.ItemViewModel;


public class RecommendationFragment extends Fragment {

    ItemViewModel itemViewModel;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.recommended_products)
    TextView recommended_products;
    String productid;

     OnClicklistnerDetail onClicklistnerDetail;

    private RecyclerView.LayoutManager mLayoutManager;
    RecommendationAdapter recommendationAdapter;

    Unbinder unbinder;

    public RecommendationFragment() {
        // Required empty public constructor
    }
    public void bindButterKnife(View vi) {
        unbinder = ButterKnife.bind(this, vi);
    }

    public interface OnClicklistnerDetail {
        void showActivityDetail(int position);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
        {
            productid =(String) getArguments().get("productid");

        }


    }

    public static RecommendationFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("productid",id);

        RecommendationFragment fragment = new RecommendationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_recommendation, container, false);
        onClicklistnerDetail = (OnClicklistnerDetail) v.getContext();
        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        bindButterKnife(v);

        itemViewModel.getRecommendations().observe(this, (products) -> {

            if(products.size()==0)
                recommended_products.setVisibility(View.INVISIBLE);


            else {
                configureProducts(products);
                Log.d("Test", products.toString());
                recommendationAdapter.setOnDetailViewListner((position) -> {

                    onClicklistnerDetail.showActivityDetail(position);
                });
            }

        });
      itemViewModel.loadRecommendations(productid);

//        itemViewModel.loadRecommendations("36904791");
        return v;
    }

    private void configureProducts(List<Product> products) {

        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recommendationAdapter= new RecommendationAdapter(products);
        recyclerView.setAdapter(recommendationAdapter);
    }


}
