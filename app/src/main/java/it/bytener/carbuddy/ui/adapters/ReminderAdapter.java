package it.bytener.carbuddy.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.interfaces.models.IReminder;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<IReminder> reminders;

    public ReminderAdapter(List<IReminder> reminders){
        this.reminders = reminders;
    }

    public void swapReminders(List<IReminder> reminders){
        this.reminders = reminders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reminder_main, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        if (reminders == null) return 0;
        return reminders.size();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reminder_name)
        TextView reminderName;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(itemView);
        }

        public void bindView(int position){
            reminderName.setText(reminders.get(position).getDescription());
        }
    }
}
