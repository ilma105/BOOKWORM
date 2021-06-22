package com.example.system_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.system_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

public class RecyclerAdapterdrama extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int type=1;
    //  private static final Object TYPE = ;
    private final Context context;
    private final List<Object> listRecycleItem;
    private int[] images;
    private String[] Url;
    // int image[];

    public RecyclerAdapterdrama(Context context, List<Object> listRecycleItem, int[] images,String [] url) {
        this.context = context;
        this.listRecycleItem = listRecycleItem;
        this.images=images;
        this.Url=url;
    }
    public class  ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView Bookname;
        private TextView ratings;
        private TextView author;
        private TextView description;
        private ImageView img;

        ImageView bookCoverImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            Bookname=(TextView)itemView.findViewById(R.id.bookname);
            author=(TextView)itemView.findViewById(R.id.authorname);
            description=(TextView)itemView.findViewById(R.id.description);

            ratings=(TextView)itemView.findViewById(R.id.rating);

            img=(ImageView)itemView.findViewById(R.id.img);

            bookCoverImage =(ImageView) itemView.findViewById(R.id.image);

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i)
        {
            case  type:
            default:
                View layoutview= LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_item,viewGroup,false
                );
                return new ItemViewHolder(layoutview);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        DatabaseReference databaseReference;
        FirebaseUser firebaseUser= mAuth.getCurrentUser();
        String  profileid=firebaseUser.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("download").child(profileid);
        int viewtype=getItemViewType(i);
        switch (viewtype){
            case type:
            default:
                ItemViewHolder itemViewHolder=(ItemViewHolder)holder;
                com.example.system_project.dramatic Dramatic=(com.example.system_project.dramatic)listRecycleItem.get(i);
                itemViewHolder.Bookname.setText(Dramatic.getName());
                itemViewHolder.author.setText(Dramatic.getAuthor());
                itemViewHolder.description.setText(Dramatic.getDescrip());
                itemViewHolder.ratings.setText(Dramatic.getRating());

                ((RecyclerAdapterdrama.ItemViewHolder) holder).img.setImageResource(images[i]);
                ((RecyclerAdapterdrama.ItemViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
if(Url[i]!=null) {
    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Url[i])));
    String str=itemViewHolder.Bookname.getText().toString();
    String str1=itemViewHolder.author.getText().toString();
    String str2=itemViewHolder.ratings.getText().toString();
    String str3="Drama";

    String key=databaseReference.push().getKey();
    DownloadClass user=new DownloadClass(images[i],str,str1,str3,str2,Url[i]);
    databaseReference.child(key).setValue(user);
}
else{
         Toast.makeText(context,"Not available",Toast.LENGTH_SHORT).show();
}
                    }
                });


        }

    }

    @Override
    public int getItemCount() {
        return listRecycleItem.size();
    }
}
