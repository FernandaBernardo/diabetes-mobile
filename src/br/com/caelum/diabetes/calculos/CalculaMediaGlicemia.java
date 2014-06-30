package br.com.caelum.diabetes.calculos;

import java.util.List;

import org.joda.time.DateTime;

import android.content.Context;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.GlicemiaDao;
import br.com.caelum.diabetes.model.Glicemia;

public class CalculaMediaGlicemia {
	List<Glicemia> glicemias;
	
	public CalculaMediaGlicemia(Context context) {
		DbHelper helper = new DbHelper(context);
		GlicemiaDao dao = new GlicemiaDao(helper);
		this.glicemias = dao.getGlicemias();
		helper.close();
	}
	
	public int getMediaDaSemana() {
		DateTime data = new DateTime();
		int semana = data.getWeekyear();
		int contador = 0;
		int media = 0;
		for (Glicemia glicemia : glicemias) {
			if(glicemia.getData().getWeekyear() == semana) {
				media += glicemia.getValorGlicemia();
				contador++;
			}
		}
		media /= contador;
		return media;
	}
	
	public int getMediaDoMes() {
		DateTime data = new DateTime();
		int mes = data.getMonthOfYear();
		int contador = 0;
		int media = 0;
		for (Glicemia glicemia : glicemias) {
			if(glicemia.getData().getMonthOfYear() == mes) {
				media += glicemia.getValorGlicemia();
				contador++;
			}
		}
		media /= contador;
		return media;
	}
}
