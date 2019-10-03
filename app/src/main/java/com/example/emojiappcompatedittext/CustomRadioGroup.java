package com.example.emojiappcompatedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CustomRadioGroup extends RadioGroup implements RadioGroup.OnCheckedChangeListener {

    private OnAnswerListener mAnswerListener;

    public CustomRadioGroup(Context context) {
        super(context);
        setOnCheckedChangeListener(this);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnCheckedChangeListener(this);
    }

    /**
     * Loads RadioButtons from the list of Smileys
     *
     * @param smileys list of {@see Smiley}
     */
//    public void loadAnswers(List<Smiley> smileys) {
//        if (smileys == null) {
//            return;
//        }
//        removeAllViews();
//        LayoutInflater inflater = LayoutInflater.from(getContext());

    public void loadAnswers()

    {
        for (int i = 0; i < 5; i++) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            RadioButton button = (RadioButton) inflater.inflate(R.layout.answer_item, this, false);
            button.setText("Button " + i);
//            button.setTag(R.string.answer_tag, smiley.getCode());
            addView(button);
        }

    }
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton button = group.findViewById(checkedId);
        mAnswerListener.onAnswerSelected((String) button.getTag(R.string.answer_tag));
    }

    /**
     * Set enabled state of child views and parent view
     *
     * @param enabled True if child views are enabled?
     */
    @Override
    public void setEnabled(boolean enabled) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(enabled);
        }
        super.setEnabled(enabled);
    }

    public interface OnAnswerListener {
        void onAnswerSelected(String answer);
    }

    public void setOnAnswerListener(OnAnswerListener listener) {
        mAnswerListener = listener;
    }

}
