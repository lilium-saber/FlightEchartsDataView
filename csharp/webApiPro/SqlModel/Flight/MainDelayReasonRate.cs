namespace webApiPro.SqlModel.Flight
{
    public class MainDelayReasonRate
    {
        public int Year { get; set; }
        public int Month { get; set; }
        public int NumCarrierDelays {  get; set; }
        public int NumWeatherDelays { get; set; }
        public int NumNASDelays { get; set; }
        public int NumSecurityDelays { get; set; }
        public int NumLateAircraftDelays { get; set; }
        public int TotalDelays { get; set; }
        public double CarrierDelayRate { get; set; }
        public double WeatherDelayRate { get; set;}
        public double NASDelayRate { get; set; }
        public double SecurityDelayRate { get; set; }
        public double LateAircraftDelayRate { get;set; }
        
    }
}
