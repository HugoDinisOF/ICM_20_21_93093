package pt.ua.icm.hw02;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import pt.ua.icm.hw02.datamodel.Weather;

public class WeatherListAdapter extends
        RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private final LinkedList<Weather> mWeatherList;
    private LayoutInflater mInflater;

    public WeatherListAdapter(Context context, LinkedList<Weather> weatherList) {
        mInflater = LayoutInflater.from(context);
        this.mWeatherList = weatherList;
            }
    @NonNull
    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.weatherlist_item,
        parent, false);
        return new WeatherViewHolder(mItemView, this);
            }
    @Override
    public void onBindViewHolder(@NonNull WeatherListAdapter.WeatherViewHolder holder, int position) {
        Weather mCurrent = mWeatherList.get(position);
        holder.tMaxItemView.setText(String.valueOf(mCurrent.getTMax()));
        holder.tMinItemView.setText(String.valueOf(mCurrent.getTMin()));
        holder.diaItemView.setText(mCurrent.getForecastDate());
        Log.d("bra","chegou aqui");
            }

    @Override
    public int getItemCount() {
            return mWeatherList.size();
            }
    class WeatherViewHolder extends RecyclerView.ViewHolder {
        public final TextView tMaxItemView;
        public final TextView tMinItemView;
        public final TextView diaItemView;
        final WeatherListAdapter mAdapter;
        public WeatherViewHolder(View itemView, WeatherListAdapter adapter) {
            super(itemView);
            tMaxItemView = itemView.findViewById(R.id.txtTMax);
            tMinItemView = itemView.findViewById(R.id.txtTMin);
            diaItemView = itemView.findViewById(R.id.txtDia);
            this.mAdapter = adapter;
        }
    }
}