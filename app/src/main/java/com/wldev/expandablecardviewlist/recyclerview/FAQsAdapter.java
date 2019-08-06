package com.wldev.expandablecardviewlist.recyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wldev.expandablecardviewlist.R;
import com.wldev.expandablecardviewlist.data.ExpandedStateItem;
import com.wldev.expandablecardviewlist.data.FAQModel;
import com.wldev.expandablecardviewlist.databinding.FaqItemBinding;
import com.wldev.expandablecardviewlist.extra.ClickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQsAdapter extends RecyclerView.Adapter<BindingViewHolder<FaqItemBinding>> {
    private Context context;
    private ArrayList<FAQModel> faqModelArrayList = new ArrayList<>();

    private static final float MAX_MARGIN = 16;
    private static final float MIN_MARGIN = 2;

    private ValueAnimator marginAnimator = ValueAnimator.ofFloat(MAX_MARGIN, MIN_MARGIN); // replace with dimens
    private final List<ExpandedStateItem> states = new ArrayList<>();
    private boolean isListExpanded;


    public FAQsAdapter(Context context, ArrayList<FAQModel> _faqModelArrayList) {
//        this.faqModelArrayList = _faqModelArrayList;
        this.context = context;
        marginAnimator.addUpdateListener(animation -> {
            for (ExpandedStateItem item : states) {
                item.setMargin((float) animation.getAnimatedValue());
            }
        });
        setData(_faqModelArrayList);
    }

//    @NonNull
//    @Override
//    public FAQsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
//        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
//        return new ViewHolder(rowView);
//    }

//    @Override
//    public void onBindViewHolder(@NonNull FAQsAdapter.ViewHolder holder, int position) {
//        holder.questionTV.setText(faqModelArrayList.get(position).getQuestion());
//        holder.answerTV.setText(faqModelArrayList.get(position).getAnswer());
//        holder.itemView.setOnClickListener(v -> {
////            if (holder.answerTV.getVisibility() == View.GONE) {
////                holder.answerTV.setVisibility(View.VISIBLE);
////                Extras.setRoboto(context, holder.questionTV, true);
////                holder.ivArrrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
////            } else {
////                holder.answerTV.setVisibility(View.GONE);
////                Extras.setRoboto(context, holder.questionTV, false);
////                holder.ivArrrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
////            }
//            ObjectAnimator animator = ObjectAnimator.ofFloat(holder.ivArrrow, "rotation", rotationAngle, rotationAngle + 180);
//            animator.setDuration(500);
//            animator.start();
//            rotationAngle += 180;
//            rotationAngle = rotationAngle % 360;
//            if (rotationAngle == 180) {
////                holder.answerTV.setVisibility(View.VISIBLE);
//                expand(holder.answerTV);
//                Extras.setRoboto(context, holder.questionTV, true);
//            } else {
////                holder.answerTV.setVisibility(View.GONE);
//                collapse(holder.answerTV);
//                Extras.setRoboto(context, holder.questionTV, false);
//            }
//
//
//        });
//    }


    @NonNull
    @Override
    public BindingViewHolder<FaqItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingViewHolder<>(R.layout.faq_item, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<FaqItemBinding> holder, int position) {
        holder.getBinding().setItem(faqModelArrayList.get(holder.getAdapterPosition()));
        holder.getBinding().executePendingBindings();  // We need to set our item here because view will need to know how to measure itself (which data to use, how many text lines and so on)

        states.get(holder.getAdapterPosition()).setFast(true);
        holder.getBinding().setState(states.get(holder.getAdapterPosition()));

        holder.getBinding().setIsLast(holder.getAdapterPosition() == faqModelArrayList.size() - 1);
        holder.getBinding().setIsFirst(holder.getAdapterPosition() == 0);

        holder.getBinding().setOnClick(new ClickAdapter() {
            @Override
            public void onClick(View v) {
                states.get(holder.getAdapterPosition()).setFast(false);
                states.get(holder.getAdapterPosition()).setExpanded(!states.get(holder.getAdapterPosition()).isExpanded());
                invalidateExpandedState();
            }
        });

        holder.getBinding().executePendingBindings();

    }

    private void invalidateExpandedState() {
        boolean isAnyExpanded = false;
        for (ExpandedStateItem item :
                states) {
            isAnyExpanded = isAnyExpanded || item.isExpanded();
        }

        if (isListExpanded != isAnyExpanded) {
            if (!isAnyExpanded) {
                isListExpanded = false;
                marginAnimator.start();
            } else {
                isListExpanded = true;
                marginAnimator.reverse();
            }
        }
    }

    @Override
    public int getItemCount() {
        return faqModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.faq_question_tv)
        TextView questionTV;
        @BindView(R.id.faq_answers_tv)
        TextView answerTV;
        @BindView(R.id.arrow_faq_iv)
        ImageView ivArrrow;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setData(List<FAQModel> newData) {
        this.faqModelArrayList.clear();
        this.states.clear();

        for (FAQModel i :
                newData) {
            this.faqModelArrayList.add(i);
            this.states.add(new ExpandedStateItem(false)); // You can actually save expanded states here
        }
    }
}
