package br.com.fiap.thebestway.domain.validation.utils;

//Créditos: https://gist.github.com/adrianoluis/5043397d378ae506d87366abb0ab4e30

public class DocumentUtil {

	// CPF
	private static final int[] weightSsn = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int recursiveSum(int[] weight, char[] chr, int number) {
		if (number <= 0)
			return 0;
		final int chrIndex = number - 1;
		final int weightIndex = weight.length > chr.length ? number : chrIndex;
		return (recursiveSum(weight, chr, chrIndex) + Character.getNumericValue(chr[chrIndex]) * weight[weightIndex]);
	}

	private static int calculate(final String str, final int[] weight) {
		final char[] chr = str.toCharArray();
		int sum = recursiveSum(weight, chr, chr.length);
		sum = 11 - (sum % 11);
		return sum > 9 ? 0 : sum;
	}

	/**
	 * Valida CPF
	 *
	 * @param ssn
	 * @return
	 */
	public static boolean isValidCPF(final String ssn) {
		if ((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}"))
			return false;

		final Integer digit1 = calculate(ssn.substring(0, 9), weightSsn);
		final Integer digit2 = calculate(ssn.substring(0, 9) + digit1, weightSsn);
		return ssn.equals(ssn.substring(0, 9) + digit1.toString() + digit2.toString());
	}
}