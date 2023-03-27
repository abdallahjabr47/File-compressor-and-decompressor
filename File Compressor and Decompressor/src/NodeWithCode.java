
public class NodeWithCode implements Comparable<NodeWithCode>{
	String code ; // code as String 
	byte value ; // example : value of a represented in bits

	// addCode * 2
	public NodeWithCode(String s, byte value) {
		super();
		this.code = s;
		this.value = value;
	}
	
	@Override
	public int compareTo(NodeWithCode a) {
		// TODO Auto-generated method stub
		if (this.value > a.value)
			return 1 ;
		if (this.value < a.value)
			return -1 ;
		else
			return 0 ;
	}
}
