package com.ekattorit.kickytailor.activities.dashboard;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ekattorit.kickytailor.R;
import com.ekattorit.kickytailor.activities.dashboard.fragments.HomeFragment;
import com.ekattorit.kickytailor.activities.dashboard.fragments.ProfileFragment;
import com.ekattorit.kickytailor.adapters.ImageSliderAdapter;
import com.ekattorit.kickytailor.adapters.ImageSliderAdapterListener;
import com.ekattorit.kickytailor.utils.Tools;
import com.ekattorit.kickytailor.utils.ViewAnimation;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MainDashboardActivity extends AppCompatActivity {

    @BindView(R.id.viewpagerSlider)
    ViewPager viewpagerSlider;
    @BindView(R.id.dotSlider)
    CircleIndicator dotSlider;
    @BindView(R.id.layoutSlider)
    LinearLayout layoutSlider;

    private TabLayout tab_layout;
    private ActionBar actionBar;
    private NestedScrollView nested_scroll_view;
    private ImageSliderAdapter imageSliderAdapter;
    private Fragment fragment;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.background, R.drawable.img_plant_1, R.drawable.img_plant_2, R.drawable.img_plant_3};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        fragment = new HomeFragment();
        changeFragment(fragment, new Bundle());
        initToolbar();
        initComponent();
        initSlider();
    }

    private void initSlider() {
        for (int i = 0; i < IMAGES.length; i++) {
            ImagesArray.add(IMAGES[i]);
        }

        NUM_PAGES = IMAGES.length;

        imageSliderAdapter = new ImageSliderAdapter(MainDashboardActivity.this, ImagesArray);
        viewpagerSlider.setAdapter(imageSliderAdapter);
        imageSliderAdapter.setImageSliderAdapterListener(new ImageSliderAdapterListener() {
            @Override
            public void changeImage() {
                if (currentPage > NUM_PAGES) {
                    currentPage = 0;
                }
                viewpagerSlider.setCurrentItem(currentPage++, true);
            }
        });

        dotSlider.setViewPager(viewpagerSlider);
        dotSlider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_20);
    }

    private void initComponent() {
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_home), 0);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_search), 1);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_add_box), 2);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_favorite_border), 3);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_person), 4);

        // set icon color pre-selected
        tab_layout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.deep_orange_500), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(4).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.deep_orange_500), PorterDuff.Mode.SRC_IN);
                switch (tab.getPosition()) {
                    case 0:
                        actionBar.setTitle("Home");
                        layoutSlider.setVisibility(View.VISIBLE);
                        fragment = new HomeFragment();
                        changeFragment(fragment, new Bundle());
                        break;
                    case 1:
                        actionBar.setTitle("Explore");
                        layoutSlider.setVisibility(View.GONE);
                        fragment = new ProfileFragment();
                        changeFragment(fragment, new Bundle());
                        break;
                    case 2:
                        actionBar.setTitle("Story");
                        layoutSlider.setVisibility(View.GONE);
                        fragment = new HomeFragment();
                        changeFragment(fragment, new Bundle());
                        break;
                    case 3:
                        actionBar.setTitle("Activity");
                        layoutSlider.setVisibility(View.GONE);
                        fragment = new ProfileFragment();
                        changeFragment(fragment, new Bundle());
                        break;
                    case 4:
                        actionBar.setTitle("Profile");
                        layoutSlider.setVisibility(View.GONE);
                        fragment = new HomeFragment();
                        changeFragment(fragment, new Bundle());
                        break;
                }

                ViewAnimation.fadeOutIn(nested_scroll_view);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    public void changeFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(MainDashboardActivity.this, ShoppingCartActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
