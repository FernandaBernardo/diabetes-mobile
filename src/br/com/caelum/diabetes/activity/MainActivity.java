package br.com.caelum.diabetes.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.fragment.DashboardFragment;

public class MainActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.main);
		
		DashboardFragment fragment = new DashboardFragment();
		fragment.setArguments(getIntent().getExtras());
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_view, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	@Override
	public void onBackPressed() {
		
		DashboardFragment fragment = new DashboardFragment();
		fragment.setArguments(getIntent().getExtras());
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_view,fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	
}