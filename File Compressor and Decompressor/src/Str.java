
public class Str {
	String s;

	// StageOfDecompressoin & readTree
	public Str(String s) {
		this.s = s;
	}
	
	// getByteBinaryString
	String peek(int i) {
		if (i > s.length())
			return "";
		String r = s.substring(0, i);
		s = s.substring(i);
		return r;
	}
}
