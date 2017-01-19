package com.example.admin.searchablerecyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    RelativeLayout RL_replace;
    Button BTN_checkout;

    private RecyclerView recyclerview;
    private ProductListAdapter pAdapter;
    private List<SellerProductPOJO> productList;

    Toolbar toolbar;
    private RecyclerView rv_productList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recycleAdapter;
    public static String var = "";

    String value = "";
    LinearLayout LL_cart_open_close;

    private MaterialSearchView searchView;
    TextView tvTotalAllPrice, textViewItemCount, textViewItem, textViewInyourCard, textViewTotal;
    String b_cat_ID;
    public ImageView ivBannerProduct;
    LinearLayout linearLayoutBanar;
    // String url = "http://www.blog.livebingonetwork.co.uk/wp-content/uploads/2014/06/Fathers-Day-600x222.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);


        //===============================
        linearLayoutBanar = (LinearLayout) findViewById(R.id.linearLayoutBanar);

        //============== Bussiness Category==============

        RL_replace = (RelativeLayout) findViewById(R.id.RL_replace);
        textViewItem = (TextView) findViewById(R.id.textViewItem);
        textViewInyourCard = (TextView) findViewById(R.id.textViewInyourCard);
        textViewTotal = (TextView) findViewById(R.id.textViewTotal);



        if (var.equals("")) {
            var = "filled";
        }
        BTN_checkout = (Button) findViewById(R.id.BTN_checkout);

        LL_cart_open_close = (LinearLayout) findViewById(R.id.LL_cart_open_close);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVisibility(View.GONE);

        rv_productList = (RecyclerView) findViewById(R.id.id_recycleview_productList);
        //   rv_productList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(ProductListActivity.this).size(2).build());
        rv_productList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        rv_productList.setLayoutManager(layoutManager);
        //Initializing our superheroes list
        productList = new ArrayList<>();



        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                    final List<SellerProductPOJO> filteredModelList = filter(productList, newText);

                    pAdapter.setFilter(filteredModelList);

                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                toolbar.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                toolbar.setVisibility(View.VISIBLE);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


            getProducts( b_cat_ID, "");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.product_list_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search_product);
        searchView.setMenuItem(item);
        //   searchView.setBackground(getDrawable(R.drawable.defaultimg));
        searchView.setHint("Search...");
        // searchView.setBackgroundColor(000000);
        searchView.setBackgroundColor(Color.WHITE);
        searchView.setHintTextColor(Color.GRAY);

        return true;
    }


    public void getProducts(String b_cat_ID, String ProductName) {

//        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading Data..", "Please wait...", false, false);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.1.116:90/GrowciaWebApi/api/")
                .build(); //Finally building the adapter
        //Creating object for our interface
        Api objGetProducts = adapter.create(Api.class);


        objGetProducts.getProducts(
                //Passing the values by getting it from editTexts
                "PUNE1234562",
                "919975732581",
                ProductName,
                //Creating an anonymous callback
                new Callback<String>() {
                    @Override
                    public void success(String result, Response response) {

                        try {
                            //===================================== Gson code by ravi============================
                            Log.e("product list data = ", result);
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<SellerProductPOJO>>() {
                            }.getType();

                            productList = (List<SellerProductPOJO>) gson.fromJson(result, listType);
                            if (productList.size() != 0) {
//                                loading.dismiss();
                                pAdapter = new ProductListAdapter(productList, MainActivity.this);
                                //Adding adapter to recyclerview
                                rv_productList.setAdapter(pAdapter);

                                //============@@@@ Displaying Add=======================
                                JSONArray jsonArray = new JSONArray(result);
                                JSONObject job = jsonArray.getJSONObject(0);
                                String pramotionImageUrl = job.getString("PromotionImage");

                                URL newurl = new URL(pramotionImageUrl);
                                Bitmap image = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                BitmapDrawable background = new BitmapDrawable(image);
                                linearLayoutBanar.setBackgroundDrawable(background);
                            }
                            //============@@@@ Displaying Add=======================

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this, "Please check Internet connection !", Toast.LENGTH_LONG).show();

                    }

                }
        );

    }
    private List<SellerProductPOJO> filter(List<SellerProductPOJO> models, String query) {
        query = query.toLowerCase();final List<SellerProductPOJO> filteredModelList = new ArrayList<>();
        for (SellerProductPOJO model : models) {
            final String text = model.getProductName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }




}
