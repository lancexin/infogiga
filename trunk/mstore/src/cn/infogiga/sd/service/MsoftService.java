package cn.infogiga.sd.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.infogiga.pojo.Attachment;
import cn.infogiga.pojo.Phonebrand;
import cn.infogiga.pojo.Phonebrandcategory;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Soft;

public interface MsoftService {
	public void addPhonebrand(Phonebrand phonebrand,HttpServletRequest request);
	public void addPhonebrands(List<Phonebrand> phonebrandList,HttpServletRequest request);
	public void deletePhonebrand(Phonebrand phonebrand,HttpServletRequest request);
	
	public void addCategory(Phonebrandcategory category,HttpServletRequest request);
	public void addCategories(List<Phonebrandcategory> PhonebrandcategoryList,HttpServletRequest request);
	public void deleteCategory(Phonebrandcategory category,HttpServletRequest request);
	
	public void addPhonetype(Phonetype phonetype,HttpServletRequest request);
	public void addPhonetypes(List<Phonetype> phonetypes,HttpServletRequest request);
	public void deletePhoentype(Phonetype phonetype,HttpServletRequest request);
	
	public void addAttachment(Attachment attachment,HttpServletRequest request);
	public void addAttachments(List<Attachment> attachments,HttpServletRequest request);
	public void deleteAttachment(Attachment attachment,HttpServletRequest request);
	public void deleteAttachments(Soft soft,HttpServletRequest request);
	
	public void deleteAll(HttpServletRequest request);
}
