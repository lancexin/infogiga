package cn.infogiga.config;

import java.util.ResourceBundle;

import org.apache.taglibs.standard.tag.common.fmt.BundleSupport;

public class RegionConfig {

	private static ResourceBundle bundle = ResourceBundle.getBundle("region2pageno");
	
	public static String getPageNo(String regionCode) {
		return bundle.getString(regionCode);
	}
}
