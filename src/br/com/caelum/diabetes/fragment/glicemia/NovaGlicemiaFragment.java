package br.com.caelum.diabetes.fragment.glicemia;

import org.joda.time.DateTime;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TimePicker;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.calculos.DescobreTipoRefeicao;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.GlicemiaDao;
import br.com.caelum.diabetes.extras.TipoRefeicao;
import br.com.caelum.diabetes.model.Glicemia;

@SuppressLint("NewApi")
public class NovaGlicemiaFragment extends Fragment {
	Glicemia glicemia;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.nova_glicemia, null);
		
		final TextClock horario = (TextClock) view.findViewById(R.id.hora_glicemia);
		horario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 DateTime horaAtual = new DateTime();
		            int hora = horaAtual.getHourOfDay();
		            int minuto = horaAtual.getMinuteOfHour();
		            TimePickerDialog timePicker;
		            timePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
		                @Override
		                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
		                    horario.setText(selectedHour + ":" + selectedMinute);
		                }
		            }, hora, minuto, true);
		            timePicker.setTitle("Select Time");
		            timePicker.show();
			}
		});
		
		final TextClock data = (TextClock) view.findViewById(R.id.data_glicemia);
		data.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DateTime horaAtual = new DateTime();
		        int dia = horaAtual.getDayOfMonth();
		        int mes = horaAtual.getMonthOfYear();
		        int ano = horaAtual.getYear();
		        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker arg0, int year, int month, int day) {
						data.setText(day+ "/" + month+ "/" + year);
					}
		        }, ano, mes-1, dia);
		        datePicker.show();
			}
		});
		
		final Spinner tipoRefeicao = (Spinner) view.findViewById(R.id.tipo_refeicao);
		final ArrayAdapter<String> spinnerAdapter = (ArrayAdapter<String>) tipoRefeicao.getAdapter();
		
		glicemia = new Glicemia();
		TipoRefeicao tipo = new DescobreTipoRefeicao().getTipoRefeicao();
		
		int position = spinnerAdapter.getPosition(tipo.getText());
		if (position == -1) position = 0;
		tipoRefeicao.setSelection(position);
		
		final EditText valorGlicemia = (EditText) view.findViewById(R.id.valor_glicemia);
		Button salvarGlicemia = (Button) view.findViewById(R.id.salvar_glicemia);
		
		salvarGlicemia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				glicemia.setValorGlicemia(Integer.parseInt(valorGlicemia.getText().toString()));
				int pos = tipoRefeicao.getSelectedItemPosition();
				glicemia.setTipoRefeicao(TipoRefeicao.fromString(spinnerAdapter.getItem(pos)));
				
				String[] dataSalvar = ((String)data.getText()).split("/");
				String[] horarioSalvar = ((String)horario.getText()).split(":");
				
				DateTime dateTime = new DateTime(Integer.parseInt(dataSalvar[2]), Integer.parseInt(dataSalvar[1])+1, Integer.parseInt(dataSalvar[0]), 
						Integer.parseInt(horarioSalvar[0]), Integer.parseInt(horarioSalvar[1]));
				glicemia.setData(dateTime);
				
				DbHelper helper = new DbHelper(getActivity());
				GlicemiaDao dao = new GlicemiaDao(helper);
				dao.salva(glicemia);
				helper.close();
				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new ListaGlicemiaFragment());
				transaction.commit();
			}
		});
		return view;
	}
}
