package products.walmart.com.walmartapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import products.walmart.com.walmartapp.R;
import products.walmart.com.walmartapp.model.Product;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    OnTouchEventListner onTouchEventListner;

    List<Product> photoList;

    public ProductAdapter() {}

    void setProducts(List<Product> photoList) {
        this.photoList = photoList;
    }

    interface OnTouchEventListner{

        void onTouchEvent(int position);

    }

    void setOnTouchEventListner(OnTouchEventListner onTouchEventListner){

        this.onTouchEventListner=onTouchEventListner;

    }

    OnTouchEventListner returnOnTouchEventListner(){
        return onTouchEventListner;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlist, parent, false);
        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {

        Product p=photoList.get(position);
        holder.title.setText(p.getName());
        Picasso.get().load(p.getThumbnailImage()).into(holder.imageView);
        holder.price.setText("$"+String.valueOf(p.getSalePrice()));
        holder.description.setText(p.getShortDescription());


    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.slider)
        ImageView imageView;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.description)
        TextView description;




        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            title.setOnClickListener((view)-> {

                onTouchEventListner.onTouchEvent(getAdapterPosition());

            });
            imageView.setOnClickListener((view)->{

                onTouchEventListner.onTouchEvent(getAdapterPosition());
            });

        }

    }
}
