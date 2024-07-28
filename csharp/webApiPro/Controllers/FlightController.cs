using Microsoft.AspNetCore.Mvc;
using webApiPro.SqlModel.Flight;

namespace webApiPro.Controllers
{

    [Route("/flight")]
    [ApiController]
    public class FlightController : Controller
    {
        private readonly SqlConnector _sqlConnector;
        private readonly HiveConnector _hiveConnector;

        /*
        public FlightController(SqlConnector sqlConnector)
        {
            _sqlConnector = sqlConnector;
        }
        */

        public FlightController(SqlConnector sqlConnector, HiveConnector hiveConnector)
        {
            _sqlConnector = sqlConnector;
            _hiveConnector = hiveConnector;
        }

        public class OriginGet
        {
            public string? origin { get; set; }
            public int? maxDelay { get; set; }
        }

        public class AirTimeGet
        {
            public int? FlightNub { get; set; }
            public int? AirTime { get; set; }
        }

        public class MonthRateGet
        {
            public int? month { get; set; }
            public string? delay_ratio { get; set; }
        }


        [HttpGet("originmaxdelay")]
        public List<OriginGet> getOriginDelay()
        {
            var temp =_sqlConnector.maxdepdelays
                .OrderByDescending(f => f.MaxDepDelay)
                .Where(f => f.MaxDepDelay.HasValue && f.Origin != null)
                .Take(10)
                .ToList();
            List<OriginGet> result = new List<OriginGet>();
            foreach(var f in temp)
            {
                OriginGet og = new OriginGet();
                og.origin = f.Origin;
                og.maxDelay = f.MaxDepDelay;
                result.Add(og);
            }
            return result;
        }

        [HttpGet("destmaxdelay")]
        public List<SqlModel.Flight.maxarrdelay> getDestinationDelay()
        {
            return _sqlConnector.maxarrdelays
                .OrderByDescending(f => f.MaxArrDelay)
                .Where(f => f.Dest != null && f.MaxArrDelay.HasValue)
                .Take(10)
                .ToList();
        }

        [HttpGet("typedelay")]
        public List<SqlModel.Flight.maxdelay> GetMaxdelays()
        {
            return _sqlConnector.maxdelays
                .Where(f => f.MaxDelay.HasValue && f.DelayType != null)
                .ToList();
        }

        [HttpGet("airlongest")]
        public List<AirTimeGet> GetLongestflights()
        {
            var temp = _sqlConnector.longestflights
                .OrderByDescending(f => f.MaxAirTime)
                .Where(f => f.FlightNum.HasValue && f.MaxAirTime.HasValue)
                .Take(10)
                .ToList();
            List<AirTimeGet> list = new List<AirTimeGet>();
            foreach(var f in temp)
            {
                AirTimeGet airTimeGet = new AirTimeGet();
                airTimeGet.AirTime = f.MaxAirTime;
                airTimeGet.FlightNub = f.FlightNum;
                list.Add(airTimeGet);
            }
            return list;

        }

        [HttpGet("maindelayreasonrate")]
        public List<MainDelayReasonRate> GetMainReason()
        {
            return _sqlConnector.mainDelayReasons
                .OrderByDescending(f => f.Month)
                .ToList();
        }

        [HttpGet("monthrate")]
        public List<MonthRateGet> GetMonthRateGets()
        {
            List<SqlModel.Flight.monthRate> monthRates = _sqlConnector.monthRates
                .OrderByDescending(f => f.Month) 
                .ToList();
            List<MonthRateGet> list = new List<MonthRateGet>();
            foreach (var f in monthRates)
            {
                MonthRateGet monthRateGet = new MonthRateGet();
                monthRateGet.month = f.Month;
                monthRateGet.delay_ratio = (f.delay_ratio * 100).ToString("F2");
                list.Add(monthRateGet);
            }
            return list;
        }

        [HttpGet("monthrate/{month}")]
        public List<MonthRateGet> GetMonthRateGets(int month)
        {
            List<SqlModel.Flight.monthRate> monthRates = _sqlConnector.monthRates
                .Where(f => f.Month == month)
                .OrderByDescending(f => f.Month)
                .ToList();
            List<MonthRateGet> list = new List<MonthRateGet>();
            foreach (var f in monthRates)
            {
                MonthRateGet monthRateGet = new MonthRateGet();
                monthRateGet.month = f.Month;
                monthRateGet.delay_ratio = (f.delay_ratio * 100).ToString("F2");
                list.Add(monthRateGet);
            }
            return list;
        }


        [HttpPost("monthrate")]
        public string PostMonthRate([FromBody] MonthRateGet monthRateGet)
        {
            _sqlConnector.monthRates.Add(new SqlModel.Flight.monthRate
            {
                Month = monthRateGet.month.Value,
                delay_ratio = double.Parse(monthRateGet.delay_ratio) / 100
            });
            return "PostMonthRate";
        }

        [HttpGet("hivetest")]
        public IActionResult GetHiveTest()
        {
            string sql = "SELECT * FROM delayedflights LIMIT 10";
            try
            {
                var result = _hiveConnector.Query(sql);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return StatusCode(500, "Internal server error: " + ex.Message);
            }
        }

        


    }
}
