using MassTransit;
using MassTransitModels;
using NotificationService.Interfaces;
using NotificationService.Managers;
using NotificationService.Models;
using System;
using System.Threading.Tasks;

namespace NotificationService.Consumers
{
    public class LikeConsumer : IConsumer<Like>
    {
        private readonly INotificationManager manager;

        public LikeConsumer(INotificationManager manager)
        {
            this.manager = manager;
        }
        public async Task Consume(ConsumeContext<Like> context)
        {
            var msg = context.Message;
            //TO DO
            //if (msg.Total % 5 == 0)
           // {
                Notification notification = new Notification { Content = $"{msg.Total} users liked your post: {msg.PostText.Substring(0, 5)}" , CreatedOn = DateTime.Now, For = msg.LikedUsername };
                await manager.CreateNotification(notification);
                await Console.Out.WriteLineAsync(msg.Total + " users liked " + msg.LikedUsername + "'s post");
           // }
        }
    }
}
