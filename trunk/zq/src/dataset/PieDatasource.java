package dataset;

import java.util.ArrayList;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

//import connection.Database;
import bean.ResultBean;

public class PieDatasource {
	
	private DefaultPieDataset dataset = new DefaultPieDataset();
//	private Database db = new Database();
	
	public PieDatasource(ArrayList<ResultBean> list) {
		fillData(list);
	}
	
	public PieDataset getDataset() {
		return dataset;
	}
	
	private void fillData(ArrayList<ResultBean> list) {	
		for(ResultBean b: list) {
			dataset.setValue(b.getName(), b.getNum());
		}
	}
}
