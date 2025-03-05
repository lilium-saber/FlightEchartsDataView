using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using webApiPro.SqlModel.Flight;

namespace webApiPro.Controllers
{

    [Route("/flight")]
    [ApiController]
    public class FlightController : Controller
    {
        private readonly SqlConnector _sqlConnector;
        //private readonly HiveConnector _hiveConnector;

        
        public FlightController(SqlConnector sqlConnector)
        {
            _sqlConnector = sqlConnector;
        }
        
        /*
        public FlightController(SqlConnector sqlConnector, HiveConnector hiveConnector)
        {
            _sqlConnector = sqlConnector;
            _hiveConnector = hiveConnector;
        }
        */

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
            public int month { get; set; }
            public string delay_ratio { get; set; }
        }


        [HttpGet("originmaxdelay")]
        public async Task<List<OriginGet>> getOriginDelay()
        {
            return await _sqlConnector.maxdepdelays
                .OrderByDescending(f => f.MaxDepDelay)
                .Where(f => f.MaxDepDelay.HasValue && f.Origin != null)
                .Take(10)
                .Select(_ => new OriginGet
                {
                    origin = _.Origin,
                    maxDelay = _.MaxDepDelay
                })
                .ToListAsync();
        }

        [HttpGet("destmaxdelay")]
        public async Task<List<SqlModel.Flight.maxarrdelay>> getDestinationDelay()
        {
            return await _sqlConnector.maxarrdelays
                .OrderByDescending(f => f.MaxArrDelay)
                .Where(f => f.Dest != null && f.MaxArrDelay.HasValue)
                .Take(10)
                .ToListAsync();
        }

        [HttpGet("typedelay")]
        public List<SqlModel.Flight.maxdelay> GetMaxdelays()
        {
            return [.. _sqlConnector.maxdelays.Where(f => f.MaxDelay.HasValue && f.DelayType != null)];
        }

        [HttpGet("airlongest")]
        public async Task<List<AirTimeGet>> GetLongestflights()
        {
            return await _sqlConnector.longestflights
                .OrderByDescending(f => f.MaxAirTime)
                .Where(f => f.FlightNum.HasValue && f.MaxAirTime.HasValue)
                .Take(10)
                .Select(_ => new AirTimeGet
                {
                    FlightNub = _.FlightNum,
                    AirTime = _.MaxAirTime
                })
                .ToListAsync();
        }

        [HttpGet("maindelayreasonrate")]
        public async Task<List<MainDelayReasonRate>> GetMainReason()
        {
            return await _sqlConnector.mainDelayReasons
                .OrderByDescending(f => f.Month)
                .ToListAsync();
        }

        [HttpGet("monthrate")]
        public async Task<List<MonthRateGet>> GetMonthRateGets()
        {
            return await _sqlConnector.monthRates
                .OrderByDescending(f => f.Month) 
                .Select(_ => new MonthRateGet
                {
                    month = _.Month,
                    delay_ratio = (_.delay_ratio * 100).ToString("F2")
                })
                .ToListAsync();
        }

        [HttpGet("monthrate/{month}")]
        public async Task<List<MonthRateGet>> GetMonthRateGets(int month)
        {
            return await _sqlConnector.monthRates
                .Where(f => f.Month == month)
                .OrderByDescending(f => f.Month)
                .Select(_ => new MonthRateGet
                {
                    month = _.Month,
                    delay_ratio = (_.delay_ratio * 100).ToString("F2")
                })
                .ToListAsync();
        }


        [HttpPost("monthrate")]
        public async Task<string> PostMonthRate([FromBody] MonthRateGet monthRateGet)
        {
            await _sqlConnector.monthRates.AddAsync(new SqlModel.Flight.monthRate
            {
                Month = monthRateGet.month,
                delay_ratio = float.Parse(monthRateGet.delay_ratio) / 100
            });
            await _sqlConnector.SaveChangesAsync();
            return "Success";
        }

        /*
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
        }*/

        


    }
}
