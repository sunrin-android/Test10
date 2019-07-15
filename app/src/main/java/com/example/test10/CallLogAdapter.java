package com.example.test10;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;

public class CallLogAdapter extends ArrayAdapter<CallLogVO> {
    Context context;
    int resId;
    ArrayList<CallLogVO> datas;

    public CallLogAdapter(Context context, int resource, ArrayList<CallLogVO> objects) {
        super(context, resource, objects);
        // 멤버 변수
        this.context = context;
        this.resId = resource;
        this.datas = objects;
    }


    // 전체 데이터 개수
    @Override
    public int getCount() {
        return datas.size();
    }

    // 한 개 item 완성해서 return
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // main_list_item.xml 인플레이션
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            CallLogWrapper wrapper = new CallLogWrapper(convertView);
            convertView.setTag(wrapper);
        }

        // 한 개 item 데이터 가져오기(ArrayList에서)
        final CallLogVO call = datas.get(position);

        // view에 데이터 넣기(set)
        CallLogWrapper holder = (CallLogWrapper)convertView.getTag();
        holder.nameView.setText(call.name);
        holder.dateView.setText(call.date);

        if(call.photo.equals("yes"))
            holder.personImageView.setImageResource(R.drawable.hong);
        else
            holder.personImageView.setImageResource(R.drawable.ic_person);

        // 이벤트 처리 - 전화걸기
        holder.dialerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+call.phone));
                if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                    context.startActivity(intent);
                else
                    Toast.makeText(context, "권한이 허용되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
