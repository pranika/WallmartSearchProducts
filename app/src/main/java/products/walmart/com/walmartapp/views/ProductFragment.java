package products.walmart.com.walmartapp.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import products.walmart.com.walmartapp.R;
import products.walmart.com.walmartapp.WalmartApplication;
import products.walmart.com.walmartapp.disposeLeaks.DisposableManager;
import products.walmart.com.walmartapp.model.Product;
//import products.walmart.com.walmartapp.repolist.ViewInterface;
import products.walmart.com.walmartapp.viewModel.ItemViewModel;


public class ProductFragment extends Fragment {



    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.searchbutton)
    ImageButton searchbutton;
    @BindView(R.id.search)
    EditText search;

    Unbinder unbinder;

    ItemViewModel itemViewModel;
    private RecyclerView.LayoutManager mLayoutManager;
    ProductAdapter productAdapter;
    OnClicklistner onClicklistner;
    Observer productObserver;

    public void bindButterKnife(View vi) {
        unbinder = ButterKnife.bind(this, vi);
    }

    public ProductFragment() {
        // Required empty public constructor
    }



    public interface OnClicklistner {
        void showActivity(int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WalmartApplication) getActivity().getApplication()).mDaggerComponent.inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_product, container, false);
        onClicklistner = (OnClicklistner) v.getContext();
        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        bindButterKnife(v);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        productAdapter = new ProductAdapter();

        productObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                configureProducts(products);
                Log.d("Test", products.toString());
            }
        };


        itemViewModel.getAllItems().observe(this, productObserver);

        productAdapter.setOnTouchEventListner((position)-> {
            onClicklistner.showActivity(position);
        });

        searchbutton.setOnClickListener((view)->{
            if(search.getText()!=null){

                itemViewModel.loadProducts(search.getText().toString());
            }

        });


        return v;
    }

    private void configureProducts(List<Product> products) {
        productAdapter.setProducts(products);
        recyclerView.setAdapter(productAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ProductAdapter.OnTouchEventListner onTouchEventListner=productAdapter.returnOnTouchEventListner();
        onTouchEventListner=null;

        productAdapter.setOnTouchEventListner(null);

        itemViewModel.getAllItems().removeObserver(productObserver);

        DisposableManager.dispose();


    }


}
