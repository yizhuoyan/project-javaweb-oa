package com.neusoft.thirdparty;

public class WeatherForecastDto {
	private String cityName;
	private String temperature;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public static WeatherForecastDto of(WeatherResponseJson json) {
		WeatherForecastDto dto=new WeatherForecastDto();
		dto.setCityName(json.getCity());
		dto.setTemperature(json.getData().getWendu());
		return dto;
	}
	
	
}
