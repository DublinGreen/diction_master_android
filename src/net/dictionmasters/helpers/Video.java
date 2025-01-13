package net.dictionmasters.helpers;

public class Video {
	public String videoName;
	public int imageResource;
	public String videoDescription;
	public String videoSize;
	public String videoNet;

	public Video(String id, int imageResource, String instructions, String size, String videoNet) {
		this.videoName = id;
		this.imageResource = imageResource;
		this.videoDescription = instructions;
		this.videoSize = size;
		this.videoNet = videoNet;
	}

	@Override
	public String toString() {
		return videoName;
	}
}

