using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace SunCoreServer
{
    /// <summary>
    /// SunCoreService 的摘要说明
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // 若要允许使用 ASP.NET AJAX 从脚本中调用此 Web 服务，请取消注释以下行。 
    // [System.Web.Script.Services.ScriptService]
    public class SunCoreService : System.Web.Services.WebService
    {
        DBController dbcntl = new DBController();

        [WebMethod]
        public string HelloWorld() {
            return "Hello World";
        }

        [WebMethod(Description = "获取天气情况")]
        public string[] getWeatherInfo() {
            return dbcntl.getDBWeather();
        }

        [WebMethod(Description = "获取警报")]
        public string[] getAlarm() {
            return dbcntl.getDBAlarm();
        }

        [WebMethod(Description = "获取发电量")]
        public string[] getEletric() {
            return dbcntl.getDBEletric();
        }

        [WebMethod(Description = "获取工单")]
        public string[] getOnlineInstance() {
            return dbcntl.getDBOnlineInstance();
        }

        [WebMethod(Description = "登陆")]
        public bool Login(String usrname,String password) {
            return dbcntl.Login(usrname,password);
        }
    }
}
