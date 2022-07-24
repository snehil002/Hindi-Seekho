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

public class FamilyMembersActivity extends Fragment {

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
        words.add(new Word("MUMMY", "MAA", R.drawable.family_mother, R.raw.family_mummy_audio));
        words.add(new Word("PAPA", "PITA", R.drawable.family_father, R.raw.family_father_audio));
        words.add(new Word("ELDER SISTER", "DIDI", R.drawable.family_older_sister, R.raw.family_elder_sister_audio));
        words.add(new Word("ELDER BROTHER", "BHAIYYA", R.drawable.family_older_brother, R.raw.family_elder_brother_audio));
        words.add(new Word("YOUNGER SISTER", "BEHEN", R.drawable.family_younger_sister, R.raw.family_younger_sister_audio));
        words.add(new Word("YOUNGER BROTHER", "BHAI", R.drawable.family_younger_brother, R.raw.family_younger_brother_audio));
        words.add(new Word("MATERNAL GRANDMOTHER", "NANI", R.drawable.family_grandmother, R.raw.family_maternal_grand_mother_audio));
        words.add(new Word("MATERNAL GRANDFATHER", "NANA", R.drawable.family_grandfather, R.raw.family_maternal_grand_father_audio));
        words.add(new Word("PATERNAL GRANDMOTHER", "DADI", R.drawable.family_grandmother, R.raw.family_paternal_grand_mother_audio));
        words.add(new Word("PATERNAL GRANDFATHER", "DADA", R.drawable.family_grandfather, R.raw.family_paternal_grand_father_audio));
        words.add(new Word("DAUGHTER", "BETI", R.drawable.family_daughter, R.raw.family_daughter_audio));
        words.add(new Word("SON", "BETA", R.drawable.family_son, R.raw.family_son_audio));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.family_cat);

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