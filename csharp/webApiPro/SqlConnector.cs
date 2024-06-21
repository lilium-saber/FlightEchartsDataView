using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Internal;


namespace webApiPro
{
    public class SqlConnector : DbContext
    {
        public SqlConnector(){}

        private static string connection_centos = "server=centos;database=flight;user=root;password=hadoop;AllowPublicKeyRetrieval=True;port=3306;";

        //public SqlConnector(DbContextOptions<SqlConnector> options) : base(options){}

        public DbSet<SqlModel.Flight.carrierdelayrate> carrierdelayrates { get; set; }
        public DbSet<SqlModel.Flight.carrierdelays> carrierdelays { get; set; }
        public DbSet<SqlModel.Flight.DelayedFlights> delayedFlights { get; set; }
        public DbSet<SqlModel.Flight.lateaircraftdelayrate> llateaircraftdelayrates { get;set; }
        public DbSet<SqlModel.Flight.longestflight> longestflights { get; set; }
        public DbSet<SqlModel.Flight.maxarrdelay> maxarrdelays { get; set; }
        public DbSet<SqlModel.Flight.maxdelay> maxdelays { get; set; }
        public DbSet<SqlModel.Flight.maxdepdelay> maxdepdelays { get; set; }
        public DbSet<SqlModel.Flight.MainDelayReasonRate> mainDelayReasons { get; set; }
        public DbSet<SqlModel.Flight.monthRate> monthRates { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql("server=centos;database=flight;user=root;port=3306;password=hadoop;AllowPublicKeyRetrieval=True;",new MySqlServerVersion(new Version(5, 7, 29)));
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<SqlModel.Flight.carrierdelayrate>().ToTable("carrierdelayrate").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.carrierdelays>().ToTable("carrierdelays").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.DelayedFlights>().ToTable("DelayedFlights").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.lateaircraftdelayrate>().ToTable("lateaircraftdelayrate").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.longestflight>().ToTable("longestflight").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.maxarrdelay>().ToTable("maxarrdelay").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.maxdelay>().ToTable("maxdelay").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.maxdepdelay>().ToTable("maxdepdelay").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.MainDelayReasonRate>().ToTable("mainDelayReasonRate").HasNoKey();
            modelBuilder.Entity<SqlModel.Flight.monthRate>().ToTable("monthdelayrate").HasNoKey();
        }


        /*
        public DbSet<SqlModel.User> Users { get; set; }
        public DbSet<SqlModel.Text> Texts { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql(connectionString,ServerVersion.AutoDetect(connectionString));
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<SqlModel.User>().ToTable("User");
            modelBuilder.Entity<SqlModel.Text>().ToTable("Text");
        }

        public bool Login(string uid, string username, string password)
        {
            var user = Users.Where(u => u.Uid == uid &&  u.Password == password).FirstOrDefault();
            if(user == null)
            {
                return false;
            }
            return true;
        }

        public bool AddUser(string uid, string username, string password)
        {
            var user = Users.Where(u => u.Uid == uid).FirstOrDefault();
            if(user != null)
            {
                return false;
            }
            Users.Add(new SqlModel.User { Uid = uid, Name = username, Password = password, Create_time = System.DateTime.Now});
            SaveChanges();
            return true;
        }

        public bool ChangePassword(string uid, string password)
        {
            var user = Users.Where(u => u.Uid == uid).FirstOrDefault();
            if(user == null)
            {
                return false;
            }
            user.Password = password;
            SaveChanges();
            return true;
        }

        public bool CheckUser(string uid, string password)
        {
            var user = Users.Where(u => u.Uid == uid && u.Password == password).FirstOrDefault();
            if(user == null)
            {
                return false;
            }
            return true;
        }

        public void AddText(int pv, string title, string content, string picture)
        {
            var iPv = Texts.Where(t => t.Pv == pv).FirstOrDefault();
            if(iPv != null)
            {
                return;
            }
            if(picture == null)
            {
                picture = "NULL";
            }
            Texts.Add(new SqlModel.Text { Pv = pv, Title = title, Content = content, Create_time = System.DateTime.Now, Picture = picture});
            SaveChanges();
        }

        public List<string>? GetText(int pv)
        {
            var text = Texts.Where(t => t.Pv == pv).FirstOrDefault();
            if(text == null)
            {
                return null;
            }
            List<string> result = new List<string>();
            result.Add(text.Title);
            result.Add(text.Content);
            result.Add(text.Picture);
            return result;
        }

        public void ChangeText(int pv, string title, string content, string picture)
        {
            var text = Texts.Where(t => t.Pv == pv).FirstOrDefault();
            if(text == null)
            {
                return;
            }
            text.Title = title;
            text.Content = content;
            if (picture == null)
            {
                picture = "NULL";
            }
            text.Picture = picture;
            SaveChanges();
        }

        public void DeleteText(int pv)
        {
            var text = Texts.Where(t => t.Pv == pv).FirstOrDefault();
            if(text == null)
            {
                return;
            }
            Texts.Remove(text);
            SaveChanges();
        }

        public void DeleteUser(string uid)
        {
            var user = Users.Where(u => u.Uid == uid).FirstOrDefault();
            if(user == null)
            {
                return;
            }
            Users.Remove(user);
            SaveChanges();
        }
        */

    }
}
