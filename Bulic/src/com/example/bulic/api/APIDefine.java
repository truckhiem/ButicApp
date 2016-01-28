package com.example.bulic.api;

public class APIDefine {
	
	public static final int RESPONSE_OK = 0;
	public static final int RESPONSE_FAIL = 1;
	
	public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
	public static final String API_KEY = "&key=AIzaSyCQQBtu78eJ9cz-ARMyYKA6ayW196x5PhE";
	public static final String URL_GET_PLAYLIST = "playlists?fields=items(contentDetails,id,snippet(title,thumbnails)),nextPageToken,prevPageToken&channelId=%s&part=id,snippet,contentDetails&maxResults=%s";
	public static final String URL_GET_ACTIVITY = "activities?fields=items(contentDetails,id,snippet(title,thumbnails)),nextPageToken,prevPageToken&channelId=%s&part=id,snippet,contentDetails&maxResults=%s";
	public static final String URL_PLAYLIST_ITEM = "playlistItems?fields=items(contentDetails,id,snippet(title,thumbnails))&part=id,snippet,contentDetails&playlistId=PLFgquLnL59andd8u3e-Ji8bBmM1vX8uHV";
//	public static final String URL_PLAYLIST_ITEM = "https://www.googleapis.com/youtube/v3/playlistItems?part=contentDetails&playlistId={PLAYLIST_ID}";
	
	public class Catalog{
		public static final String POP_MUSIC = "UCE80FOXpJydkkMo-BYoJdEg";
		public static final String HIPHOP_MUSIC = "UCUnSTiCHiHgZA9NQUG6lZkQ";
		public static final String ROCK_MUSIC = "UCRZoK7sezr5KRjk7BBjmH6w";
		public static final String RHYTHM_AND_BLUES = "UCvwDeZSN2oUHlLWYRLvKceA";
		public static final String SOUL_MUSIC = "UCsFaF_3y_L__y8kWAIEhv1w";
		public static final String REGGAE = "UCEdvzYtzTH_FFpB3VRjFV6Q";
		public static final String COUNTRY_MUSIC = "UClYMFaf6IdjQnZmsnw9N1hQ";
		public static final String FUNK = "UCxk1wRJGOTmzJAbvbQ8VicQ";
		public static final String 	FOLK_MUSIC = "UC9GxgUzRt2qUIII3tSSRjwQ";
		public static final String MIDDLE_EASTERN_MUSIC = "UCkkGN2n-fAmGhTFJOXvdIJw";
		public static final String JAZZ = "UC7KZmdQxhcajZSEFLJr3gCg";
		public static final String DISCO = "UCNGkvx5UwHzqlo6zDgRDYsQ";
		public static final String CLASSICAL_MUSIC = "UCLwMU2tKAlCoMSbGQDuiMSg";
		public static final String ELECTRONIC_MUSIC = "UCCIPrrom6DIftcrInjeMvsQ";
		public static final String LATIN_AMERICAN_MUSIC = "UCYYsyo5ekR-2Nw10s4mj3pQ";
		public static final String BLUES = "UCYlU_M1PLtYZ6qTfKIUlxLQ";
		public static final String CHILDRENS_MUSIC = "UCMBT_zT5NtEG_3Nn3XSPTxw";
		public static final String NEW_AGE_MUSIC = "UCfqBDMEJrevX2_2XBUSxAqg";
		public static final String VOCAL_MUSIC = "UCrrrTqJSxijC3hIJ-2oL8mw";
		public static final String AFRICAN_MUSIC = "UCadO807x4w5SAo-KKnQTMcA";
		public static final String CHRISTIAN_MUSIC = "UCnl8lkoNIxpKL9aO0wqHYfA";
		public static final String ASIAN_MUSIC = "UCDQ_5Wcc54n1_GrAzf05uWQ";
		public static final String SKA = "UCvQrmdG4yaae7QhUI2AOxNw";
		public static final String TRADITIONAL_MUSIC = "UCbMcht964OUJoeVi9oxFcKg";
		public static final String INDEPENDENT_MUSIC = "UCm-O8i4MEqBWq2wB03YGtfg";
	}
}
