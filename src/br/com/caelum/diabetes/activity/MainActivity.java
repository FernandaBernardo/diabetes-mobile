package br.com.caelum.diabetes.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.fragment.DashboardFragment;

public class MainActivity extends FragmentActivity{
	  private DrawerLayout menuLateral;
	  private ListView listaMenuLateral;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.main);
		
		String[] titulos = {"Home", "Calculadora"};
		
        menuLateral = (DrawerLayout) findViewById(R.id.menu_lateral);
        listaMenuLateral = (ListView) findViewById(R.id.lista_menu_lateral);

        listaMenuLateral.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titulos));
        listaMenuLateral.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
		});
        
        Button logo = (Button) findViewById(R.id.logo);
        logo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuLateral.openDrawer(listaMenuLateral);
			}
		});		
		
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