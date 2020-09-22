package com.anime.kyatchifurzu;

import android.media.Image;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioListViewHolder> implements  Handler.Callback{

    private static final String TAG = "***AudioListAdapter****";

//    private final ClickListener clickListener;
    private MediaPlayer mMediaPlayer;
    private Handler uiUpdateHandler;
    private List<AudioFileEntity> mAudioFile;
    private int playingPosition = -1;
    private AudioListViewHolder viewHolder;

    public AudioListAdapter(List<AudioFileEntity> audioFile/*, ClickListener clickListener*/) {
        mAudioFile = audioFile;
        uiUpdateHandler = new Handler(this);
//        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AudioListAdapter.AudioListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_list, parent, false);
        return new AudioListViewHolder(view/*, clickListener*/);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioListAdapter.AudioListViewHolder holder, int position) {
        String names[] = mAudioFile.get(position).getName().split(":");
        holder.getTextViewAudioName().setText(names[0]);
        holder.getTextViewAnimeName().setText(names[1]);
        if(playingPosition == position) {
            viewHolder = holder;
            updatePlayingView();
        } else {
            updateNonPlayingView(holder);
        }
    }

    @Override
    public int getItemCount() {
        return mAudioFile.size();
    }

    @Override
    public void onViewRecycled(@NonNull AudioListViewHolder holder) {
        super.onViewRecycled(holder);
        if(playingPosition == holder.getAdapterPosition()) {
            updateNonPlayingView(viewHolder);
            viewHolder = null;
        }
    }


    private void updatePlayingView() {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            viewHolder.playImageView.setImageResource(R.drawable.ic_action_stop);
        } else {
            viewHolder.playImageView.setImageResource(R.drawable.ic_action_play);
        }
    }

    private void updateNonPlayingView(AudioListViewHolder holder) {
        holder.playImageView.setImageResource(R.drawable.ic_action_play);
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        return false;
    }

    void stopPlayer() {
        if (mMediaPlayer != null) {
            releaseMediaPlayer();
        }
    }

    private void releaseMediaPlayer() {
        if(viewHolder != null) {
            updateNonPlayingView(viewHolder);
        }
        mMediaPlayer.release();
        mMediaPlayer = null;
        playingPosition = -1;
    }


    public class AudioListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView textViewAudioName;
        private TextView textViewAnimeName;
        private ImageView playImageView;
        private ImageView moreOptionImageView;
//        private WeakReference<ClickListener> listenerWeakReference;

        public AudioListViewHolder(@NonNull View itemView/*, ClickListener clickListener*/) {
            super(itemView);

//            listenerWeakReference = new WeakReference<>(clickListener);
            textViewAudioName = itemView.findViewById(R.id.text_audio);
            textViewAnimeName = itemView.findViewById(R.id.text_anime);
            playImageView = itemView.findViewById(R.id.button_play);
            moreOptionImageView = itemView.findViewById(R.id.button_options);

            itemView.setOnClickListener(this);
            playImageView.setOnClickListener(this);
            moreOptionImageView.setOnClickListener(this);
        }

        public TextView getTextViewAudioName() {
            return textViewAudioName;
        }

        public TextView getTextViewAnimeName() {
            return textViewAnimeName;
        }

        @Override
        public void onClick(View v) {

            if(v.getId() == playImageView.getId()) {

                if(playingPosition == getAdapterPosition()){
                    if(mMediaPlayer.isPlaying()) {
                        mMediaPlayer.stop();
                        releaseMediaPlayer();
                    } else {
                        mMediaPlayer.start();
                    }
                } else {
                    playingPosition = getAdapterPosition();
                    if(mMediaPlayer != null) {
                        if (viewHolder != null) {
                            updateNonPlayingView(viewHolder);
                        }
                        mMediaPlayer.release();
                    }
                    viewHolder = this;
                    mMediaPlayer = MediaPlayer.create(v.getContext(), mAudioFile.get(playingPosition).getResourceId());
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                    mMediaPlayer.start();
                }
                updatePlayingView();
                Toast.makeText(v.getContext(), "Playing...", Toast.LENGTH_LONG).show();
            } else if (v.getId() == moreOptionImageView.getId()) {
                Uri uri = Uri.parse("android.resource://com.anime.kyatchifurzu/" + mAudioFile.get(getAdapterPosition()).getResourceId());
                RingtoneManager.setActualDefaultRingtoneUri(v.getContext(), RingtoneManager.TYPE_NOTIFICATION, uri);

                Toast.makeText(v.getContext(), "Audio set as Notification Tune", Toast.LENGTH_LONG).show();
            }
//            listenerWeakReference.get().onPositionClickListener(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }

    }

}
