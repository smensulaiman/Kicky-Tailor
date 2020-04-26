package com.ekattorit.kickytailor.activities.dashboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekattorit.kickytailor.R;
import com.ekattorit.kickytailor.adapters.NewReleaseProductAdapter;
import com.ekattorit.kickytailor.adapters.RecommendedProductAdapter;
import com.ekattorit.kickytailor.adapters.TopRatedProductAdapter;
import com.ekattorit.kickytailor.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.recyclerViewRecommended)
    RecyclerView recyclerViewRecommended;

    @BindView(R.id.recyclerViewTopRated)
    RecyclerView recyclerViewTopRated;

    private NewReleaseProductAdapter newReleaseProductAdapter;
    private RecommendedProductAdapter recommendedProductAdapter;
    private TopRatedProductAdapter topRatedProductAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        newReleaseProductAdapter = new NewReleaseProductAdapter(getContext(), getProductModel());
        recommendedProductAdapter = new RecommendedProductAdapter(getContext(),getProductModel());
        topRatedProductAdapter = new TopRatedProductAdapter(getContext(),getProductModel());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewRecommended.setLayoutManager(linearLayoutManager);
        recyclerViewTopRated.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(newReleaseProductAdapter);
        recyclerViewRecommended.setAdapter(recommendedProductAdapter);
        recyclerViewTopRated.setAdapter(topRatedProductAdapter);

        return view;
    }

    public List<ProductModel> getProductModel() {

        List<ProductModel> productModels = new ArrayList<ProductModel>();

        productModels.add(new ProductModel("1", "Product One", "Product Details", "120 taka", "Image", "Green", "available"));
        productModels.add(new ProductModel("2", "Product One", "Product Details", "120 taka", "Image", "Green", "available"));
        productModels.add(new ProductModel("3", "Product One", "Product Details", "120 taka", "Image", "Green", "available"));
        productModels.add(new ProductModel("4", "Product One", "Product Details", "120 taka", "Image", "Green", "available"));
        productModels.add(new ProductModel("5", "Product One", "Product Details", "120 taka", "Image", "Green", "available"));
        productModels.add(new ProductModel("6", "Product One", "Product Details", "120 taka", "Image", "Green", "available"));

        return productModels;

    }
}
