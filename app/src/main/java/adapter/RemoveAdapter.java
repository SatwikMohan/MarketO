package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.marketo.R;

import java.util.Set;

public class RemoveAdapter extends RecyclerView.Adapter<RemoveAdapter.RecyclerViewHolder> {
    Set<String> item;
    Set<String> name;
    Context context;
    public RemoveAdapter(Set<String> item,Set<String> name) {
        this.item=item;
        this.name=name;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.removeitem;
    }
    @NonNull
    @Override
    public RemoveAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemoveAdapter.RecyclerViewHolder holder, int position) {

        holder.delete.setImageResource(R.drawable.delete);
        String[] Name = name.toArray(new String[name.size()]);
        holder.itemname.setText(Name[position]);
        String[] Ihead=item.toArray(new String[item.size()]);
        holder.itemhead.setText(Ihead[position]);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView itemhead,itemname;
        ImageView delete;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            itemhead=itemView.findViewById(R.id.itemhead);
            itemname=itemView.findViewById(R.id.itemname);
            delete=itemView.findViewById(R.id.deleteitem);

        }
    }
}
