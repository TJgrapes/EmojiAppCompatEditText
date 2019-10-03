package com.example.emojiappcompatedittext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiEditText;
import androidx.emoji.widget.EmojiTextView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    TextView textView;

    EditText editTextView;

    EmojiEditText emojiEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);

        setContentView(R.layout.activity_main);
//

//
//        EmojiTextView emojiTextView = findViewById(R.id.emoji);
//
////        emojiTextView.setText(new String(Character.toChars(0x1F60A)));
//
////        emojiTextView.setText("U+1F970");
//
//        CharSequence processedSequence = EmojiCompat.get().process("U+1F970");
//
//        emojiTextView.setText(processedSequence);

        textView = findViewById(R.id.textView);

        //U+1F618 0x1F618

//        textView.setText(new String(Character.toChars(0x1F618)));

        String textViewEmojiString = textView.getText().toString();

        String myString = "" + Long.toHexString(textViewEmojiString.codePointAt(0)).toUpperCase();

        Toast.makeText(this, myString, Toast.LENGTH_SHORT).show();

        EmojiTextView emojiTextView = findViewById(R.id.emojiTextView);

        emojiTextView.setText(new String(Character.toChars(0x1F60B)));

        emojiTextView.setText(new String(Character.toChars(0x1F92A)));

//        Toast.makeText(this, emojiTextView.getText(), Toast.LENGTH_SHORT).show();


        /**
         *
         * Implementing a custom radio button class
         *
         */
        CustomRadioGroup mAnswersView;

        mAnswersView = findViewById(R.id.answersView);

        mAnswersView.loadAnswers();

        editTextView = findViewById(R.id.editText);

//        editTextView.addTextChangedListener(this);

        emojiEditText = findViewById(R.id.emojiEditTextView);

        emojiEditText.addTextChangedListener(this);
//


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//        textView.setText(charSequence);

    }

    @Override
    public void afterTextChanged(Editable editable) {

        // Get the codepoint of the emoji entered in the EmojiEditText
        String text = editable.toString();

        // Convert the codepoint to its unicode value using the getStringsHex() method
        String unicode = getStringsHex(text);

        Toast.makeText(getApplicationContext(), unicode, Toast.LENGTH_SHORT).show();

        // Set the unicode text view to the appropriate unicode
        textView.setText(unicode);

    }

    private String getStringsHex(String text) {
        if (text == null || text.isEmpty()) {
            return "U+";
        }
        return "U+" + Long.toHexString(text.codePointAt(0)).toUpperCase();
    }

}

