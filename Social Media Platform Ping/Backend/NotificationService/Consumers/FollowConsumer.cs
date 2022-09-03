using MassTransit;
using MassTransitModels;
using NotificationService.Interfaces;
using NotificationService.Managers;
using NotificationService.Models;
using System;
using System.Threading.Tasks;

namespace NotificationService.Consumers
{
    public class FollowConsumer : IConsumer<FollowRequest>
    {
        private readonly INotificationManager manager;

        public FollowConsumer(INotificationManager manager)
        {
            this.manager = manager;
        }
        public async Task Consume(ConsumeContext<FollowRequest> context)
        {
            var msg = context.Message;
            //TO DO
            Notification notification = new Notification { Content = $"{msg.Follower} has requested to follow you.", For = msg.Followed, CreatedOn = DateTime.Now};
            await manager.CreateNotification(notification);
            await Console.Out.WriteLineAsync(Convert.ToString(msg.Id));
        }
    }
}
