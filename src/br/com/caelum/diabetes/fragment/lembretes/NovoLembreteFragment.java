package br.com.caelum.diabetes.fragment.lembretes;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TimePicker;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.LembreteDao;
import br.com.caelum.diabetes.model.Lembrete;

@SuppressLint("NewApi")
public class NovoLembreteFragment extends Fragment {
	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int minuto;
	private Lembrete lembrete;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.novo_lembrete, null);
		DateTime dataAgora = new DateTime();
		final TextClock horario = (TextClock) view
				.findViewById(R.id.hora_lembrete);

		horario.setText(dataAgora.getHourOfDay() + ":"
				+ dataAgora.getMinuteOfHour());

		final TextClock data = (TextClock) view
				.findViewById(R.id.data_lembrete);
		data.setText(dataAgora.getDayOfMonth() + "/"
				+ dataAgora.getMonthOfYear() + "/" + dataAgora.getYear());

		String dataAtual = (String) data.getText();
		String[] numerosData = dataAtual.split("/");

		String horarioAtual = (String) horario.getText();
		String[] numerosHorario = horarioAtual.split(":");

		dia = Integer.parseInt(numerosData[0]);
		mes = Integer.parseInt(numerosData[1]) - 1;
		ano = Integer.parseInt(numerosData[2]);
		hora = Integer.parseInt(numerosHorario[0]);
		minuto = Integer.parseInt(numerosHorario[1]);

		horario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				TimePickerDialog timePicker = new TimePickerDialog(
						getActivity(),
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker timePicker,
									int selectedHour, int selectedMinute) {
								hora = selectedHour;
								minuto = selectedMinute;
								horario.setText(hora + ":" + minuto);
							}
						}, hora, minuto, true);
				timePicker.show();
			}
		});

		data.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DatePickerDialog datePicker = new DatePickerDialog(
						getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker arg0, int year,
									int month, int day) {
								dia = day;
								mes = month;
								ano = year;
								data.setText(dia + "/" + (mes + 1) + "/" + ano);
							}
						}, ano, mes, dia);
				datePicker.show();
			}
		});

		lembrete = new Lembrete();

		final EditText atividade = (EditText) view
				.findViewById(R.id.atividade_lembrete);
		final EditText anotacoes = (EditText) view
				.findViewById(R.id.anotacoes_lembrete);

		Button salvar = (Button) view.findViewById(R.id.salvar_lembrete);
		salvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DateTime dateTime = new DateTime(ano, mes + 1, dia, hora,
						minuto);
				lembrete.setData(dateTime);
				lembrete.setAtividade(atividade.getText().toString());
				lembrete.setAnotacoes(anotacoes.getText().toString());

				DbHelper helper = new DbHelper(getActivity());
				LembreteDao dao = new LembreteDao(helper);
				dao.salva(lembrete);
				helper.close();
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.replace(R.id.main_view,
						new DashboardLembreteFragment());
				transaction.commit();
			}

		});

		return view;
	}
}
