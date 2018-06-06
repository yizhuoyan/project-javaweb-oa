package com.neusoft.thirdparty;

import java.util.List;

public class WeatherResponseJson {
		private String date;
		private String message;
		private int  status;
		private String city;
		private int count;
		private Data data; 
		
		
		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Data getData() {
			return data;
		}

		public void setData(Data data) {
			this.data = data;
		}

		public static class Data{
			private String shidu;
			private double pm25;
			private double pm10;
			private String quality;
			private String wendu;
			private String ganmao;
			private Forecast yesterday;
			private List<Forecast> forecast;
			public String getShidu() {
				return shidu;
			}
			public void setShidu(String shidu) {
				this.shidu = shidu;
			}
			public double getPm25() {
				return pm25;
			}
			public void setPm25(double pm25) {
				this.pm25 = pm25;
			}
			public double getPm10() {
				return pm10;
			}
			public void setPm10(double pm10) {
				this.pm10 = pm10;
			}
			public String getQuality() {
				return quality;
			}
			public void setQuality(String quality) {
				this.quality = quality;
			}
			public String getWendu() {
				return wendu;
			}
			public void setWendu(String wendu) {
				this.wendu = wendu;
			}
			public String getGanmao() {
				return ganmao;
			}
			public void setGanmao(String ganmao) {
				this.ganmao = ganmao;
			}
			public Forecast getYesterday() {
				return yesterday;
			}
			public void setYesterday(Forecast yesterday) {
				this.yesterday = yesterday;
			}
			public List<Forecast> getForecast() {
				return forecast;
			}
			public void setForecast(List<Forecast> forecast) {
				this.forecast = forecast;
			}
			
		};
		
		public static class Forecast{
			private String date;
			private String sunrise;
			private String high;
			private String low;
			private String sunset;
			private double aqi;
			private String fx;
			private String fl;
			private String type;
			private String notice;
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
			}
			public String getSunrise() {
				return sunrise;
			}
			public void setSunrise(String sunrise) {
				this.sunrise = sunrise;
			}
			public String getHigh() {
				return high;
			}
			public void setHigh(String high) {
				this.high = high;
			}
			public String getLow() {
				return low;
			}
			public void setLow(String low) {
				this.low = low;
			}
			public String getSunset() {
				return sunset;
			}
			public void setSunset(String sunset) {
				this.sunset = sunset;
			}
			public double getAqi() {
				return aqi;
			}
			public void setAqi(double aqi) {
				this.aqi = aqi;
			}
			public String getFx() {
				return fx;
			}
			public void setFx(String fx) {
				this.fx = fx;
			}
			public String getFl() {
				return fl;
			}
			public void setFl(String fl) {
				this.fl = fl;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public String getNotice() {
				return notice;
			}
			public void setNotice(String notice) {
				this.notice = notice;
			}
			
			
		}

		@Override
		public String toString() {
			return "WeatherResponseJson [date=" + date + ", message=" + message + ", status=" + status + ", city="
					+ city + ", count=" + count + ", data=" + data + "]";
		}
		
}
