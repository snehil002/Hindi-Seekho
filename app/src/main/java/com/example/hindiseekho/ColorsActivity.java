package com.example.hindiseekho;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends Fragment {

    private MediaPlayer mMediaPlayer;

    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
                    {
                        mMediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }

                }
            };

    private AudioManager mAudioManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("DUSTY YELLOW", "PEELA", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow_audio));
        words.add(new Word("WHITE", "SHWET", R.drawable.color_white, R.raw.color_white_audio));
        words.add(new Word("GREEN", "HARA", R.drawable.color_green, R.raw.color_green_audio));
        words.add(new Word("BLACK", "KAALAA", R.drawable.color_black, R.raw. color_black_audio));
        words.add(new Word("RED", "LAAL", R.drawable.color_red, R.raw.color_red_audio));
        words.add(new Word("YELLOW", "PEELA", R.drawable.color_mustard_yellow, R.raw.color_yellow_audio));
        words.add(new Word("GRAY", "RAAKH", R.drawable.color_gray, R.raw.color_gray_audio));
        words.add(new Word("BROWN", "BHOORA", R.drawable.color_brown, R.raw.color_brown_audio));

//        words.add(new Word("PURPLE", "BAIGANY"));
//        words.add(new Word("BLUE", "NEELA"));
//        words.add(new Word("PINK", "GULAABI"));
//        words.add(new Word("ORANGE", "NARANGI"));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.colors_cat);

        ListView listView = (ListView) root.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                Word word = words.get(position);

                int requestForAudioFocus = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(requestForAudioFocus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}