package it.instruman.treasurecruisedatabase;

/**
 * Created by Paolo on 05/10/2016.
 */

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class listViewAdapter extends BaseAdapter {
    private ArrayList<HashMap> list;
    private Context activity;

    public listViewAdapter(Context activity, ArrayList<HashMap> list) {
        super();
        this.activity = activity;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public Integer getIDfromPosition(int position) {
        return (Integer) list.get(position).get(Constants.ID);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.column_row, parent, false);
            holder = new ViewHolder();
            holder.charId = convertView.findViewById(R.id.char_id);
            holder.smallImg = convertView.findViewById(R.id.small_img);
            holder.txtName = convertView.findViewById(R.id.name);
            holder.txtType = convertView.findViewById(R.id.type);
            holder.txtStars = convertView.findViewById(R.id.stars);
            holder.txtAtk = convertView.findViewById(R.id.atk);
            holder.txtHP = convertView.findViewById(R.id.hp);
            holder.txtRCV = convertView.findViewById(R.id.rcv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);

        Glide
                .with(activity)
                .load("http://onepiece-treasurecruise.com/wp-content/uploads/f" + convertID((Integer) map.get(Constants.ID)) + ".png")
                .dontTransform()
                .override(MainActivity.thumbnail_width, MainActivity.thumbnail_height)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.smallImg);

        holder.txtName.setText((String) map.get(Constants.NAME));
        String SEC_COL = (String) map.get(Constants.TYPE);
        Pattern p = Pattern.compile("(\\w{3}),(\\w{3})");
        Matcher m;
        if((m = p.matcher(SEC_COL)).find()) {
            holder.txtType.setText(String.format("%s"+System.getProperty("line.separator")+"%s", m.group(1), m.group(2)));

            GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                    new int[] {getColorFromType(m.group(1)), getColorFromType(m.group(2))});
            gradient.setAlpha((int)(255*0.5));

            if(Build.VERSION.SDK_INT>=16)
                holder.txtType.setBackground(gradient);
            else holder.txtType.setBackgroundDrawable(gradient);
            holder.txtType.setTextColor(activity.getResources().getColor(getResIdFromAttribute(activity, R.attr.char_info_txt)));
        } else {
            holder.txtType.setText(SEC_COL);
            switch (SEC_COL) {
                case "STR":
                    holder.txtType.setBackgroundColor(activity.getResources().getColor(R.color.str_bg));
                    holder.txtType.setTextColor(activity.getResources().getColor(R.color.str_txt));
                    break;
                case "QCK":
                    holder.txtType.setBackgroundColor(activity.getResources().getColor(R.color.qck_bg));
                    holder.txtType.setTextColor(activity.getResources().getColor(R.color.qck_txt));
                    break;
                case "DEX":
                    holder.txtType.setBackgroundColor(activity.getResources().getColor(R.color.dex_bg));
                    holder.txtType.setTextColor(activity.getResources().getColor(R.color.dex_txt));
                    break;
                case "PSY":
                    holder.txtType.setBackgroundColor(activity.getResources().getColor(R.color.psy_bg));
                    holder.txtType.setTextColor(activity.getResources().getColor(R.color.psy_txt));
                    break;
                case "INT":
                    holder.txtType.setBackgroundColor(activity.getResources().getColor(R.color.int_bg));
                    holder.txtType.setTextColor(activity.getResources().getColor(R.color.int_txt));
                    break;
                default:
                    break;
            }
        }
        Double stars = (Double)map.get(Constants.STARS);
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.DOWN);
        String stars_p = df.format(stars);
        if(stars==5.5)
            holder.txtStars.setText("5+");
        else if (stars==6.5)
            holder.txtStars.setText("6+");
        else
            holder.txtStars.setText(stars_p);
        switch (stars_p) {
            case "1":
            case "2":
                holder.txtStars.setBackgroundColor(activity.getResources().getColor(R.color.bronze_bg));
                holder.txtStars.setTextColor(activity.getResources().getColor(R.color.bronze_txt));
                break;
            case "3":
                holder.txtStars.setBackgroundColor(activity.getResources().getColor(R.color.silver_bg));
                holder.txtStars.setTextColor(activity.getResources().getColor(R.color.silver_txt));
                break;
            case "4":
            case "5":
                holder.txtStars.setBackgroundColor(activity.getResources().getColor(R.color.gold_bg));
                holder.txtStars.setTextColor(activity.getResources().getColor(R.color.gold_txt));
                break;
            case "6":
                holder.txtStars.setBackgroundColor(activity.getResources().getColor(R.color.red_bg));
                holder.txtStars.setTextColor(activity.getResources().getColor(R.color.red_txt));
                break;
        }
        holder.txtAtk.setText(map.containsKey(Constants.MAXATK) ? String.valueOf(map.get(Constants.MAXATK)) : "");
        holder.txtHP.setText(map.containsKey(Constants.MAXHP) ? String.valueOf(map.get(Constants.MAXHP)) : "");
        holder.txtRCV.setText(map.containsKey(Constants.MAXRCV) ? String.valueOf(map.get(Constants.MAXRCV)) : "");
        holder.charId.setText(String.valueOf(map.get(Constants.ID)));
        return convertView;
    }

    private String convertID(Integer ID) {
        if ((ID==574)||(ID==575)) {
            return ("00" + ID.toString());
        }
        if (ID < 10) return ("000" + ID.toString());
        else if (ID < 100) return ("00" + ID.toString());
        else if (ID < 1000) return ("0" + ID.toString());
        else return ID.toString();
    }

    private class ViewHolder {
        TextView charId;
        ImageView smallImg;
        TextView txtName;
        TextView txtType;
        TextView txtStars;
        TextView txtAtk;
        TextView txtHP;
        TextView txtRCV;
    }

    private int getColorFromType(String type) {
        switch (type) {
            case "STR":
                return activity.getResources().getColor(R.color.str_bg);
            case "QCK":
                return activity.getResources().getColor(R.color.qck_bg);
            case "DEX":
                return activity.getResources().getColor(R.color.dex_bg);
            case "PSY":
                return activity.getResources().getColor(R.color.psy_bg);
            case "INT":
                return activity.getResources().getColor(R.color.int_bg);
            default:
                return Color.WHITE;
        }
    }

    private boolean isLightColor(String type) {
        switch (type) {
            case "STR":
                return false;
            case "QCK":
                return false;
            case "DEX":
                return true;
            case "PSY":
                return true;
            case "INT":
                return false;
            default:
                return true;
        }
    }

    public static int getResIdFromAttribute(final Context activity, final int attr) {
        if (attr == 0)
            return 0;
        final TypedValue typedvalueattr = new TypedValue();
        activity.getTheme().resolveAttribute(attr, typedvalueattr, true);
        return typedvalueattr.resourceId;
    }
}
