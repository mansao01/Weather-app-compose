package com.example.weatherappcompose.data.network.response

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue


data class FullWeatherResponse(

    @field:SerializedName("current")
    val current: Current,

    @field:SerializedName("location")
    val location: Location,

    @field:SerializedName("forecast")
    val forecast: Forecast
) 


data class HourItem(

    @field:SerializedName("feelslike_c")
    val feelslikeC: @RawValue Any? = null,

    @field:SerializedName("feelslike_f")
    val feelslikeF: @RawValue Any? = null,

    @field:SerializedName("wind_degree")
    val windDegree: Int,

    @field:SerializedName("windchill_f")
    val windchillF: @RawValue Any? = null,

    @field:SerializedName("windchill_c")
    val windchillC: @RawValue Any? = null,

    @field:SerializedName("temp_c")
    val tempC: @RawValue Any? = null,

    @field:SerializedName("temp_f")
    val tempF: @RawValue Any? = null,

    @field:SerializedName("cloud")
    val cloud: Int,

    @field:SerializedName("wind_kph")
    val windKph: @RawValue Any? = null,

    @field:SerializedName("wind_mph")
    val windMph: @RawValue Any? = null,

    @field:SerializedName("humidity")
    val humidity: Int,

    @field:SerializedName("dewpoint_f")
    val dewpointF: @RawValue Any? = null,

    @field:SerializedName("will_it_rain")
    val willItRain: Int,

    @field:SerializedName("uv")
    val uv: @RawValue Any? = null,

    @field:SerializedName("heatindex_f")
    val heatindexF: @RawValue Any? = null,

    @field:SerializedName("dewpoint_c")
    val dewpointC: @RawValue Any? = null,

    @field:SerializedName("is_day")
    val isDay: Int,

    @field:SerializedName("precip_in")
    val precipIn: @RawValue Any? = null,

    @field:SerializedName("heatindex_c")
    val heatindexC: @RawValue Any? = null,

    @field:SerializedName("wind_dir")
    val windDir: String,

    @field:SerializedName("gust_mph")
    val gustMph: @RawValue Any? = null,

    @field:SerializedName("pressure_in")
    val pressureIn: @RawValue Any? = null,

    @field:SerializedName("chance_of_rain")
    val chanceOfRain: Int,

    @field:SerializedName("gust_kph")
    val gustKph: @RawValue Any? = null,

    @field:SerializedName("precip_mm")
    val precipMm: @RawValue Any? = null,

    @field:SerializedName("condition")
    val condition: Condition,

    @field:SerializedName("will_it_snow")
    val willItSnow: Int,

    @field:SerializedName("vis_km")
    val visKm: @RawValue Any? = null,

    @field:SerializedName("time_epoch")
    val timeEpoch: Int,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("chance_of_snow")
    val chanceOfSnow: Int,

    @field:SerializedName("pressure_mb")
    val pressureMb: @RawValue Any? = null,

    @field:SerializedName("vis_miles")
    val visMiles: @RawValue Any? = null
) 


data class Current(

    @field:SerializedName("feelslike_c")
    val feelslikeC: @RawValue Any? = null,

    @field:SerializedName("uv")
    val uv: @RawValue Any? = null,

    @field:SerializedName("last_updated")
    val lastUpdated: String,

    @field:SerializedName("feelslike_f")
    val feelslikeF: @RawValue Any? = null,

    @field:SerializedName("wind_degree")
    val windDegree: Int,

    @field:SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int,

    @field:SerializedName("is_day")
    val isDay: Int,

    @field:SerializedName("precip_in")
    val precipIn: @RawValue Any? = null,

    @field:SerializedName("wind_dir")
    val windDir: String,

    @field:SerializedName("gust_mph")
    val gustMph: @RawValue Any? = null,

    @field:SerializedName("temp_c")
    val tempC: @RawValue Any? = null,

    @field:SerializedName("pressure_in")
    val pressureIn: @RawValue Any? = null,

    @field:SerializedName("gust_kph")
    val gustKph: @RawValue Any? = null,

    @field:SerializedName("temp_f")
    val tempF: @RawValue Any? = null,

    @field:SerializedName("precip_mm")
    val precipMm: @RawValue Any? = null,

    @field:SerializedName("cloud")
    val cloud: Int,

    @field:SerializedName("wind_kph")
    val windKph: @RawValue Any? = null,

    @field:SerializedName("condition")
    val condition: Condition,

    @field:SerializedName("wind_mph")
    val windMph: @RawValue Any? = null,

    @field:SerializedName("vis_km")
    val visKm: @RawValue Any? = null,

    @field:SerializedName("humidity")
    val humidity: Int,

    @field:SerializedName("pressure_mb")
    val pressureMb: @RawValue Any? = null,

    @field:SerializedName("vis_miles")
    val visMiles: @RawValue Any? = null
)



data class ForecastdayItem(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("astro")
    val astro: Astro,

    @field:SerializedName("date_epoch")
    val dateEpoch: Int,

    @field:SerializedName("hour")
    val hour: List<HourItem>,

    @field:SerializedName("day")
    val day: Day
) 


data class Forecast(

    @field:SerializedName("forecastday")
    val forecastday: List<ForecastdayItem>
) 


data class Condition(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("icon")
    val icon: String,

    @field:SerializedName("text")
    val text: String
) 


data class Astro(

    @field:SerializedName("moonset")
    val moonset: String,

    @field:SerializedName("moon_illumination")
    val moonIllumination: String,

    @field:SerializedName("sunrise")
    val sunrise: String,

    @field:SerializedName("moon_phase")
    val moonPhase: String,

    @field:SerializedName("sunset")
    val sunset: String,

    @field:SerializedName("moonrise")
    val moonrise: String
) 


data class Day(

    @field:SerializedName("avgvis_km")
    val avgvisKm: @RawValue Any? = null,

    @field:SerializedName("uv")
    val uv: @RawValue Any? = null,

    @field:SerializedName("avgtemp_f")
    val avgtempF: @RawValue Any? = null,

    @field:SerializedName("avgtemp_c")
    val avgtempC: @RawValue Any? = null,

    @field:SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,

    @field:SerializedName("maxtemp_c")
    val maxtempC: @RawValue Any? = null,

    @field:SerializedName("maxtemp_f")
    val maxtempF: @RawValue Any? = null,

    @field:SerializedName("mintemp_c")
    val mintempC: @RawValue Any? = null,

    @field:SerializedName("avgvis_miles")
    val avgvisMiles: @RawValue Any? = null,

    @field:SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int,

    @field:SerializedName("mintemp_f")
    val mintempF: @RawValue Any? = null,

    @field:SerializedName("totalprecip_in")
    val totalprecipIn: @RawValue Any? = null,

    @field:SerializedName("totalsnow_cm")
    val totalsnowCm: @RawValue Any? = null,

    @field:SerializedName("avghumidity")
    val avghumidity: @RawValue Any? = null,

    @field:SerializedName("condition")
    val condition: Condition,

    @field:SerializedName("maxwind_kph")
    val maxwindKph: @RawValue Any? = null,

    @field:SerializedName("maxwind_mph")
    val maxwindMph: @RawValue Any? = null,

    @field:SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,

    @field:SerializedName("totalprecip_mm")
    val totalprecipMm: @RawValue Any? = null,

    @field:SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int
) 


data class Location(

    @field:SerializedName("localtime")
    val localtime: String,

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("localtime_epoch")
    val localtimeEpoch: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("lon")
    val lon: @RawValue Any? = null,

    @field:SerializedName("region")
    val region: String,

    @field:SerializedName("lat")
    val lat: @RawValue Any? = null,

    @field:SerializedName("tz_id")
    val tzId: String
) 
