package com.anime.kyatchifurzu;

import android.media.Image;
import android.media.MediaPlayer;
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

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioListViewHolder> {

    private static final String TAG = "***AudioListAdapter****";

    private final ClickListener clickListener;
    private List<AudioFileEntity> mAudioFile;

    public AudioListAdapter(List<AudioFileEntity> audioFile, ClickListener clickListener) {
        mAudioFile = audioFile;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AudioListAdapter.AudioListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_list, parent, false);
        return new AudioListViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioListAdapter.AudioListViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");
        String names[] = mAudioFile.get(position).getName().split(":");
        holder.getTextViewAudioName().setText(names[0]);
        holder.getTextViewAnimeName().setText(names[1]);
//        holder.getTextView().setText(mAudioFile.get(position).getFileName());
    }

    @Override
    public int getItemCount() {
        return mAudioFile.size();
    }


    public static class AudioListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView textViewAudioName;
        private TextView textViewAnimeName;
        private ImageView playImageView;
        private ImageView moreOptionImageView;
        private WeakReference<ClickListener> listenerWeakReference;

        public AudioListViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);

            listenerWeakReference = new WeakReference<>(clickListener);
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
                Toast.makeText(v.getContext(), "Play button pressed " + getAdapterPosition(), Toast.LENGTH_LONG).show();
            } else if (v.getId() == moreOptionImageView.getId()) {
                Toast.makeText(v.getContext(), "More options pressed ", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(v.getContext(), "Row pressed", Toast.LENGTH_LONG).show();
            }
            listenerWeakReference.get().onPositionClickListener(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

}
