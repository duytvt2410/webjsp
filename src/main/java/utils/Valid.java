package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valid {
	private static final String VIETNAMESE_DIACRITIC_CHARACTERS = "ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";

	private static boolean isContainsSpecialCharacter(String str) {
		if (str != null && !str.trim().isEmpty()) {
			return !str.matches("[\\w " + VIETNAMESE_DIACRITIC_CHARACTERS + ",]*");
		}
		return false;
	}

	private static boolean isValidText(String str) {
		if (str != null && !str.trim().isEmpty()) {
			return str.matches("[\\w " + VIETNAMESE_DIACRITIC_CHARACTERS + " ,.\\-:+)(&]*");
		}
		return false;
	}

	private static boolean isContainsDigit(String str) {
		boolean containsDigit = false;
		if (str != null && !str.trim().isEmpty()) {
			for (char c : str.toCharArray()) {
				if (containsDigit = Character.isDigit(c)) {
					break;
				}
			}
		}
		return containsDigit;
	}
	
	public static boolean isEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public static String checkNameNotContainSpecialCharacterDigit(String name, int minSize, int maxSize) {
		if (name == null)
			return SystemContain.EMPTY;
		if (isContainsSpecialCharacter(name) == true)
			return SystemContain.CONTAINS_SPECIAL_CHARACTER;
		if (isOverLength(name, minSize, maxSize) == true)
			return SystemContain.OVER_SIZE;
		if (isContainsDigit(name) == true)
			return SystemContain.CONTAINS_DIGIT;
		return "valid";
	}

	public static String checkNameNotContainSpecial(String name, int minSize, int maxSize) {
		if (name == null)
			return SystemContain.EMPTY;
		if (!isValidText(name) == true)
			return SystemContain.CONTAINS_SPECIAL_CHARACTER;
		if (isOverLength(name, minSize, maxSize) == true)
			return SystemContain.OVER_SIZE;
		return "valid";
	}
	
	public static String checkEmail(String email, int minSize, int maxSize) {
		if (email == null)
			return SystemContain.EMPTY;
		if (!isEmail(email))
			return SystemContain.NOTEMAIL;
		if (isOverLength(email, minSize, maxSize) == true)
			return SystemContain.OVER_SIZE;
		return "valid";
	}
	
	 public static boolean checkPassword(String password) {
		 if (password == null || password == "")
				return false;
	        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
	    }

	public static boolean isOverLength(String str, int min, int max) {
		if (str == null || str.trim().isEmpty()) {
			return true;

		}
		return str.trim().length() > max || str.trim().length() < min ? true : false;
	}
}
