namespace webApiPro.SqlModel.Flight
{
    public class DelayedFlights
    {
        public int? id { get; set; }
        public string? Year { get; set; }
        public string? Month { get; set; }
        public string? DayofMonth { get; set; }
        public string? DayOfWeek { get; set; }
        public string? DepTime { get; set; }
        public string? CRSDepTime { get; set; }
        public string? ArrTime { get; set; }
        public string? CRSArrTime { get; set; }
        public string? UniqueCarrier { get; set; }
        public string? FlightNum { get; set; }
        public string? TailNum { get; set; }
        public string? ActualElapsedTime { get; set; }
        public string? CRSElapsedTime { get; set; }
        public string? AirTime { get; set; }
        public string? ArrDelay {  get; set; }
        public string? DepDelay { get; set; }
        public string? Origin {  get; set; }
        public string? Dest {  get; set; }
        public string? Distance { get; set; }
        public string? TaxiIn { get; set; }
        public string? TaxiOut { get; set; }
        public string? Cancelled {  get; set; }
        public string? CancellationCode { get; set; }
        public string? Diverted { get; set; }
        public string? CarrierDelay { get; set; }
        public string? WeatherDelay { get; set; }
        public string? NASDelay { get; set; }
        public string? SecurityDelay { get; set; }
        public string? LateAircraftDelay { get; set; }
    }
}
