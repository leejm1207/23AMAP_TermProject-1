package com.example.termproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.termproject.BookMark.BookmarkFragment;
import com.example.termproject.Home.HomeFragment;
import com.example.termproject.Category.CategoryFragment;
import com.example.termproject.MyPage.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    final int HOMEFILTER_REQUEST = 347;
    BottomNavigationView navbar;

    HomeFragment home_frag = new HomeFragment();
    BookmarkFragment bookmark_frag = new BookmarkFragment();
    CategoryFragment category_frag = new CategoryFragment();
    MyPageFragment mypage_frag = new MyPageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // 다크모드 해제
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        navbar = (BottomNavigationView) findViewById(R.id.activity_main_navbar);

//        TODO : 유저의 로그인 확인하고, 로그아웃이면 로그아웃 액티비티 실행
//        ...
//        ...
//        ...



        changeFragment(R.id.main_fragment, home_frag);
        navbar.setOnItemSelectedListener(item -> {
            FragmentManager fm = getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); i++)
                fm.popBackStack();

            // TODO: 각각의 프래그먼트 만들어서 이어주기
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                changeFragment(R.id.main_fragment, home_frag);
                return true;
            } else if (itemId == R.id.bookmark) {
                changeFragment(R.id.main_fragment, bookmark_frag);
                return true;
            } else if (itemId == R.id.category) {
                changeFragment(R.id.main_fragment, category_frag);
                return true;
            } else if (itemId == R.id.myPage) {
                changeFragment(R.id.main_fragment, mypage_frag);
                return true;
            }
            return false;
        });
    }

    /** address주소의 프래그먼트 슬롯을 frag라는 프래그먼트로 교체
     *  @param address 프래그먼트 슬롯의 주소
     *  @param frag 변경할 프래그먼트   */
    private void changeFragment(int address, Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(address, frag);
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == HOMEFILTER_REQUEST) {
            if (resultCode == RESULT_OK) home_frag.loadHomeFeed();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}