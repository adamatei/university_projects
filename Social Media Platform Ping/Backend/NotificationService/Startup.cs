using MassTransit;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;
using NotificationService.Consumers;
using NotificationService.DataAccess;
using NotificationService.Interfaces;
using NotificationService.Managers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService
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
                x.AddConsumer<FollowConsumer>();
                x.AddConsumer<LikeConsumer>();
                x.AddConsumer<MentionConsumer>();
                x.AddConsumer<DeleteRequestConsumer>();
                x.UsingRabbitMq((ctx, cfg) =>
                {
                    cfg.Host("amqp://guest:guest@rabbitmq:5672");

                    //exchange
                    cfg.ReceiveEndpoint(Configuration["ServiceBus:Queue"], c =>
                    {
                        c.ConfigureConsumer<FollowConsumer>(ctx);
                    });
                    cfg.ReceiveEndpoint("mention_queue", c =>
                    {
                        c.ConfigureConsumer<MentionConsumer>(ctx);
                    });
                    cfg.ReceiveEndpoint("like_queue", c =>
                    {
                        c.ConfigureConsumer<LikeConsumer>(ctx);
                    });
                    cfg.ReceiveEndpoint("delete_notifications", c =>
                    {
                        c.ConfigureConsumer<DeleteRequestConsumer>(ctx);
                    });
                });
            });
            services.AddMassTransitHostedService();
            services.AddDbContext<DatabaseContext>();
            services.AddTransient<INotificationManager, NotificationManager>();
            services.AddControllers();
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "NotificationService", Version = "v1" });
            });
            services.AddStackExchangeRedisCache(options => {
                options.Configuration = "localhost:6379";
                options.InstanceName = "localRedis_";
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseSwagger();
                app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "NotificationService v1"));
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
