using MassTransit;
using MassTransitModels;
using ProfileService.Interfaces;
using ProfileService.Models;
using ProfileService.Services;
using System;
using System.Threading.Tasks;

namespace ProfileService.Consumers
{
    public class RegistrationConsumer : IConsumer<RegistrationUpdate>
    {
        private readonly IProfileManager profileManager;

        public RegistrationConsumer(IProfileManager profileManager)
        {
            this.profileManager = profileManager;
        }
        public async Task Consume(ConsumeContext<RegistrationUpdate> context)
        {
            var msg = context.Message;
            //TO DO
            try
            {
                Profile profile = new Profile { Username = msg.Username, isBlocked = false, isPublic = true, Role = "user", Birthday = new DateTime(), Education = "Unknown", Intro = "Unknown", Job = "Unknown", LivingCity = "Unknown", LivingCountry = "Unknown", OriginCityy = "Unknown", OriginCountry = "Unknown" };
                await Console.Out.WriteLineAsync("RabbitMQ sent over the following username " + msg.Username);
                await profileManager.CreateProfile(profile);
                await Console.Out.WriteLineAsync("A new account was created with the username " + msg.Username);
            }
            catch(Exception ex)
            {
                await Console.Out.WriteLineAsync(ex.Message);
            }
        }
    }
}
