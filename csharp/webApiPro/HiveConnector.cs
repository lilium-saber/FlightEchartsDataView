using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Odbc;
using Dapper;
using Microsoft.Hadoop.Hive;

namespace webApiPro
{
    public class HiveConnector
    {
        //private string connect_hive = "Driver={Hive ODBC Driver};Host=centos;Port=10000;Database=huawei;UID=root;PWD=;";
        private string connect_hive = "Host=centos;Port=10000;Database=flight;UID=root;PWD=;";
        private readonly string _connectionString;

        public HiveConnector(string connectionString)
        {
            _connectionString = connectionString;
        }

        public IEnumerable<dynamic> Query(string sql)
        {
            using (var connection = new OdbcConnection(connect_hive))
            {
                connection.Open();
                return connection.Query(sql);
            }
        }


    }
}
