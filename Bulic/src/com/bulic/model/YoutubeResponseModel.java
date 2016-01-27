package com.bulic.model;

import java.util.ArrayList;
import java.util.List;

public class YoutubeResponseModel {
	
	public String nextPageToken;
	public List<Item> items = new ArrayList<YoutubeResponseModel.Item>();
			
	public class Item{
		public String id;
		public Snippet snippet;
		public ContentDetails contentDetails;
	}
	
	public class Snippet{
		public String title;
		public Thumbnails thumbnails;
	}
	
	public class Thumbnails{
		public ImageModel defaults;
		public ImageModel medium;
		public ImageModel high;
		public ImageModel standard;
		public ImageModel maxres;
	}
	
	public class ImageModel{
		public String url;
		public String width;
		public String height;
	}
	
	public class ContentDetails{
		public Bulletin bulletin;
		public String itemCount;
		public String videoId;
	}
	
	public class Bulletin{
		public ResourceId resourceId;
	}
	
	public class ResourceId{
		public String kind;
		public String videoId;
	}
	
}
