package id.tiregdev.si_pemandu.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.si_pemandu.Activity.data_anak;
import id.tiregdev.si_pemandu.R;

public class adapter_anak extends RecyclerView.Adapter<adapter_anak.holder_anak> {
    private List<itemObject_anak> itemList;
    private Context context;

    public adapter_anak(Context context, List<itemObject_anak> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_anak onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_anak,null);
        holder_anak hn = new holder_anak(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_anak holder, int position){
        holder.list_user.setText(itemList.get(position).getNamaAnak());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_anak extends RecyclerView.ViewHolder {
        public TextView list_user;

        public holder_anak(View itemView){
            super(itemView);

            list_user = (TextView)itemView.findViewById(R.id.namaAnak);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, data_anak.class);
                    intent.putExtra("id_anak", itemList.get(getAdapterPosition()).getId_anak());
                    context.startActivity(intent);
                }
            });
        }
    }
}
