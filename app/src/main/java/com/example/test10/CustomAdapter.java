package com.example.test10;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Custom> {
    Context context;
    int layoutId;
    ArrayList<Custom> datas;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Custom> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        datas = objects;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);

            CustomHolder customHolder = new CustomHolder(convertView);
            convertView.setTag(customHolder);
        }

        ImageView profileIcon = convertView.findViewById(R.id.profileIcon);
        TextView name = convertView.findViewById(R.id.name);
        TextView date = convertView.findViewById(R.id.date);
        TextView phone = convertView.findViewById(R.id.phone);

        // 현재 위치에 해당하는 data 확보
        final Custom custom  = datas.get(position);
        CustomHolder holder = (CustomHolder)convertView.getTag();

        // view에 data 넣기
        holder.titleView.setText(drive.title);
        holder.dateView.setText(drive.date);

        if (drive.type.equals("doc"))
            holder.typeImage.setImageResource(R.drawable.ic_type_doc);
        else if(drive.type.equals("file"))
            holder.typeImage.setImageResource(R.drawable.ic_type_file);
        else if(drive.type.equals("img"))
            holder.typeImage.setImageResource(R.drawable.ic_type_image);

        // 파일 제목을 클릭했을 때 이벤트 처리
        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, drive.title, Toast.LENGTH_SHORT).show();
            }
        });

        // 메뉴이미지를 클릭했을 때 이벤트 처리
        holder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 추가
//                datas.add(new Drive("doc", "파일 추가","최종수정날짜 : 6월 10일"));
                // 삭제 - db 삭제, liveview 삭제
                DBHelper helper = new DBHelper(context);
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "DELETE FROM tb_calllog WHERE _id=" + drive.id;
                db.execSQL(sql);
                db.close();

                datas.remove(position);
                DriveAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;
        return super.getView(position, convertView, parent);
    }
}
