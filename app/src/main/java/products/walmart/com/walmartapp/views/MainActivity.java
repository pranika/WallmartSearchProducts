package products.walmart.com.walmartapp.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import products.walmart.com.walmartapp.R;
import products.walmart.com.walmartapp.model.Product;
import products.walmart.com.walmartapp.viewModel.ItemViewModel;
import products.walmart.com.walmartapp.views.ProductFragment;

public class MainActivity extends AppCompatActivity implements ProductFragment.OnClicklistner,RecommendationFragment.OnClicklistnerDetail {


    ItemViewModel itemViewModel;

    List<Product> productlist;

    int id=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        productlist=new ArrayList<>();
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.getAllItems().observe(this, (products) -> {

            productlist=products;
            Log.d("Test", products.toString());



        });



        ProductFragment productFragment=new ProductFragment();
        setFragment(productFragment);



    }


    public void setFragment(ProductFragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame,fragment).addToBackStack("frame").commit();
    }



    @Override
    public void showActivity(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();


        Product product=productlist.get(position);
        int id=product.getItemId();

        fragmentManager.beginTransaction().replace(R.id.frame,DetailFragment.newInstance(id)).addToBackStack(null).commit();

    }


    @Override
    public void showActivityDetail(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();


        itemViewModel.getRecommendations().observe(this,(recommendations)->{
            Product product=recommendations.get(position);
            id=product.getItemId();

        });
        fragmentManager.beginTransaction().
                replace(R.id.frame,DetailFragmentRecommended.newInstance(id)).addToBackStack(null).commit();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
