package net.dictionmasters.helpers;

public class DictionMasterVideo {
	public String name;
	public int imageResource;
	public String description;
	public String size;
	public String net;

	public DictionMasterVideo(String name, int imageResource, String description,String size, String net) {
		this.name = name;
		this.imageResource = imageResource;
		this.description = description;
		this.size = size;
		this.net = net;
	}

	@Override
	public String toString() {
		return name;
	}
}

