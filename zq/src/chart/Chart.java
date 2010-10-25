package chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;

import bean.ResultBean;
import dataset.BarDatasource;
import dataset.PieDatasource;

public class Chart {
	
	private JFreeChart chart = null;
	private static Logger log = Logger.getLogger(Chart.class.getName());
	
	public Chart() {}
	
	public void buildPieChart(ArrayList<ResultBean> list, String fileName) {
		PieDataset dataset = new PieDatasource(list).getDataset();
		chart = ChartFactory.createPieChart3D(
				"体验分布图",
				dataset,
				true,
				false,
				false);			
		configPieChart();
		writePiePicture(fileName);
	}
	
	public void buildBarChart(ArrayList<ResultBean> list, String fileName) {
		CategoryDataset dataset = new BarDatasource(list).getDataset();
		chart = ChartFactory.createBarChart(
				"体验柱状图",
				"体验",
				"次数",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false);
		configBarChart();
		writeBarPicture(fileName);
	}
	
	private void configPieChart() {
		PiePlot3D plot = (PiePlot3D)chart.getPlot();
		Font font = new Font("宋体", Font.PLAIN, 12);    
		TextTitle title = new TextTitle("体验分布图");
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));    
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例                   
		 plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));    
		// 设置背景色为白色    
		chart.setBackgroundPaint(Color.white);    
		// 指定图片的透明度(0.0-1.0)    
		 plot.setForegroundAlpha(1.0f);    
		// 指定显示的饼图上圆形(false)还椭圆形(true)    
		plot.setCircular(true);    
		// 设置图标题的字体    
		title.setFont(new Font("黑体", Font.PLAIN, 12));     
		chart.setTitle(title);   
		plot.setLabelFont(font); 
		chart.getLegend().setItemFont(font); 
	}
	
	private void writePiePicture(String fileName) {
		FileOutputStream fos_jpg = null;    
		try {    
		     fos_jpg=new FileOutputStream(fileName);    
		     ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f, chart, 480, 360, null);    
		     fos_jpg.close();    
		} catch (Exception e) {    
			log.error("写入图片失败："+ e);
		 } 
	}
	
	private void configBarChart() {
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();   
	    categoryplot.setDomainGridlinesVisible(true);   
	    categoryplot.setRangeCrosshairVisible(true);   
	    categoryplot.setRangeCrosshairPaint(Color.blue);   
	    
	    NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();   
	    numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
	    
	    BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();   
	    barrenderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 12));   
//	    barrenderer.setSeriesItemLabelFont(1, new Font("宋体", Font.PLAIN, 12));   
	  
	    CategoryAxis domainAxis = categoryplot.getDomainAxis();  	  
	    /*------设置X轴坐标上的文字-----------*/  
	    domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11)); 	  
	    /*------设置X轴的标题文字------------*/  
	    domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));    
	    /*------设置Y轴坐标上的文字-----------*/  
	    numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12)); 
	    /*------设置Y轴的标题文字------------*/  
	    numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
	    /*------这句代码解决了底部汉字乱码的问题-----------*/  
	    chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); 
	    
	    TextTitle title = new TextTitle("体验柱状图");
	    chart.setTitle(title);
	    
	    barrenderer.setDrawBarOutline(false);  
	    GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(   
	        0, 0, 64));   
	    /*GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F,   
	        new Color(0, 64, 0));   
	    GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(   
	        64, 0, 0)); */  
	    barrenderer.setSeriesPaint(0, gradientpaint);   
	    /*barrenderer.setSeriesPaint(1, gradientpaint1);   
	    barrenderer.setSeriesPaint(2, gradientpaint2);*/   
	   /* barrenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator(   
	        "Tooltip: {0}"));  */ 
	    CategoryAxis categoryaxis = categoryplot.getDomainAxis();   
	    categoryaxis.setCategoryLabelPositions(CategoryLabelPositions   
	        .createUpRotationLabelPositions(Math.PI / 6.0));  
	}
	
	private void writeBarPicture(String fileName) {
		FileOutputStream fos_jpg = null;    
		try {
		     fos_jpg=new FileOutputStream(fileName);    
		     ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f, chart, 480, 360, null);    
		     fos_jpg.close();    
		} catch (Exception e) {    
			log.error("写入图片失败："+ e);
		 } 
	}
}
