package cindy.page.hibernate;




public class CirteriaQuery {
	
	public static final int EQ = 0;

	public static final int LIKE = 1;
	
	public static final int BETWEED = 2;
	
	public static final int IS_STRING = 3;
	
	public static final int IS_INT = 4;
	
	public static final int IS_OBJECT = 5;
	
	private int mark = -1;
	
	private int type = -1;
	
	private String[] alias = null;
	
	private String prame = null;
	
	private Object value = null;
	
	private Object[] values = null;
	
	public int getMark() {
		return mark;
	}

	public int getType() {
		return type;
	}

	public String[] getAlias() {
		return alias;
	}

	public String getPrame() {
		return prame;
	}

	public Object getValue() {
		return value;
	}

	public Object[] getValues() {
		return values;
	}

	public CirteriaQuery(int mMark,int mType,String mPrame,Object mValue,String[] mAlias){
		type = mType;
		mark = mMark;
		prame = mPrame;
		value = mValue;
		alias = mAlias;
	}
	
	public CirteriaQuery(int mMark,int mType,String mPrame,Object[] mValues,String[] mAlias){
		type = mType;
		mark = mMark;
		prame = mPrame;
		values = mValues;
		alias = mAlias;
	}
	
	public CirteriaQuery setAlias(String value,String al,String vl){
		this.alias = new String[2];
		this.alias[0] = al;
		this.alias[1] = vl;
		return this;
	}
}
