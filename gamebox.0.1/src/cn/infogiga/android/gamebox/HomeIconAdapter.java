package cn.infogiga.android.gamebox;




import java.util.List;







import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeIconAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Bitmap icon;
	List<AppInfo> applist;
	Context context;

	public HomeIconAdapter(Context context, List<AppInfo> list) {
		mInflater = LayoutInflater.from(context);
		applist = list;
		this.context = context;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return applist.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return applist.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		AppInfo applifo = applist.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.grid_ltem_icon_text, null);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(applifo.title);
		holder.icon.setImageDrawable(applifo.icon);
		return convertView;
	}



}
