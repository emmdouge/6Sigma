package chart;

import component.Range;
import component.XBar;
import reader.Data;

public class XBarR {

	public XBarR(Data d) throws Exception {
		this(1, d);
	}
	
	public XBarR(int sets, Data d) throws Exception {
		Range r = new Range(sets, d);
		XBar x = new XBar(sets, d, r.getAvgRange());
	}
}
