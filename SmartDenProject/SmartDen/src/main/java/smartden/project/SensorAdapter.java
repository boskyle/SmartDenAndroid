/*Team Name: Humber Elites*/
package smartden.project;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.MyViewHolder> {

    private List<Sensor_Info> sensorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sensor_name, qrcode, location,uid;

        public MyViewHolder(View view) {
            super(view);
            sensor_name = (TextView) view.findViewById(R.id.sensor_ID);
            qrcode = (TextView) view.findViewById(R.id.sensor_code);
            location = (TextView) view.findViewById(R.id.sensor_location);

        }
    }


    public SensorAdapter(List<Sensor_Info> sensorList) {
        this.sensorList = sensorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensor_format, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Sensor_Info sensor = sensorList.get(position);
        holder.sensor_name.setText(sensor.getname());
        holder.qrcode.setText(sensor.getQrcode());
        holder.location.setText(sensor.getLocation());
        //holder.uid.setText(sensor.getUid());
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }
}
