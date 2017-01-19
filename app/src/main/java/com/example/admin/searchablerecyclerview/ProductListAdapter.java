package com.example.admin.searchablerecyclerview;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07/04/2016.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements View.OnClickListener {

    public Context context;
    String productCode;
    boolean isPressed = false;
    int count = 0;

    public String pname, price, img_path;
    static int productItem = 0;
    double totPrice;

    MainActivity objProductList;
    List<SellerProductPOJO> productListDetails;

    public ProductListAdapter(List<SellerProductPOJO> productDetails, Context context) {
        super();

        objProductList = new MainActivity();
        this.productListDetails = productDetails;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final SellerProductPOJO objSellerProductPOJO = productListDetails.get(position);

        if (!objSellerProductPOJO.getDiscountAmount().equals("0.0")) {
            holder.tv_offerText.setText("Rs " + objSellerProductPOJO.getDiscountAmount().toString() + " OFF");
        } else if (!objSellerProductPOJO.getDiscountPercentage().equals("0.0")) {
            holder.tv_offerText.setText(objSellerProductPOJO.getDiscountPercentage().toString() + "% OFF");
            Log.e("******" + position, objSellerProductPOJO.getDiscountPercentage() + "");
        } else if (objSellerProductPOJO.getDiscountType().length() > 5) {
            holder.tv_offerText.setText(objSellerProductPOJO.getDiscountType().toString() + "");
        } else {
            holder.tv_offerText.setText("");
        }

        holder.tv_unit.setText(objSellerProductPOJO.getUnit());

        String url = objSellerProductPOJO.getImagePath();
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.error)   // optional
                .resize(100, 100)                       // optional
                .into(holder.ivProduct);


//        holder.ivProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        holder.tvProductName.setText(objSellerProductPOJO.getProductName());
//        holder.tvUnit.setText(objSellerProductPOJO.getAvailableQuantity());
        holder.tvPrice.setText("Rs " + objSellerProductPOJO.getPrice());


        if (objSellerProductPOJO.getIsFav().toString().equalsIgnoreCase("Y")) {
            holder.imagefavorite.setBackgroundResource(R.drawable.favourite_icon);

        } else {
            holder.imagefavorite.setBackgroundResource(R.drawable.ic_not_favourite);
        }

        holder.tvQty.setText(objSellerProductPOJO.getQty() + "");

//if () {
        if (!objSellerProductPOJO.getDiscountedPrice().toString().equals("0.00") && !objSellerProductPOJO.getDiscountedPrice().equals("null")) {
            holder.tvPrice.setPaintFlags(holder.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvPrice.setText("Rs " + objSellerProductPOJO.getPrice().toString());
            holder.tv_discountedPrice.setText("Rs " + objSellerProductPOJO.getDiscountedPrice().toString());
        } else {
            holder.tvPrice.setText("");
            holder.tv_discountedPrice.setText("Rs " + objSellerProductPOJO.getPrice().toString());
        }


        holder.imagefavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("position", "all position" + position);
                if (isPressed) {
                    objSellerProductPOJO.setIsFav("N");

                    holder.imagefavorite.setBackgroundResource(R.drawable.ic_not_favourite);

                } else {
                    objSellerProductPOJO.setIsFav("Y");

                    holder.imagefavorite.setBackgroundResource(R.drawable.favourite_icon);
                }

                isPressed = !isPressed;
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return productListDetails.size();
    }

    @Override
    public void onClick(View v) {

    }


    public void setFilter(List<SellerProductPOJO> countryModels) {
        productListDetails = new ArrayList<>();
        productListDetails.addAll(countryModels);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvProductName;
        public TextView tv_offerText;
        //        public TextView tvUnit;
        public TextView tv_unit;
        public TextView tv_discountedPrice;
        public ImageView img_CurruncyIcon;


        public TextView tvPrice;
        public TextView tvQty;
        public ImageView ivProduct;
        public ImageView imagefavorite;
        //  public EditText edqntiry;
        public Button btnPlus;
        public Button btnMinus;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_offerText = (TextView) itemView.findViewById(R.id.tv_offerText);
            tv_discountedPrice = (TextView) itemView.findViewById(R.id.tv_discountedPrice);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
//            img_CurruncyIcon = (ImageView) itemView.findViewById(R.id.img_CurruncyIcon);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
//            tvUnit = (TextView) itemView.findViewById(R.id.tvUnit);
            tv_unit = (TextView) itemView.findViewById(R.id.tv_unit);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvQty = (TextView) itemView.findViewById(R.id.tvQty);
            btnPlus = (Button) itemView.findViewById(R.id.btnPlus);
            btnMinus = (Button) itemView.findViewById(R.id.btnMinus);
            imagefavorite = (ImageView) itemView.findViewById(R.id.imagefavorite);
        }


    }


}
