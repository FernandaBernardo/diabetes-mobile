package br.com.caelum.diabetes.util;

import android.widget.EditText;

public class ValidatorUtils {

	public static void checkIfOnError(final EditText editText) {

		if (editText.getText().toString().length() == 0) {
			editText.setError("Campo Obrigatório!");
		}

		if (editText.getText().toString().equals("0.0")
				|| editText.getText().toString().equals("0")) {
			editText.setError("Valor Inválido");
		}

	}

	public static boolean checkEmptyEditText(EditText... editTexts) {
		boolean result = true;
		for (EditText editText : editTexts) {
			if (editText.getText().toString().equals("")
					|| editText.getText() == null) {
				result = false;
			}
		}

		return result;
	}

	public static boolean checkIfIsValid(EditText... editTexts) {
		boolean result = true;
		for (EditText editText : editTexts) {
			if (editText.getText().toString().equals("")
					|| editText.getText() == null
					|| editText.getText().toString().equals("0.0")
					|| editText.getText().toString().equals("0")) {

				result = false;
			}

		}

		return result;

	}
}
