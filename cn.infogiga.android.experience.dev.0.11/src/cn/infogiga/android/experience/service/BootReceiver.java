package cn.infogiga.android.experience.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 
 * @author cindy
 *
 */
public class BootReceiver extends BroadcastReceiver  {
	
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
        i.setClass(context, ExperienceService.class);
        context.startService(i);
	}

}
