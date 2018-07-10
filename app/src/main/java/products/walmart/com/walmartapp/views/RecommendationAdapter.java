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

public class RecommendationAdapter extends   RecyclerView.Adapter<RecommendationAdapter.ViewHolder> {

        OnDetailViewListner onDetailViewListner;

        List<Product> recommendationList;

        public RecommendationAdapter( List<Product> recommendationList) {

        this.recommendationList = recommendationList;
        }

    interface OnDetailViewListner{

            void showDetail(int position);
    }
    void setOnDetailViewListner(OnDetailViewListner onDetailViewListner){

        this.onDetailViewListner=onDetailViewListner;

    }

    @Override
    public RecommendationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommendationlist, parent, false);
        RecommendationAdapter.ViewHolder viewHolder = new RecommendationAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecommendationAdapter.ViewHolder holder, int position) {

        Product p=recommendationList.get(position);
        holder.title.setText(p.getName());
        Picasso.get().load(p.getThumbnailImage()).into(holder.imageView);
        holder.price.setText("$"+String.valueOf(p.getSalePrice()));


    }

    @Override
    public int getItemCount() {
        return recommendationList.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.slider)
    ImageView imageView;
    @BindView(R.id.price)
    TextView price;



    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        title.setOnClickListener((view)->{

            onDetailViewListner.showDetail(getAdapterPosition());

        });
        imageView.setOnClickListener((view)->{
            onDetailViewListner.showDetail(getAdapterPosition());
        });

    }

}
}
