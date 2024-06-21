namespace webApiPro.SqlModel.Flight
{
    public class monthRate
    {
        public int Month { get; set; }
        public int total_count { get; set; }
        public int delay_count { get; set; }
        public double delay_ratio { get; set; }
    }
}
