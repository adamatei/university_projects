using MassTransit;
using MassTransitModels;
using NotificationService.Interfaces;
using NotificationService.Managers;
using NotificationService.Models;
using System;
using System.Threading.Tasks;

namespace NotificationService.Consumers
{
    public class MentionConsumer : IConsumer<MentionNotification>
    {
        private readonly INotificationManager manager;

        public MentionConsumer(INotificationManager manager)
        {
            this.manager = manager;
        }
        public async Task Consume(ConsumeContext<MentionNotification> context)
        {
            var msg = context.Message;
            //TO DO
            Notification notification = new Notification { Content = $"{msg.Mentioner} mentioned you in a post: : {msg.PostText.Substring(0, 5)}", For = msg.Mentioned, CreatedOn = DateTime.Now};
            await manager.CreateNotification(notification);
            await Console.Out.WriteLineAsync("User " + msg.Mentioner + " mentioned " + msg.Mentioned + " in a post");
        }
    }
}
