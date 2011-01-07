package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Channel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Channel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String channelName;
	private Set bissinusshalls = new HashSet(0);

	// Constructors

	/** default constructor */
	public Channel() {
	}

	/** full constructor */
	public Channel(String channelName, Set bissinusshalls) {
		this.channelName = channelName;
		this.bissinusshalls = bissinusshalls;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Set getBissinusshalls() {
		return this.bissinusshalls;
	}

	public void setBissinusshalls(Set bissinusshalls) {
		this.bissinusshalls = bissinusshalls;
	}

}