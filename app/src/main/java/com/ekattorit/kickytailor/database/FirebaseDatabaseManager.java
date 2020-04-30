package com.ekattorit.kickytailor.database;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ekattorit.kickytailor.models.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseManager {

    private Context context;
    private DatabaseReference allProductsReference = FirebaseDatabase.getInstance().getReference(FirebaseNodeConstants.ALL_PRODUCTS);
    private FirebaseDatabaseManagerInterface firebaseDatabaseManagerInterface;

    public FirebaseDatabaseManager(Context context){
        this.context = context;
    }

    public void setFirebaseDatabaseManagerInterface(FirebaseDatabaseManagerInterface firebaseDatabaseManagerInterface) {
        this.firebaseDatabaseManagerInterface = firebaseDatabaseManagerInterface;
    }

    public void getAllProducts (FirebaseDatabaseManagerInterface firebaseDatabaseManagerInterface){
        List<ProductModel> productModels = new ArrayList<>();
        allProductsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductModel productModel = snapshot.getValue(ProductModel.class);
                    productModels.add(productModel);
                }

                if(firebaseDatabaseManagerInterface != null){
                    firebaseDatabaseManagerInterface.getAllProducts(productModels);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
