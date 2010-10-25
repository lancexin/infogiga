package cn.infogiga.android.experience;

import java.util.List;



import cn.infogiga.android.experience.bean.ErrInfo;
import cn.infogiga.android.experience.bean.Intf;
import cn.infogiga.android.experience.bean.RetInfo;
import cn.infogiga.android.experience.bean.UserProductRecord;
import cn.infogiga.android.experience.service.ExperienceService;
import cn.infogiga.android.experience.service.ExperienceService.ExperienceServiceBinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ComboActivity extends Activity{
	
	private static final String tag = "ComboActivity";
	
	TextView tv;
	ListView lv;
	Button b1,b2;
	protected int choose = -1;
	
	protected static String serverPwd = null; 
	
	protected String xml = null;
	protected String menuId = null;
	protected String phoneNumber = null;
	protected String funcId = null;
	protected String name = null;
	protected String pkg = null;
	protected String selfIntr = null;
	
	
	public static ExperienceServiceBinder experienceServiceBinder;
	
	private static Spanned[] GENRES;
	private static String[] VALUES;
	private static String[] NAME;
	private static String[] CURRENTSTATUS;
	private static String[] FUNCIDS;
	private String action = "1";
	
	private String scene = "5";
	
	
	protected static String WARN_MSG = null;
	
	protected static String showMessage = "程序返回值出现错误";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		hideTitleBar();
		showDialog(WAIT_DIALOG);
		try {
			Intent i = new Intent();
			i.setClass(this, ExperienceService.class);
			this.startService(i);
			bindService(i, conn, Context.BIND_AUTO_CREATE);
		} catch (RuntimeException e) {
			Toast.makeText(this,R.string.no_service, Toast.LENGTH_LONG).show();
			this.finish();
			e.printStackTrace();
		}
	}
	
	public void init(){
		Bundle bundle = getIntent().getExtras();
		//xml = bundle.getString("info");
		menuId = bundle.getString("menuId");
		funcId = bundle.getString("funcId");
		phoneNumber = bundle.getString("phoneNumber");
		name = bundle.getString("name");
		pkg = bundle.getString("pkg");
		selfIntr = bundle.getString("selfIntr");
		//isFetion = bundle.getString("isFetion");
		
		this.setContentView(R.layout.combo);
		tv = (TextView) findViewById(R.id.busi_content);
		lv = (ListView) findViewById(R.id.combo_list);
		b1 = (Button) findViewById(R.id.combo_ok);
        b2 = (Button) findViewById(R.id.combo_cancel);
        
        
        b1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(choose == -1){
					Toast.makeText(ComboActivity.this,"您必须选择一项", Toast.LENGTH_SHORT).show(); 
					return;
				}
				if("0".equals(CURRENTSTATUS[choose])){
					action = "1";
					scene= "5";
					showMessage = "您确定要订购\""+NAME[choose]+"\"吗?";
				}else{
					//WARN_MSG = "您已经成功取消业务\""+GENRES[choose]+"\"";
					showMessage = "系统检测到您已经订购\""+NAME[choose]+"\"业务,您是否要取消此业务?";
					scene= "8";
					action = "0";
				}
				new AlertDialog.Builder(ComboActivity.this)
				.setTitle("确认信息")
				.setMessage(showMessage)
				.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                	showDialog(INSERT_SERVER_PWD_DIALOG);
	                }
	            })
	            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                	//ComboActivity.this.finish();
	                	//finish();
	                }
	            })
				.show();
			}
        });
        
        b2.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View arg0) {
        		ComboActivity.this.finish();
        	}
        });
        handler.post(new Runnable(){
			@Override
			public void run() {
				String xml = experienceServiceBinder.getItemInfo(menuId, phoneNumber,"0");
				System.out.println("xml:"+xml);
				if(xml == null){
					Toast.makeText(ComboActivity.this,"数据获取失败,请检查网络是否联通,或联系管理员", Toast.LENGTH_SHORT).show(); 
					ComboActivity.this.finish();
					return;
				}
				
				Intf intf = new Intf();
				try {
					intf.parse(xml);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ErrInfo err = intf.getErrinfo(); 
				if(!"0".equals(err.getErrCode())){
					ComboActivity.this.dismissDialog(WAIT_DIALOG);
					//Toast.makeText(ComboActivity.this,err.getErrHint(), Toast.LENGTH_LONG).show(); 
					WARN_MSG = err.getErrHint()==null?"E.系统繁忙,请稍后再试":err.getErrHint();
					showDialog(WORN_DIALOG);
					return;
				}
				final RetInfo retInfo = intf.getRetInfo();
				funcId = retInfo.getFuncId();
				initQuery(retInfo);
				ComboActivity.this.dismissDialog(WAIT_DIALOG);
				if(selfIntr != null && !"".equals(selfIntr)){
					tv.setText(selfIntr);
				}else{
					if(retInfo.getBusiDesc() == null){
						tv.setText("无");
					}else{
						tv.setText(Html.fromHtml(retInfo.getBusiDesc()+(retInfo.getBusiDesc2()==null?"":retInfo.getBusiDesc2())));
						tv.setMovementMethod(LinkMovementMethod.getInstance());
					}
				}
				
				//SpinnerAdapter
				lv.setAdapter(new RadioListAdapter(ComboActivity.this,GENRES));
				
		        lv.setItemsCanFocus(false);
		        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		       
		        lv.setOnItemClickListener(new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						choose = (int) arg3;
						Log.e(tag, "you have choose:"+choose);
						//Toast.makeText(ComboActivity.this,"你选择了："+choose, Toast.LENGTH_SHORT).show(); 
					}
		        });
				
			}	
        });
		
	}
	
	
	
	protected void initQuery(final RetInfo retInfo){
		//RetInfo retInfo = info.getRetInfo();
		final List<UserProductRecord> recordList = retInfo.getUserProductList();
		
		if(recordList == null){
			Toast.makeText(ComboActivity.this,"数据获取出现错误,请联系管理员", Toast.LENGTH_SHORT).show(); 
			this.finish();
			return;
		}

		int s = recordList.size();
		GENRES = new Spanned[s];
		VALUES = new String[s];
		NAME = new String[s];
		CURRENTSTATUS = new String[s];
		FUNCIDS = new String[s];
		UserProductRecord record = null;
		for(int i=0;i<s;i++){
			 record = recordList.get(i);
			 GENRES[i]= Html.fromHtml(record.getProdName()+(record.getBusiFee()==null?"":" "+record.getBusiFee())+("0".equals(record.getCurrentStatus())?"":" <font color='red'>已开通</font>"));
			 NAME[i] = record.getProdName();
			 VALUES[i]= record.getProdCode();
			 CURRENTSTATUS[i] = record.getCurrentStatus();
			 
		}
	}
	
	ServiceConnection conn = new ServiceConnection() {
	    @Override
	    public void onServiceConnected(ComponentName name, IBinder service) {
	    	experienceServiceBinder = (ExperienceServiceBinder) service;
	    	init();
	        Log.e(tag, "onServiceConnected");
	    }

	    @Override
	    public void onServiceDisconnected(ComponentName arg0) {
	    	experienceServiceBinder = null;
	    	Log.e(tag, "onServiceDisconnected");
	    }
	};
	
	private static final int INSERT_SERVER_PWD_DIALOG = 1010111;
	
	private static final int WAIT_DIALOG = 1010112;
	
	private static final int CONFIRM_DIALOG = 1010113;
	
	private static final int WORN_DIALOG = 1010114;
	private static final int WORN_DIALOG2 = 1010115;

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case INSERT_SERVER_PWD_DIALOG:
			
			LayoutInflater factory = LayoutInflater.from(this);
	        final View textEntryView = factory.inflate(R.layout.insert_server_passwrod, null);
	        final EditText ev = (EditText) textEntryView.findViewById(R.id.server_password_edit);
			
			return new AlertDialog.Builder(this)
			.setTitle(R.string.alert_dialog_server_password_title)
			.setView(textEntryView)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                		String codeStr = ev.getText().toString();
                		if(codeStr == null || "".equals(codeStr.trim())){
                			Toast.makeText(ComboActivity.this,"服务密码不能为空", Toast.LENGTH_SHORT).show();
                			return;
                		}
                		serverPwd = codeStr;
                		showDialog(WAIT_DIALOG);
                		new Thread(new Runnable(){
							@Override
							public void run() {
								final Intf c = experienceServiceBinder.order(menuId, funcId, phoneNumber, VALUES[choose], NAME[choose],serverPwd, null,action);
								final ErrInfo errinfo = c.getErrinfo();
								dismissDialog(WAIT_DIALOG);
								if("0".equals(errinfo.getErrCode())){
									handler.post(new Runnable(){
										@Override
										public void run() {
											experienceServiceBinder.log(menuId, "3", phoneNumber,scene,NAME[choose],pkg);
											WARN_MSG = errinfo.getErrHint();
											showDialog(WORN_DIALOG);
										}
									});
									
								}else{
									handler.post(new Runnable(){
										@Override
										public void run() {
											WARN_MSG = errinfo.getErrHint();
											//showDialog(WORN_DIALOG);
											showDialog(WORN_DIALOG);
											//Toast.makeText(ComboActivity.this,errinfo.getErrHint(), Toast.LENGTH_SHORT).show();
										}
									});
								}
							}	
                		}).start();
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	
                }
            })
			.create();
		
		case WAIT_DIALOG:
			ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("正在加载数据...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            return dialog;

            
		case CONFIRM_DIALOG:
			
			new AlertDialog.Builder(ComboActivity.this)
			.setTitle("确认信息")
			.setMessage(showMessage)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	showDialog(INSERT_SERVER_PWD_DIALOG);
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	
                }
            })
			.create();
		case WORN_DIALOG:
			return new AlertDialog.Builder(this)
			.setTitle("提示信息")
			.setMessage(WARN_MSG)
			.setNegativeButton("退出", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					ComboActivity.this.finish();
				}
			})
			.create();
		case WORN_DIALOG2:
			return new AlertDialog.Builder(this)
			.setTitle("提示信息")
			.setMessage(WARN_MSG)
			.setNegativeButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					//ComboActivity.this.finish();
				}
			})
			.create();
		default:
			break;
		}
		
		return super.onCreateDialog(id);
	}
	
	Handler handler = new Handler();
	
	private static final String KILL_ACTION = "cn.infogiga.android.killprocess";

	@Override
	protected void onDestroy() {
		Intent intent = new Intent(KILL_ACTION);
		this.sendBroadcast(intent);
		unbindService(conn);
		super.onDestroy();
	}
	
	class RadioListAdapter extends BaseAdapter {

		public RadioListAdapter(Context context, Spanned[] list) {
			this.context = context;
			this.list = list;
		}

		public int getCount() {
			return list.length;
		}

		public Object getItem(int index) {
			return list[index];
		}

		public long getItemId(int index) {		
			return index;
		}
		
		public void setDefaultPosition(int position) {
			this.position = position;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			if(convertView == null) {
				convertView = inflater.inflate(R.layout.list_item_single_choice, parent, false);				
			} 
			TextView view = (TextView) convertView;		
			view.setText(list[position]);
			/*if(this.position == position) {
				view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_radio_on, 0, 0, 0);
			} else {
				view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_radio_off, 0, 0, 0);
			}*/
			return view;
		}
		
		private Spanned[] list;
		private Context context;
		private int position;
	}
	
	public void hideTitleBar(){
	   	 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        	    WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
}
