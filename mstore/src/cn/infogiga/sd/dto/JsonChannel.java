package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonChannel {
	@Sync(value = "id")
	private String channelId;
	@Sync(value = "channelName")
	private String channelName;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}
