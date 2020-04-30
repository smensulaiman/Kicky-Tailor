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
import com.ekattorit.kickytailor.database.FirebaseDatabaseManager;
import com.ekattorit.kickytailor.database.FirebaseDatabaseManagerInterface;
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

        newReleaseProductAdapter = new NewReleaseProductAdapter(getContext(), new ArrayList<ProductModel>());
        recommendedProductAdapter = new RecommendedProductAdapter(getContext(),new ArrayList<ProductModel>());
        topRatedProductAdapter = new TopRatedProductAdapter(getContext(),new ArrayList<ProductModel>());

        recyclerView.setLayoutManager(getLayoutManager());
        recyclerViewRecommended.setLayoutManager(getLayoutManager());
        recyclerViewTopRated.setLayoutManager(getLayoutManager());

        recyclerView.setAdapter(newReleaseProductAdapter);
        recyclerViewRecommended.setAdapter(recommendedProductAdapter);
        recyclerViewTopRated.setAdapter(topRatedProductAdapter);

        getProductModel();

        return view;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        return linearLayoutManager;
    }

    public void getProductModel() {
        new FirebaseDatabaseManager(getContext()).getAllProducts(new FirebaseDatabaseManagerInterface() {
            @Override
            public void getAllProducts(List<ProductModel> allProducts) {
                newReleaseProductAdapter.setProductModels(allProducts);
                recommendedProductAdapter.setProductModels(allProducts);
                topRatedProductAdapter.setProductModels(allProducts);
            }
        });

    }
}
