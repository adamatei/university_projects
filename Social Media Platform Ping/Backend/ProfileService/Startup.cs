using MassTransit;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;
using ProfileService.Consumers;
using ProfileService.DataAccess;
using ProfileService.Interfaces;
using ProfileService.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ProfileService
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddCors();
            services.AddMassTransit(x =>
            {
                x.AddConsumer<RegistrationConsumer>();
              
                x.UsingRabbitMq((ctx, cfg) =>
                {
                    cfg.Host("amqp://guest:guest@rabbitmq:5672");

                    //exchange
                    cfg.ReceiveEndpoint("register_queue", c =>
                    {
                        c.ConfigureConsumer<RegistrationConsumer>(ctx);
                    });                  
                });
            });
            services.AddDbContext<DatabaseContext>();
            services.AddTransient<IProfileManager, ProfileManager>();
            services.AddMassTransitHostedService();
            services.AddControllers();
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "ProfileService", Version = "v1" });
            });
            services.AddSingleton<EncrytionLogic>(new EncrytionLogic());
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseSwagger();
                app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "ProfileService v1"));
            }

            app.UseRouting();

            app.UseAuthorization();
            app.UseCors(
                options => options.WithOrigins("http://localhost:3000").AllowAnyMethod().AllowAnyHeader()
            );
            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
