package dataset;

import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import bean.ResultBean;
//import connection.Database;

public class BarDatasource {
	
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
	public BarDatasource(ArrayList<ResultBean> list) {
		fillData(list);
	}
	
	public CategoryDataset getDataset() {
		return dataset;
	}
	
	private void fillData(ArrayList<ResultBean> list) {
		int i = 1;
		for(ResultBean b: list) {
			dataset.addValue(b.getNum(), "", b.getName());
			i++;
		}
	}
}
