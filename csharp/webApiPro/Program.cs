using Microsoft.EntityFrameworkCore;
using webApiPro;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.WebHost.ConfigureKestrel(options =>
{
    options.ListenAnyIP(8080);
});

builder.Services.AddCors(options =>
{
    options.AddPolicy("CorsPolicy", builder =>
    {
        builder.AllowAnyOrigin()
               .AllowAnyMethod()
               .AllowAnyHeader();
    });
});

//var connectionString = "server=centos;database=flight;user=root;password=hadoop;AllowPublicKeyRetrieval=True;port=3306;";
builder.Services.AddDbContext<webApiPro.SqlConnector>(options => options.UseMySql("server=sh-cynosdbmysql-grp-7htri1i4.sql.tencentcdb.com;database=vsccauc;user=testCode;password=TestCode636;port=20094;", 
    new MySqlServerVersion(new Version(8, 0, 1)),
    mySqlOptions => mySqlOptions.EnableRetryOnFailure()
    ));

//builder.Services.AddSingleton<HiveConnector>(provider => new HiveConnector("Driver={Hive ODBC Driver};Host=centos;Port=10000;Database=flight;UID=root;PWD=;"));

//builder.Services.AddSingleton<HiveConnector>();

var app = builder.Build();

app.UseCors("CorsPolicy");
app.UseStaticFiles();
app.MapFallbackToFile("index.html");

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

using (var scope = app.Services.CreateScope())
{
    var services = scope.ServiceProvider;
    var context = services.GetRequiredService<webApiPro.SqlConnector>();
    //context.Database.Migrate();
}


app.UseRouting();

app.UseAuthorization();
app.UseAuthentication();

app.UseCors(x => x.AllowAnyHeader().AllowAnyMethod().AllowAnyOrigin());

app.UseEndpoints(endpoints =>
{
    endpoints.MapControllers();
});

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
