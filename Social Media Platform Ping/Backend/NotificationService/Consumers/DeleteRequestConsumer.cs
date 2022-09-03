using MassTransit;
using MassTransitModels;
using NotificationService.Interfaces;
using System;
using System.Threading.Tasks;

namespace NotificationService.Consumers
{
    public class DeleteRequestConsumer : IConsumer<DeleteRequest>
    {
        private readonly INotificationManager manager;

        public DeleteRequestConsumer(INotificationManager manager)
        {
            this.manager = manager;
        }
        public async Task Consume(ConsumeContext<DeleteRequest> context)
        {
            var msg = context.Message;

            try
            {
                await manager.DeleteNotificationsByUsername(msg.Username);

                await Console.Out.WriteLineAsync(msg.Username + " is deleted ");
            }
            catch (Exception e)
            {
                await Console.Out.WriteLineAsync(e.Message);
            }
        }
    }
}
