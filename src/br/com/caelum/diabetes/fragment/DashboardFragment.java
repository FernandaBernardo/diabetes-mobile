package br.com.caelum.diabetes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.dialog.PreencherDadosMedicosDialog;
import br.com.caelum.diabetes.fragment.calculadora.DashboardCalculadoraFragment;
import br.com.caelum.diabetes.fragment.calculadora.SelecionaRefeicaoFragment;
import br.com.caelum.diabetes.fragment.glicemia.DashboardGlicemiaFragment;
import br.com.caelum.diabetes.fragment.glicemia.NovaGlicemiaFragment;
import br.com.caelum.diabetes.fragment.perfil.ConfigurarPerfilFragment;
import br.com.caelum.diabetes.model.Paciente;

public class DashboardFragment extends Fragment{
	
	private PacienteDao dao;
	private Paciente paciente;
	
	private void newPaciente(){
		DbHelper helper = new DbHelper(getActivity());
		dao = new PacienteDao(helper);
		paciente = dao.getPaciente();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dashboard, null);
		Button calculadora = (Button) view.findViewById(R.id.main_calculadora);
		calculadora.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				newPaciente();
				
				if(!paciente.hasMedicalInfo()){
					PreencherDadosMedicosDialog dadosMedicosDialog = new PreencherDadosMedicosDialog();
					FragmentManager fm = getFragmentManager();
					dadosMedicosDialog.show(fm, "dashboard_fragment");
				}else{
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.main_view, new SelecionaRefeicaoFragment());
					transaction.commit();
				}
				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new DashboardCalculadoraFragment());
				transaction.commit();
			}
		});
		
		Button perfil = (Button) view.findViewById(R.id.main_perfil);
		perfil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new ConfigurarPerfilFragment());
				transaction.commit();
			}
		});
		
		Button medicao = (Button) view.findViewById(R.id.main_medicao);
		medicao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new DashboardGlicemiaFragment());
				transaction.commit();
			}
		});
		
		return view;
	}
}