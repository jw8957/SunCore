using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace SunCoreServer
{
    public class DBController
    {
        public DBController()
        {
            //SqlConnection conn
            conStr = @"Data Source=.\SQLExpress;Initial Catalog=SunCoreDB;Integrated Security=True";
            conn = new SqlConnection(conStr);
            comm = new SqlCommand();

            conn.Open();
            conn.Close();
        }

        public String[] getDBWeather() {
            comm.Connection = conn;
            comm.Connection.Open();

            comm.CommandText = "SELECT TOP 1 [TEMPRETURE],[HUMIDITY],[WINDSPEED],[WINDDIRECTION],[AIRPRESSURE] FROM [SunCoreDB].[dbo].[PERF_WEATHER2]";
            dr = comm.ExecuteReader();
            dr.Read();
            String []weather=new String[5];

            //double tmp = dr.GetDouble(0);
            //int a;

            weather[0] = dr.GetDouble(0).ToString();
            weather[1] = dr.GetDouble(1).ToString();
            weather[2] = dr.GetDouble(2).ToString();
            weather[3] = dr.GetDouble(3).ToString();
            weather[4] = dr.GetDouble(4).ToString();
            comm.Connection.Close();

            return weather;
        }

        public String[] getDBAlarm()
        {
            comm.Connection = conn;
            comm.Connection.Open();

            comm.CommandText = "SELECT TOP 1 [ALARMOBJDN],[ALARMOBJTYPE],[ALARMLEVEL],CONVERT(DateTime,[BEGINTIME]) FROM [SunCoreDB].[dbo].[ALM_ALARMINFO]";
            dr = comm.ExecuteReader();
            dr.Read();
            String[] alarm = new String[4];

            alarm[0] = dr.GetString(0);
            alarm[1] = dr.GetString(1);
            alarm[2] = dr.GetDecimal(2).ToString();
            alarm[3] = dr.GetDateTime(3).ToString(); 
            
            comm.Connection.Close();
            return alarm;
        }

        public string[] getDBEletric() { 
            //SELECT TOP 1 [PV_VOLTAGE] FROM [SunCoreDB].[dbo].[PERF_ARRAY] 单元
            //SELECT TOP 1 [CAPACITY_CUMULATIVE_EM] FROM [SunCoreDB].[dbo].[PERF_STATION] 阵列
            //
            String[] eletric = new String[3];
            comm.Connection = conn;
            comm.Connection.Open();

            comm.CommandText = "SELECT TOP 1 [PV_VOLTAGE] FROM [SunCoreDB].[dbo].[PERF_ARRAY]";
            dr = comm.ExecuteReader();
            dr.Read();
            eletric[0] = dr.GetString(0);
            dr.Close();

            comm.CommandText = "SELECT TOP 1 [CAPACITY_CUMULATIVE_EM] FROM [SunCoreDB].[dbo].[PERF_STATION]";
            dr = comm.ExecuteReader();
            dr.Read();
            eletric[1] = dr.GetDouble(0).ToString();
            dr.Close();

            eletric[2] = "0";

            comm.Connection.Close();
            return eletric;
        }

        public string[] getDBOnlineInstance() { 
            //SELECT [tenantName] FROM [SunCoreDB].[dbo].[onlineInstance]
            String[] instance;

            comm.Connection = conn;
            comm.Connection.Open();

            comm.CommandText = "SELECT [tenantName] FROM [SunCoreDB].[dbo].[onlineInstance]";
            dr = comm.ExecuteReader();

            instance=new String[2]; 
            dr.Read();
            instance[0] = dr.GetString(0);
            dr.Read();
            instance[1] = dr.GetString(0);

            comm.Connection.Close();
            return instance;
        }

        public bool Login(String usrname,String password) { 
            //SELECT * FROM [SunCoreDB].[dbo].[SEC_USERINFO] WHERE USERNAME='abc' AND PWD='abc'
            String[] instance;

            comm.Connection = conn;
            comm.Connection.Open();

            comm.CommandText = "SELECT * FROM [SunCoreDB].[dbo].[SEC_USERINFO] WHERE USERNAME='"+usrname+"' AND PWD='"+password+"'";
            dr = comm.ExecuteReader();
            bool flag = dr.HasRows;
            comm.Connection.Close();
            
            return flag;
        }

        private String conStr;
        private SqlConnection conn;
        private SqlCommand comm;
        private SqlDataReader dr;
    }
}