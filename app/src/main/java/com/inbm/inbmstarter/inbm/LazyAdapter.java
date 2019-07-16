package com.inbm.inbmstarter.inbm;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class LazyAdapter extends BaseAdapter {

    
    protected static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    protected int layout;
    public Context context;
    public LazyAdapter(Context context, int layout) {
        this.layout = layout;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(context.getApplicationContext());
        this.context = context;
    }

    public abstract int getCount();

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    abstract public void display(int position, View v);
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    
    	convertView = inflater.inflate(layout, null);
    	display(position, convertView);
    	
	    
		return convertView;
    }
    
    protected void setText(View v, int id, String str){
    	TextView tv = (TextView)v.findViewById(id);
    	tv.setText(str);
    }
    
    protected void setImage(View v, int id, String url){
    	ImageView iv = (ImageView)v.findViewById(id);
    	System.out.println("LazyAdapter/setImage");
    	imageLoader.DisplayImage(url, iv);
    }
}