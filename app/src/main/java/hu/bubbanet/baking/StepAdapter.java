/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * STEPADAPTER Class
 *
 */

package hu.bubbanet.baking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import hu.bubbanet.baking.model.Step;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private List<Step> stepList;

    final private ListItemClickListener onClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItem);
    }

    StepAdapter(List<Step> list, ListItemClickListener listener) {
        this.stepList = list;
        this.onClickListener = listener;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context)
                .inflate(R.layout.steps_list_item, parent, false);

        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        Step step = stepList.get(position);
        String shortDescription = step.getShortDescription();
        holder.itemTextView.setText(shortDescription);
    }

    @Override
    public int getItemCount() {
        if (stepList == null) return 0;
        return stepList.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemTextView;

        StepViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.steps_item_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            onClickListener.onListItemClick(clickedPosition);
        }
    }
}