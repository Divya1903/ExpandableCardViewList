package com.wldev.expandablecardviewlist.faqs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wldev.expandablecardviewlist.R;
import com.wldev.expandablecardviewlist.data.FAQModel;
import com.wldev.expandablecardviewlist.databinding.ActivityFaqBinding;
import com.wldev.expandablecardviewlist.recyclerview.FAQsAdapter;

import java.util.ArrayList;


public class FAQActivity extends AppCompatActivity {

    ActivityFaqBinding binding;
//    @BindView(R.id.rv_faq_test)
//    RecyclerView rvFaqTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_faq);
//        ButterKnife.bind(this);


        ArrayList<FAQModel> faqModelArrayList = new ArrayList<>();
        String[] questionsArray = getResources().getStringArray(R.array.questions_array);
        String[] answersArray = getResources().getStringArray(R.array.answers_array);
        for (int i = 0; i < questionsArray.length; i++) {
            FAQModel fm = new FAQModel(questionsArray[i], answersArray[i]);
            faqModelArrayList.add(fm);
        }
        FAQsAdapter faQsAdapter = new FAQsAdapter(this, faqModelArrayList);
        binding.rvFaqTest.setLayoutManager(new LinearLayoutManager(this));
        binding.rvFaqTest.setAdapter(faQsAdapter);
    }
}
